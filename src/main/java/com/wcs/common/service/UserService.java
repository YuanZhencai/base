package com.wcs.common.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wcs.common.controller.vo.CompanymstrFormItems;
import com.wcs.common.controller.vo.CompanymstrVo;
import com.wcs.common.controller.vo.PFormItemsVo;
import com.wcs.common.controller.vo.PVo;
import com.wcs.common.controller.vo.PositionorgVo;
import com.wcs.common.controller.vo.RoleVo;
import com.wcs.common.controller.vo.UsermstrFormItemsVo;
import com.wcs.common.controller.vo.UsermstrVo;
import com.wcs.common.model.Companymstr;
import com.wcs.common.model.O;
import com.wcs.common.model.P;
import com.wcs.common.model.Positionorg;
import com.wcs.common.model.Rolemstr;
import com.wcs.common.model.Taxauthority;
import com.wcs.common.model.Usercompany;
import com.wcs.common.model.Usermstr;
import com.wcs.common.model.Userpositionorg;
import com.wcs.common.model.Userrole;

@Stateless
public class UserService implements Serializable {

	private static final long serialVersionUID = -4531023608569097125L;

	@PersistenceContext
	public EntityManager em;

	@SuppressWarnings("unchecked")
	public List<PVo> getAllP(PFormItemsVo pfiv) {
		StringBuffer sb = new StringBuffer();
		sb.append("select p from P p where NOT EXISTS(select u.pid from Usermstr u where u.pid=p.id)");

		if (pfiv != null) {
			if (pfiv.getNachn() != null && !"".equals(pfiv.getNachn())) {
				sb.append(" and p.nachn like '%" + pfiv.getNachn().trim()
						+ "%'");
			}
			if (pfiv.getEmail() != null && !"".equals(pfiv.getEmail())) {
				sb.append(" and p.email like '%" + pfiv.getEmail().trim()
						+ "%'");
			}
		}

		sb.append(" and p.defunctInd='N' order by p.nachn");
		String sql = sb.toString();

		List<P> list = this.em.createQuery(sql).getResultList();
		List<PVo> pVoList = new ArrayList<PVo>();
		PVo pVo = null;
		for (P p : list) {
			pVo = new PVo();
			pVo.setId(p.getId());
			pVo.setP(p);
			pVo.setO(getO(pVo.getP().getBukrs()));
			pVoList.add(pVo);
		}
		return pVoList;
	}

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
		if (ufiv.getCompanymstrId() != null
				&& !"".equals(ufiv.getCompanymstrId())) {
			sb.append(" and EXISTS(select uc.usermstr.id from Usercompany uc where u.id=uc.usermstr.id and uc.companymstr.id="
					+ ufiv.getCompanymstrId() + " and uc.defunctInd='N')");
		}
		if (ufiv.getPositionId() != null && !"".equals(ufiv.getPositionId())) {
			sb.append(" and EXISTS(select upo.usermstr.id from Userpositionorg upo where u.id=upo.usermstr.id and upo.positionorg.id="
					+ ufiv.getPositionId() + " and upo.defunctInd='N')");
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
			uv.setP(getP(u.getPid()));
			uv.setO(getO(uv.getP().getBukrs()));
			listUsermstrVo.add(uv);
		}
		return listUsermstrVo;
	}

	@SuppressWarnings("unchecked")
	public List<PositionorgVo> getAllPositionorg(String positionName) {
		StringBuffer sb = new StringBuffer();
		sb.append("select po from Positionorg po where 1=1");
		if (positionName != null && !"".equals(positionName)) {
			sb.append(" and po.position.name like '%" + positionName.trim()
					+ "%'");
		}
		sb.append(" and po.defunctInd='N'");
		String sql = sb.toString();
		List<Positionorg> list = this.em.createQuery(sql).getResultList();
		List<PositionorgVo> listPositionVo = new ArrayList<PositionorgVo>();
		PositionorgVo pv = null;
		for (Positionorg po : list) {
			pv = new PositionorgVo();
			pv.setId(po.getId());
			pv.setPositionorg(po);
			pv.setO(getO(po.getOid()));
			listPositionVo.add(pv);
		}
		return listPositionVo;
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

	@SuppressWarnings("unchecked")
	public List<CompanymstrVo> getAllCompanymstr(
			CompanymstrFormItems companymstrFormItems) {
		StringBuffer sb = new StringBuffer();
		sb.append("select c from Companymstr c where EXISTS(select o.id from O o where o.id=c.oid");
		if (companymstrFormItems.getStext() != null
				&& !"".equals(companymstrFormItems.getStext())) {
			sb.append(" and o.stext like '%"
					+ companymstrFormItems.getStext().trim() + "%'");
		}
		if (companymstrFormItems.getBukrs() != null
				&& !"".equals(companymstrFormItems.getBukrs())) {
			sb.append(" and o.bukrs like '%"
					+ companymstrFormItems.getBukrs().trim() + "%'");
		}
		sb.append(") and c.defunctInd='N' order by c.oid");

		String sql = sb.toString();
		List<Companymstr> list = this.em.createQuery(sql).getResultList();
		List<CompanymstrVo> companymstrVoList = new ArrayList<CompanymstrVo>();
		CompanymstrVo cv = null;
		for (Companymstr c : list) {
			cv = new CompanymstrVo();
			cv.setId(c.getId());
			cv.setCompanymstr(c);
			cv.setO(this.getO(c.getOid()));
			companymstrVoList.add(cv);
		}
		return companymstrVoList;
	}

	public P getP(long id) {
		return this.em.find(P.class, id);
	}

	public O getO(long id) {
		return this.em.find(O.class, id);
	}

	public O getO(String bukrs) {
		String sql = "select o from O o where o.bukrs = :bukrs";
		Query q = em.createQuery(sql);
		q.setParameter("bukrs", bukrs);
		return (O) q.getSingleResult();
	}

	public Taxauthority getTaxauthority(long id) {
		return this.em.find(Taxauthority.class, id);
	}

	public int getUserCount(String adAccount) {
		int num = 0;
		String sql = "select u from Usermstr u where u.adAccount = :adAccount";
		Query q = em.createQuery(sql);
		q.setParameter("adAccount", adAccount.trim());
		num = q.getResultList().size();
		return num;
	}

	public boolean saveUser(P p, Usermstr u, String createdBy) {
		boolean b = false;
		try {
			Usermstr um = new Usermstr();
			um.setPid(p.getId());

			um.setAdAccount(u.getAdAccount().trim());

			um.setIdentityType(u.getIdentityType());
			um.setIdtentityId(u.getIdtentityId().trim());

			um.setOnboardDate(u.getOnboardDate());
			um.setBirthday(u.getBirthday());
			um.setBackgroundInfo(u.getBackgroundInfo());

			um.setDefunctInd(u.getDefunctInd());

			um.setCreatedDatetime(new Date());
			um.setCreatedBy(createdBy);

			um.setUpdatedDatetime(new Date());
			um.setUpdatedBy(createdBy);

			this.em.persist(um);
			b = true;

		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public boolean updateUser(Usermstr usermstr) {
		boolean b = false;
		try {
			Usermstr u = this.em.find(Usermstr.class, usermstr.getId());
			u.setAdAccount(usermstr.getAdAccount());
			u.setIdentityType(usermstr.getIdentityType());
			u.setIdtentityId(usermstr.getIdtentityId());
			u.setOnboardDate(usermstr.getOnboardDate());
			u.setBirthday(usermstr.getBirthday());
			u.setBackgroundInfo(usermstr.getBackgroundInfo());
			u.setDefunctInd(usermstr.getDefunctInd());
			u.setUpdatedBy(usermstr.getUpdatedBy());
			u.setUpdatedDatetime(usermstr.getUpdatedDatetime());
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	public boolean saveUserPositionorg(PositionorgVo[] pvs, Usermstr u,
			String userName) {
		boolean b = false;
		try {

			StringBuffer sb = new StringBuffer();
			sb.append("select upo from Userpositionorg upo where upo.usermstr.id="
					+ u.getId());
			String sql = sb.toString();
			List<Userpositionorg> existUserpositionorgList = this.em
					.createQuery(sql).getResultList();

			List<Userpositionorg> selectedUserpositionorgList = new ArrayList<Userpositionorg>();
			if (pvs != null && pvs.length != 0) {
				for (PositionorgVo pv : pvs) {
					selectedUserpositionorgList.add(pv.getUserpositionorg());
				}
			}

			if (selectedUserpositionorgList.size() != 0) {
				boolean bb = false;
				for (Userpositionorg userpositionorg1 : selectedUserpositionorgList) {
					bb = false;
					if (existUserpositionorgList.size() != 0) {
						for (Userpositionorg userpositionorg2 : existUserpositionorgList) {
							if (userpositionorg1
									.getPositionorg()
									.getId()
									.compareTo(
											userpositionorg2.getPositionorg()
													.getId()) == 0) {
								bb = true;
								break;
							}
						}
					}
					if (!bb) {
						Userpositionorg userpositionorg = new Userpositionorg();
						userpositionorg.setUsermstr(u);
						userpositionorg.setPositionorg(userpositionorg1
								.getPositionorg());
						userpositionorg.setDefunctInd("N");
						userpositionorg.setCreatedBy(userName);
						userpositionorg.setCreatedDatetime(new Date());
						userpositionorg.setUpdatedBy(userName);
						userpositionorg.setUpdatedDatetime(new Date());
						this.em.persist(userpositionorg);
					}
				}
			}

			if (existUserpositionorgList.size() != 0) {
				boolean bb = false;
				Userpositionorg upo = null;
				for (Userpositionorg userpositionorg1 : existUserpositionorgList) {
					bb = false;
					if (selectedUserpositionorgList.size() != 0) {
						for (Userpositionorg userpositionorg2 : selectedUserpositionorgList) {
							if (userpositionorg1
									.getPositionorg()
									.getId()
									.compareTo(
											userpositionorg2.getPositionorg()
													.getId()) == 0) {
								bb = true;
								break;
							}
						}
					}
					upo = this.em.find(Userpositionorg.class,
							userpositionorg1.getId());
					if (bb) {
						upo.setDefunctInd("N");
					} else {
						upo.setDefunctInd("Y");
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

	public boolean saveUserCompany(List<Usercompany> existUsercompanyList,
			CompanymstrVo[] cvs, Usermstr u, String userName) {
		boolean b = false;
		try {
			Usercompany uc = null;
			List<Usercompany> selectedUsercompanyList = new ArrayList<Usercompany>();
			if (cvs != null && cvs.length != 0) {
				for (CompanymstrVo cv : cvs) {
					uc = cv.getUsercompany();
					selectedUsercompanyList.add(uc);
				}
			}
			if (selectedUsercompanyList.size() != 0) {
				boolean bb = false;
				for (Usercompany uc1 : selectedUsercompanyList) {
					bb = false;
					if (existUsercompanyList != null
							&& existUsercompanyList.size() != 0) {
						for (Usercompany uc2 : existUsercompanyList) {
							if (uc1.getCompanymstr().getId()
									.compareTo(uc2.getCompanymstr().getId()) == 0) {
								bb = true;
								break;
							}
						}
					}
					if (!bb) {
						uc = new Usercompany();
						uc.setUsermstr(u);
						uc.setCompanymstr(uc1.getCompanymstr());
						uc.setDefunctInd("N");
						uc.setCreatedBy(userName);
						uc.setCreatedDatetime(new Date());
						uc.setUpdatedBy(userName);
						uc.setUpdatedDatetime(new Date());
						this.em.persist(uc);
					}
				}
			}
			if (existUsercompanyList != null
					&& existUsercompanyList.size() != 0) {
				boolean bb = false;
				for (Usercompany uc1 : existUsercompanyList) {
					bb = false;
					if (selectedUsercompanyList.size() != 0) {
						for (Usercompany uc2 : selectedUsercompanyList) {
							if (uc1.getCompanymstr().getId()
									.compareTo(uc2.getCompanymstr().getId()) == 0) {
								bb = true;
								break;
							}
						}
					}
					uc = this.em.find(Usercompany.class, uc1.getId());
					if (bb) {
						uc.setDefunctInd("N");
					} else {
						uc.setDefunctInd("Y");
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
