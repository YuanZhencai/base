package com.wcs.common.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wcs.common.controller.vo.RoleVo;
import com.wcs.common.controller.vo.UsermstrFormItemsVo;
import com.wcs.common.controller.vo.UsermstrVo;
import com.wcs.common.model.O;
import com.wcs.common.model.P;
import com.wcs.common.model.PU;
import com.wcs.common.model.Rolemstr;
import com.wcs.common.model.Usermstr;
import com.wcs.common.model.Userrole;

@Stateless
public class UserService implements Serializable {

	private static final long serialVersionUID = -4531023608569097125L;

	@PersistenceContext
	public EntityManager em;

	@SuppressWarnings("unchecked")
	public List<UsermstrVo> getAllUsermstrVo(UsermstrFormItemsVo ufiv) {
		StringBuffer sb = new StringBuffer();
		sb.append("select u from Usermstr u where 1=1");

		if (ufiv.getAdAccount() != null && !"".equals(ufiv.getAdAccount())) {
			sb.append(" and u.adAccount like '%" + ufiv.getAdAccount().trim()
					+ "%'");
		}
		if (ufiv.getUserName() != null && !"".equals(ufiv.getUserName())) {
			sb.append(" and EXISTS(select p.id from P p where u.pid=p.id and p.nachn like '%"
					+ ufiv.getUserName().trim() + "%' and p.defunctInd='N')");
		}
		if (ufiv.getRolemstrId() != null && !"".equals(ufiv.getRolemstrId())) {
			sb.append(" and EXISTS(select ur.usermstr.id from Userrole ur where u.id=ur.usermstr.id and ur.rolemstr.id="
					+ ufiv.getRolemstrId() + " and ur.defunctInd='N')");
		}
		if (ufiv.getStatus() != null && !"".equals(ufiv.getStatus())) {
			sb.append("and u.defunctInd='" + ufiv.getStatus() + "'");
		}
		sb.append(" order by u.adAccount");

		String sql = sb.toString();
		List<Usermstr> list = this.em.createQuery(sql).getResultList();
		List<UsermstrVo> listUsermstrVo = new ArrayList<UsermstrVo>();
		UsermstrVo uv = null;
		for (Usermstr u : list) {
			uv = new UsermstrVo();
			uv.setId(u.getId());
			uv.setUsermstr(u);
			uv.setP(getP(getPU(u.getAdAccount()).getPernr()));
			uv.setO(getO(uv.getP().getBukrs()));
			listUsermstrVo.add(uv);
		}
		return listUsermstrVo;
	}

	@SuppressWarnings("unchecked")
	public List<RoleVo> getAllRoleVo() {
		StringBuffer sb = new StringBuffer();
		sb.append("select r from Rolemstr r where r.defunctInd='N' order by r.name");
		String sql = sb.toString();
		List<Rolemstr> list = this.em.createQuery(sql).getResultList();
		List<RoleVo> roleVoList = new ArrayList<RoleVo>();
		RoleVo rv = null;
		if (list.size() != 0) {
			for (Rolemstr r : list) {
				rv = new RoleVo();
				rv.setId(r.getId());
				rv.setRolemstr(r);
				roleVoList.add(rv);
			}
		}
		return roleVoList;
	}

	public P getP(String id) {
		return this.em.find(P.class, id);
	}
	
	public PU getPU(String id) {
		String sql = "select pu from PU pu where pu.id = :id";
		Query q = em.createQuery(sql);
		q.setParameter("id", id);
		return (PU) q.getSingleResult();
	}

	public O getO(String bukrs) {
		String sql = "select o from O o where o.bukrs = :bukrs";
		Query q = em.createQuery(sql);
		q.setParameter("bukrs", bukrs);
		return (O) q.getSingleResult();
	}

	public int getUserCount(String adAccount) {
		int num = 0;
		String sql = "select u from Usermstr u where u.adAccount = :adAccount";
		Query q = em.createQuery(sql);
		q.setParameter("adAccount", adAccount.trim());
		num = q.getResultList().size();
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public boolean saveUserRole(List<Long> selectedRoleVos, Usermstr u,
			String userName) {
		boolean b = false;
		try {
			Userrole userrole = null;
			List<Userrole> selectedUserroleList = new ArrayList<Userrole>();
			if (selectedRoleVos != null && selectedRoleVos.size() != 0) {
				for (int i = 0; i < selectedRoleVos.size(); i++) {
					userrole = new Userrole();
					userrole.setUsermstr(u);
					userrole.setRolemstr(this.em.find(Rolemstr.class,
							selectedRoleVos.get(i)));
					selectedUserroleList.add(userrole);
				}
			}

			StringBuffer sb = new StringBuffer();
			sb.append("select ur from Userrole ur where ur.usermstr.id="
					+ u.getId());
			String sql = sb.toString();
			List<Userrole> existUserroleList = this.em.createQuery(sql)
					.getResultList();

			if (selectedUserroleList.size() != 0) {
				boolean bb = false;
				for (Userrole ur1 : selectedUserroleList) {
					bb = false;
					if (existUserroleList.size() != 0) {
						for (Userrole ur2 : existUserroleList) {
							if (ur1.getRolemstr().getId()
									.compareTo(ur2.getRolemstr().getId()) == 0) {
								bb = true;
								break;
							}
						}
					}
					if (!bb) {
						ur1.setDefunctInd("N");
						ur1.setCreatedBy(userName);
						ur1.setCreatedDatetime(new Date());
						ur1.setUpdatedBy(userName);
						ur1.setUpdatedDatetime(new Date());
						this.em.persist(ur1);
					}
				}
			}

			if (existUserroleList.size() != 0) {
				boolean bb = false;
				for (Userrole ur1 : existUserroleList) {
					bb = false;
					if (selectedUserroleList.size() != 0) {
						for (Userrole ur2 : selectedUserroleList) {
							if (ur1.getRolemstr().getId()
									.compareTo(ur2.getRolemstr().getId()) == 0) {
								bb = true;
								break;
							}
						}
					}
					userrole = this.em.find(Userrole.class, ur1.getId());
					if (bb) {
						userrole.setDefunctInd("N");
					} else {
						userrole.setDefunctInd("Y");
					}
				}
			}

			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

}
