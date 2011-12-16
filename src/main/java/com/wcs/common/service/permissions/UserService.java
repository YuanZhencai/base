package com.wcs.common.service.permissions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.google.common.collect.Lists;
import com.wcs.base.exception.ServiceException;
import com.wcs.base.service.StatelessEntityService;
import com.wcs.base.util.CollectionUtils;
import com.wcs.common.model.Resource;
import com.wcs.common.model.Role;
import com.wcs.common.model.User;
import com.wcs.common.model.UserRole;

/**
 * 
 * <p>Project: cmdpms</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2011 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chenlong@wcs-global.com">Chen Long</a>
 */
@Stateless
public class UserService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    StatelessEntityService entityService;
//    @Inject
//    UserOrgService userOrgService;
    @Inject
    ResourceService resourceService;
//    @Inject
//    private PurchaseLocEntityService purchaseLocEntityService;
    /*
     * 构造方法
     */
    public UserService() {
       
    }



    /**
     * 
     * <p>
     * Description:查询用户角色
     * </p>
     * 
     * @param user
     * @return
     */
    public List<UserRole> getRoleByUser(User user) throws Exception {
        try {
            if (user != null) {
                String sql1 = "from UserRole urole where   urole.user.id='" + user.getId() + "')";
                return this.entityService.findList(sql1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * <p>
     * Description: 查询所有资源根据角色
     * </p>
     * 
     * @param roleList
     * @return
     */
    public List<Resource> findAllResouceOfRoleList(List<Role> roleList) throws ServiceException {
        List<Resource> distinctResource = new ArrayList<Resource>();
        try{
            if (CollectionUtils.isEmpty(roleList)){
                return distinctResource;
            }
            String jpql = "select res from RoleResource rr join rr.resource res join rr.role role where role in ?1";
            List<Resource> resourceList = entityService.findList(jpql, roleList);
            for (Resource resource : resourceList) {
                if (!distinctResource.contains(resource)) {
                    if ("3".equals(resource.getLevel())) {
                        if (!distinctResource.contains(resourceService.loadTree(resource.getParentId()))) {
                            distinctResource.add(resourceService.loadTree(resource.getParentId()));
                        }
                    }
                    distinctResource.add(resource);
                }
            }
            return distinctResource;
        }catch(Exception e){
            throw new ServiceException(e);
        }
    }
    

    /**
     * 
     * <p>
     * Description: 根据用户查询所拥有的角色
     * </p>
     * 
     * @param user
     * @return
     * @throws Exception
     */
    public List<Role> findAllRoleOfUser(User user)  {
        String jpql = "select r from UserRole ur join ur.user u join ur.role r where r.state=1 and u.id=" + user.getId();
        return entityService.findList(jpql);
    }

    /**
     * 
     * <p>
     * Description: 将资源集合转换成ID集合
     * </p>
     * 
     * @param resouceList
     * @return
     */
    public Long[] getResouceId(List<Resource> resouceList) {
        try {
            Long[] idArray = null;
            if (!CollectionUtils.isEmpty(resouceList)) {

                int size = resouceList.size();
                idArray = new Long[size];
                for (int i = 0; i < size; i++) {
                    idArray[i] = resouceList.get(i).getId();
                }
            }
            return idArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * <p>
     * Description:根据用户对象返回角色ID数组
     * </p>
     * 
     * @param user
     * @return
     */
    public Long[] getRoleIdByUser(User user) {
        try {
            List<UserRole> userRolelist = getRoleByUser(user);
            if (userRolelist != null) {
                int size = userRolelist.size();
                Long[] l = new Long[size];
                for (int i = 0; i < size; i++) {
                    Role role = userRolelist.get(i).getRole();
                    if(role.getState() == 1){
                        l[i] = role.getId();
                    }
                }
                return l;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * <p>
     * Description: 根据用户账户输入匹配
     * </p>
     * 
     * @param account
     * @return
     */

    public List<String> getUserAccountByInput(String account) {
        try {
            String sql = "from User u where  u.defunctInd=false and u.userName like :account";
            Query query = this.entityService.createQuery(sql);
            query.setParameter("account", "%" + account + "%");
            List<User> ulist = query.getResultList();
            if (ulist.size() != 0) {
                List<String> rlist = Lists.newArrayList();
                for (User user : ulist) {
                    rlist.add(user.getUserName());
                }
                return rlist;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * <p>
     * Description: 根据账户和电子邮件查询User
     * </p>
     * 
     * @param account
     * @param emial
     * @return
     */

    public User getUserByEmail(String account, String emial) {
        try {
            String sql = "from User u where u.defunctInd=false and u.userName = :uname and u.email = :Email";
            Query query = this.entityService.createQuery(sql);
            query.setParameter("uname", account);
            query.setParameter("Email", emial);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * <p>
     * Description: 更新指定用户的收购点和收购方信息
     * </p>
     * 
     * @param user
     * @param plocname
     * @param pentityname
     */

    public void updateUserPlocEntity(User user, String plocname, String pentityname) {
        try {
            user.setPurchaseLocName(plocname);
            user.setPurchaseEntityName(pentityname);
            this.entityService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public int saveOrUpdateUserOrg(User user,Long purchaseLocId,Long purchaseentityId) throws Exception{
//        int flag = 0;
//        try{
//            PurchaseLocEntity plocentity = this.purchaseLocEntityService.getLocEntity(purchaseLocId,purchaseentityId);
//            //得到用户收购点
//            Long plocId = this.userOrgService.findPurLocIdByUser(user);
//            //得到用户收购方集合
//            List<Long> pentityList = this.userOrgService.findPurchaseEntityByUser(user);
//            if(null != plocId){
//                //收购点是否相同
//                if(purchaseLocId == 0L){
//                    purchaseLocId = null;
//                }
//                if(plocId.equals(purchaseLocId)){
//                    if(purchaseentityId != null){
//                        //收购方是否相同
//                        if(!pentityList.contains(purchaseentityId)){
//                            saveUserOrg(user,plocentity);
//                            flag = 1;
//                        }else{
//                            flag = 3;
//                        }
//                    }
//                }
//            }else{
//                saveUserOrg(user,plocentity);
//                flag = 2;
//            }
//        }catch(Exception e){
//            throw e;
//        }
//       return flag;
//    }


//    private void saveUserOrg(User user,PurchaseLocEntity plocentity){
//        // 添加收购组织
//        UserOrg uog = new UserOrg();
//        uog.setUser(user);
//        uog.setPurchaseLocEntity(plocentity);
//        this.entityService.create(uog);
//        // 更新用户收购点和收购方名称字段
//        this.updateUserPlocEntity(user, plocentity.getPurchaseLoc().getPurchaseLocName(), plocentity
//                .getPurchaseEntity().getPurchaseEntityName());
//    }


}
