package com.wcs.common.service.permissions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.wcs.base.service.StatelessEntityService;
import org.primefaces.model.TreeNode;

import com.wcs.base.util.ResourcesNode;
import com.wcs.common.model.Resource;
import com.wcs.common.model.Role;
import com.wcs.common.model.RoleResource;

/**
 * 
 * <p>Project: ncp</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2011 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chenlong@wcs-global.com">chen long</a>
 */
@Stateless
public class ResourceService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public ResourceService(){
		
	}

	@Inject
    StatelessEntityService entityService;
	
	public List<Resource> findAllSysResource(){
		String sql = "from Resource rs  order by number";
		return this.entityService.findList(sql);
	}
	
	/**
	 * 
	 * <p>Description: 根据菜单Id得到孩子菜单</p>
	 * @param parentid
	 * @return
	 */
	
	public List<Resource> getChildListById(Long parentid){
		try{
			Query query = entityService.createQuery("from Resource r where r.parentId = ?");
			query.setParameter(1, parentid);
			return (List<Resource>)query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	 /**
	  * 
	  * <p>Description: 得到所有资源</p>
	  * @return
	  */
	
	public List<Resource> getAllTree(){
		try{
			return (List<Resource>)entityService.createQuery("from Resource").getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Description:得到父级资源集合 </p>
	 * @return
	 */
	
	public List<Resource> getFatherTree(){
	    try{
	         String sql = "from Resource t where t.parentId is null";
	         return (List<Resource>)entityService.createQuery(sql).getResultList();
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	
	 /**
	  * 
	  * <p>Description: 通过ID加载资源</p>
	  * @param id
	  * @return
	  */
	
	public Resource loadTree(Long id){
		try{
			
			return entityService.findUnique(Resource.class, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Description: 得到选中节点资源对象集合</p>
	 * @param selectedNodes
	 * @return
	 */
	
	public List<Resource> getSelectResource(TreeNode[] selectedNodes){
		List<Resource> list = new ArrayList<Resource>();
		if(selectedNodes != null){
			int size = selectedNodes.length;
			if(size != 0){
				for(int i = 0 ; i < size ; i++){
					 ResourcesNode node = (ResourcesNode) selectedNodes[i];
					 Resource rs = this.entityService.findUnique(Resource.class,node.getId());
					 list.add(rs);
				}
				return list;
			}else{
				return list;
			}
		}else{
			return list;
		}	
	}
	
	/**
	 * 
	 * <p>Description: 查询角色的资源中间表</p>
	 * @param role
	 * @return
	 */
	
	public List<RoleResource> getResourceListByRole(Role role){
		try{
			String sql = "from RoleResource rs where rs.role= ?1 ";
			Query query = entityService.createQuery(sql);
			query.setParameter(1, role);
			return (List<RoleResource>)query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Description: 查询角色的资源</p>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	
	public List<Resource> findResouceByRole(Role role) throws Exception{
		List<RoleResource> roleResourceList = this.getResourceListByRole(role);
		List<Resource> resourceList = new ArrayList<Resource>();
		if(!roleResourceList.isEmpty()){
			try{
				for(RoleResource roleResource :roleResourceList){
					resourceList.add(roleResource.getResource());
				}
			}catch(Exception e){
				throw e;
			}
			
		}
		return resourceList;
	}
	
	/**
	 * 
	 * <p>Description: 删除角色资源</p>
	 * @param role
	 */
	
	public void deleteRoleResource(Role role){
		String sql = "delete RoleResource rs where rs.role.id=?1";
		this.entityService.batchExecute(sql, role.getId());
	}
	
	/**
	 * 
	 * <p>Description: 根据id查找资源</p>
	 * @param sysResouce
	 * @param compoent
	 * @return
	 * @throws Exception
	 */
	public Resource findResourceByCompenet(List<Resource> sysResouce,String compoent,String currentUrl) throws Exception{
		if(currentUrl == null ||  "".equals(currentUrl)){
			return null;
		}
		if(compoent!=null&&! "".equals(compoent) && sysResouce != null){
			
			for(Resource rs :sysResouce){
				if(currentUrl.equals(rs.getUri())){
					compoent = rs.getNumber()+compoent;
				}
			}
			for(Resource rs :sysResouce){
					if(compoent.equals(rs.getNumber())){
						return  rs;
					}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Description: 根据Uri查找资源菜单 针对菜单级别</p>
	 * @param sysResouce
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Resource findResourceByUrl(List<Resource> sysResouce,String url) throws Exception{
		if(url != null && url.length() != 0 && sysResouce != null){
			for(Resource r  : sysResouce){
				if(url.equals(r.getUri())){
					return r;
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Description: 查询菜单资源下的组件资源</p>
	 * @param sysResouce
	 * @param rs
	 * @return
	 */
    public List<Resource> findMenuComponentResouce(List<Resource> sysResouce,Resource rs) throws Exception{
    	
    	List<Resource> compoentResource = new ArrayList<Resource>();
    	if(rs != null){
    		for(Resource r : sysResouce){
    			if(r.getParentId().equals(rs.getId())){
    				compoentResource.add(r);
    			}
    		}
    	}
		return compoentResource;
	}
    
    /**
     * 
     * <p>Description:查询当前用户所拥有的组件资源 </p>
     * @param allUserResource
     * @param compoentResouce
     * @return
     */
    public List<Resource> findUserCompoentResource(List<Resource> allUserResource,List<Resource> compoentResouce){
    	List<Resource> userCompoentList = new ArrayList<Resource>();
    	if(!compoentResouce.isEmpty()){
    		for(Resource r:compoentResouce){
    			
    				if(allUserResource.contains(r)){
    					userCompoentList.add(r);
    				}
    			
    		}
    	}
    	return userCompoentList;
    }
}
