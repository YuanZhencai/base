package com.wcs.base.security.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.exception.ServiceException;
import com.wcs.base.security.model.Permission;
import com.wcs.base.security.model.Resource;
import com.wcs.base.security.model.Role;
import com.wcs.base.security.model.User;
import com.wcs.base.service.StatelessEntityService;
import com.wcs.base.util.CollectionUtils;

@Stateless
public class LoginService implements Serializable{
    private static final long serialVersionUID = 1L;
    final Logger logger = LoggerFactory.getLogger(LoginService.class);
    
    @Inject
    private StatelessEntityService entityService;
    
    /**
     * 通过用户名查询唯一用户
     * @param loginName
     * @return
     */
    public User findUser(String loginName) {
        String jpql = "SELECT u FROM User u WHERE u.loginName=?1";
        List<User> userList  = this.entityService.findList(jpql, loginName);
        if (CollectionUtils.isEmpty(userList)) {
           return null;
        }
        return userList.iterator().next();
    }

   /**
    * 初始化Left导航菜单
    * @param selectId
    * @param leftMenuResouce
    * @param allUserResouce
    * @param sysResouceList
    */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void intitLeftMenu(Long selectId,ConcurrentHashMap<Resource, List<Resource>> leftMenuResouce,CopyOnWriteArrayList<Resource> allUserResouce,List<Resource> sysResouceList) {
      
        // 选中菜单的下属资源
        List<Resource> currentUserLeftResource = loadSecondResourceByTopResouce(selectId, sysResouceList, allUserResouce);
        for (Resource r : currentUserLeftResource) {
            if ("1".equals(r.getLevel())) {
                if (!leftMenuResouce.containsKey(r) && leftMenuResouce.get(r) == null) {
                    // leftMenuResouce.put(r, new ArrayList<Resource>());
                }
            } else if (r.getIsMenu() && "2".equals(r.getLevel())) {
                Resource father = getFactherResouce(sysResouceList, r);
                if (leftMenuResouce.get(father) == null) {
                    if (!leftMenuResouce.containsKey(father)) {
                        List<Resource> childlist = new ArrayList<Resource>();
                        childlist.add(r);
                        leftMenuResouce.put(father, childlist);
                    }
                } else {
                    leftMenuResouce.get(father).add(r);
                }
            }
        }
        //导航菜单排序
        if(!leftMenuResouce.isEmpty()){
            Iterator it = leftMenuResouce.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<Resource, List<Resource>> entry = (Entry<Resource, List<Resource>>) it.next();
                Collections.sort(entry.getValue(), new Comparator<Resource>(){
                    @Override
                    public int compare(Resource resource1, Resource resource2) {
                        return resource1.getNumber().compareTo(resource2.getNumber());
                    }
                });
            }
        }
        
    }
    
   /**
    * 找到用户的top资源
    * @param userResource
    * @param sysResouce
    * @return
    * @throws ServiceException
    */
    public List<Resource> findTopResourceByUser(List<Resource> userResource,List<Resource> sysResouce) throws ServiceException{
        List<Resource> distinctTopResource = new ArrayList<Resource>();
        try{
            if(!userResource.isEmpty()){
                for(Resource r: userResource){
                    if("3".equals(r.getLevel())){ 
                        Resource secondResouce = getFactherResouce(sysResouce,r);
                        Resource firstResouce = getFactherResouce(sysResouce,secondResouce);
                        Resource topResouce = getFactherResouce(sysResouce,firstResouce);
                        if(!distinctTopResource.contains(topResouce)){
                            distinctTopResource.add(topResouce);
                        }
                    }
                    if("2".equals(r.getLevel())){
                        Resource firstResouce = getFactherResouce(sysResouce,r);
                        Resource topResouce = getFactherResouce(sysResouce,firstResouce);
                        if(!distinctTopResource.contains(topResouce)){
                            distinctTopResource.add(topResouce);
                        }
                    }
                    if("1".equals(r.getLevel())){
                        Resource topResouce = getFactherResouce(sysResouce,r);
                        if(!distinctTopResource.contains(topResouce)){
                            distinctTopResource.add(topResouce);
                        }
                    }
                    if("0".equals(r.getLevel())){
                        if(!distinctTopResource.contains(r)){
                            distinctTopResource.add(r);
                        }
                    }
                }
            }
            //top 资源排序
            if(!distinctTopResource.isEmpty()){
                Collections.sort(distinctTopResource, new Comparator<Resource>(){
                    @Override
                    public int compare(Resource r1, Resource r2) {
                        return r1.getNumber().compareTo(r2.getNumber());
                    }
                });
            }
            return distinctTopResource;
        }catch(Exception e){
            throw new ServiceException(e);
        }
       
    }
    
    /**
     * 根据top定位该top的所有下级资源
     * @param selectID
     * @param sysResouce
     * @param allUserResouce
     * @return
     */
    private List<Resource> loadSecondResourceByTopResouce(Long selectID, List<Resource> sysResouce, CopyOnWriteArrayList<Resource> allUserResouce) {
        List<Resource> selectList = new ArrayList<Resource>();
        if (selectID == 0 || allUserResouce.isEmpty()) {
            return selectList;
        }
        try {
            for (Resource r : allUserResouce) {
                if ("1".equals(r.getLevel()) && selectID.equals(getFactherResouce(sysResouce, r).getId())) {
                    selectList.add(r);
                }
                if ("2".equals(r.getLevel()) && r.getIsMenu()) {
                    Resource second = getFactherResouce(sysResouce, r);
                    Resource facther = getFactherResouce(sysResouce, second);
                    if(facther == null){
                        return selectList;
                    }
                    Long topID = facther.getId();
                    if (topID.longValue() == selectID.longValue()) {
                        selectList.add(r);
                    }
                }
            }
            return selectList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectList;
    }
    
   /**
    * 得到资源的父级资源 建议封装成Map
    * @param sysResouce
    * @param child
    * @return
    */
    private Resource getFactherResouce(List<Resource> sysResouce, Resource child) {
        try {
            for (Resource sys : sysResouce) {
                if (child != null && !"0".equals(child.getLevel())) {
                    if (sys.getId().equals(child.getParentId())) {
                        return sys;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Find permission list by role
     * @param role
     * @return
     */
	public List<Permission> findPermissions(Role role) {
		String jpql = "SELECT p FROM Permission p WHERE p.roleid = ?1";
		List<Permission> permissions = entityService.findList(jpql, role.getId());
		return permissions;
	}
}
