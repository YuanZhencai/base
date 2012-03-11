package com.wcs.common.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;

import com.wcs.base.service.LoginService;
import com.wcs.common.controller.helper.PageModel;
import com.wcs.common.controller.vo.CompanymstrFormItems;
import com.wcs.common.controller.vo.CompanymstrVo;
import com.wcs.common.controller.vo.PFormItemsVo;
import com.wcs.common.controller.vo.PVo;
import com.wcs.common.controller.vo.PositionorgVo;
import com.wcs.common.controller.vo.RoleVo;
import com.wcs.common.controller.vo.UsermstrFormItemsVo;
import com.wcs.common.controller.vo.UsermstrVo;
import com.wcs.common.model.P;
import com.wcs.common.model.Usercompany;
import com.wcs.common.model.Usermstr;
import com.wcs.common.model.Userpositionorg;
import com.wcs.common.model.Userrole;
import com.wcs.common.service.UserService;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean {

	@EJB
	UserService userService;
	@EJB
	LoginService loginService;

	private UsermstrFormItemsVo usermstrFormItemsVo = new UsermstrFormItemsVo();
	private Usermstr usermstr = new Usermstr();
	private UsermstrVo selectedUsermstrVo;
	private List<UsermstrVo> usermstrVos;
	private UsermstrVo[] selectedUsermstrVos;
	private LazyDataModel<UsermstrVo> lazyUsermstrVoModel;

	private PFormItemsVo pFormItemsVo = new PFormItemsVo();
	private P p = new P();
	private PVo selectedPVo;
	private List<PVo> pVos;
	private PVo[] selectedPVos;
	private LazyDataModel<PVo> lazyPVoModel;

	private LazyDataModel<PositionorgVo> lazyUserPositionorgVoModel;
	private PositionorgVo[] selectedUserPositionorgVos;

	private LazyDataModel<PositionorgVo> lazyPositionorgVoModel;
	private PositionorgVo[] selectedPositionorgVos;
	private Set<PositionorgVo> selectedPositionorgVosSet = new HashSet<PositionorgVo>();

	private Map<String, Long> roleVos;
	private List<Long> selectedRoleVos;

	private List<CompanymstrVo> companymstrList = new ArrayList<CompanymstrVo>();
	private List<PositionorgVo> positionList = new ArrayList<PositionorgVo>();
	private List<RoleVo> rolemstrList = new ArrayList<RoleVo>();

	private LazyDataModel<CompanymstrVo> lazyUserCompanymstrVoModel;
	private CompanymstrVo[] selectedUserCompanymstrVos;

	private LazyDataModel<CompanymstrVo> lazyCompanymstrVoModel;
	private CompanymstrVo[] selectedCompanymstrVos;
	private CompanymstrFormItems companymstrFormItems = new CompanymstrFormItems();
	private Set<CompanymstrVo> selectedCompanymstrVosSet = new HashSet<CompanymstrVo>();

	// private final String RESULT_URL = "/tih/system/user/index.xhtml";

	private Date onboardDate;
	private String positionName;
	private String method;
	// private String userName = "admin";
	private String bukrsName;
	private String tempAdAccount;

	private boolean showBtn1;
	private boolean showBtn2;
	private boolean showBtn3;

	public UserBean() {

	}

	@PostConstruct
	public void init() {
		companymstrList = this.userService
				.getAllCompanymstr(new CompanymstrFormItems());
		positionList = this.userService.getAllPositionorg("");
		rolemstrList = this.userService.getAllRoleVo();
		search();
	}

	public void search() {
		usermstrVos = userService.getAllUsermstrVo(usermstrFormItemsVo);
		lazyUsermstrVoModel = new PageModel<UsermstrVo>(usermstrVos, false);
		// return RESULT_URL;
	}

	public void onRowSelectShowBtn1(SelectEvent event) {
		showBtn1 = false;
	}

	public void onRowSelectShowBtn2(SelectEvent event) {
		PositionorgVo pv = (PositionorgVo) event.getObject();
		selectedPositionorgVosSet.add(pv);

		if (selectedPositionorgVosSet.size() != 0) {
			showBtn2 = false;
		} else {
			showBtn2 = true;
		}
	}

	public void onRowUnselectShowBtn2(UnselectEvent event) {
		PositionorgVo pv = (PositionorgVo) event.getObject();
		selectedPositionorgVosSet.remove(pv);

		if (selectedPositionorgVosSet.size() != 0) {
			showBtn2 = false;
		} else {
			showBtn2 = true;
		}
	}

	public void onRowSelectShowBtn3(SelectEvent event) {
		CompanymstrVo cv = (CompanymstrVo) event.getObject();
		selectedCompanymstrVosSet.add(cv);

		if (selectedCompanymstrVosSet.size() != 0) {
			showBtn3 = false;
		} else {
			showBtn3 = true;
		}
	}

	public void onRowUnselectShowBtn3(UnselectEvent event) {
		CompanymstrVo cv = (CompanymstrVo) event.getObject();
		selectedCompanymstrVosSet.remove(cv);

		if (selectedCompanymstrVosSet.size() != 0) {
			showBtn3 = false;
		} else {
			showBtn3 = true;
		}
	}

	public void reset() {
		usermstrFormItemsVo = new UsermstrFormItemsVo();
	}

	public void resetP() {
		pFormItemsVo = new PFormItemsVo();
	}

	public void addUser() {
		showBtn1 = true;
		lazyPVoModel = null;
		selectedPVo = null;
	}

	public void searchP() {
		showBtn1 = true;
		pVos = userService.getAllP(pFormItemsVo);
		lazyPVoModel = new PageModel<PVo>(pVos, false);
		selectedPVo = null;
	}

	public void saveUser() {

		this.usermstr = new Usermstr();
		usermstr.setDefunctInd("N");
		this.p = selectedPVo.getP();
		usermstr.setIdentityType("身份证");
		usermstr.setIdtentityId(p.getIcnum());

		this.bukrsName = selectedPVo.getO().getStext();

		onboardDate = new Date();
		usermstr.setBirthday(new Date());

	}

	public void updateUser() {
		this.usermstr = selectedUsermstrVo.getUsermstr();
		this.p = selectedUsermstrVo.getP();

		this.bukrsName = selectedUsermstrVo.getO().getStext();
		this.tempAdAccount = usermstr.getAdAccount();
		onboardDate = this.usermstr.getOnboardDate();

	}

	public void saveUpdateUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		if ("insert".equals(method)) {
			usermstr.setOnboardDate(new Timestamp(onboardDate.getTime()));
			boolean b = userService.saveUser(p, usermstr,
					loginService.getCurrentUserName());
			if (b) {
				context.addMessage(null, new FacesMessage("成功", "添加用户信息成功"));
			} else {
				context.addMessage(null, new FacesMessage("失败", "添加用户信息失败"));
			}
		} else if ("update".equals(method)) {
			usermstr.setOnboardDate(new Timestamp(onboardDate.getTime()));
			usermstr.setUpdatedBy(loginService.getCurrentUserName());
			usermstr.setUpdatedDatetime(new Timestamp(onboardDate.getTime()));
			boolean b = userService.updateUser(usermstr);
			if (b) {
				context.addMessage(null, new FacesMessage("成功", "更新用户信息成功"));
			} else {
				context.addMessage(null, new FacesMessage("失败", "更新用户信息失败"));
			}
		}
		search();
		RequestContext.getCurrentInstance().addCallbackParam("issucc", "yes");
	}

	public void valid(FacesContext context, UIComponent component,
			java.lang.Object value) throws ValidatorException {
		if ("adAccount".equals(component.getId())) {
			if (value == null || "".equals(value.toString().trim())) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "用户帐号：", "不允许为空或空格"));
			} else if (value.toString().length() > 50) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "用户帐号：", "长度不能超过50个字符"));
			} else {
				if ("update".equals(this.method)) {
					if (!value.toString().equals(this.tempAdAccount)) {
						int num = this.userService.getUserCount(value
								.toString().trim());
						if (num != 0) {
							throw new ValidatorException(new FacesMessage(
									FacesMessage.SEVERITY_ERROR, "用户帐号：",
									"帐号已经存在，请重新输入"));
						}
					}
				} else if ("insert".equals(this.method)) {
					int num = this.userService.getUserCount(value.toString()
							.trim());
					if (num != 0) {
						throw new ValidatorException(new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "用户帐号：",
								"帐号已经存在，请重新输入"));
					}
				}
			}
		}
		if ("idtentityId".equals(component.getId())) {
			if (value == null || "".equals(value.toString().trim())) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "证件号：", "不允许为空或空格"));
			} else if (value.toString().length() > 50) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "证件号：", "长度不能超过50个字符"));
			}
		}

	}

	public void userPositionorg() {

		selectedUserPositionorgVos = null;
		lazyUserPositionorgVoModel = null;

		this.usermstr = selectedUsermstrVo.getUsermstr();
		this.p = selectedUsermstrVo.getP();

		if (this.usermstr.getUserpositionorgs().size() != 0) {

			List<Userpositionorg> allUserpositionorgList = this.usermstr
					.getUserpositionorgs();
			Userpositionorg up = null;
			List<Userpositionorg> userpositionorgList = new ArrayList<Userpositionorg>();
			if (allUserpositionorgList.size() != 0) {
				for (int i = 0; i < allUserpositionorgList.size(); i++) {
					up = allUserpositionorgList.get(i);
					if (!"Y".equals(up.getDefunctInd().trim())) {
						userpositionorgList.add(up);
					}
				}
			}

			int size = userpositionorgList.size();

			if (size != 0) {

				List<PositionorgVo> positionVoList = new ArrayList<PositionorgVo>();

				if (selectedUserPositionorgVos == null
						|| selectedUserPositionorgVos.length == 0) {
					selectedUserPositionorgVos = new PositionorgVo[size];
				}

				PositionorgVo positionorgVo = null;
				for (int i = 0; i < size; i++) {
					up = userpositionorgList.get(i);
					positionorgVo = new PositionorgVo();
					positionorgVo.setId(up.getId());
					positionorgVo.setUserpositionorg(up);
					positionorgVo.setO(userService.getO(up.getPositionorg()
							.getOid()));
					positionVoList.add(positionorgVo);
					selectedUserPositionorgVos[i] = positionorgVo;
				}

				lazyUserPositionorgVoModel = new PageModel<PositionorgVo>(
						positionVoList, false);
			} else {
				selectedUserPositionorgVos = null;
				lazyUserPositionorgVoModel = null;
			}
		} else {
			selectedUserPositionorgVos = null;
			lazyUserPositionorgVoModel = null;
		}

	}

	public void saveUserPositionorg() {

		FacesContext context = FacesContext.getCurrentInstance();
		boolean b = this.userService.saveUserPositionorg(
				selectedUserPositionorgVos,
				this.selectedUsermstrVo.getUsermstr(),
				loginService.getCurrentUserName());
		if (b) {
			context.addMessage(null, new FacesMessage("成功", "用户岗位分配成功"));
		} else {
			context.addMessage(null, new FacesMessage("失败", "用户岗位分配失败"));
		}
		search();

	}

	public void addPositionorg() {

		showBtn2 = true;
		selectedPositionorgVos = null;
		lazyPositionorgVoModel = null;

	}

	public void searchPositionorg() {

		showBtn2 = true;
		List<PositionorgVo> listPositionVo = this.userService
				.getAllPositionorg(positionName);
		lazyPositionorgVoModel = new PageModel<PositionorgVo>(listPositionVo,
				false);
		selectedPositionorgVos = null;

	}

	public void resetPositionorg() {
		this.positionName = "";
	}

	public void saveAddPositionorg() {

		List<PositionorgVo> list = new ArrayList<PositionorgVo>();

		if (selectedUserPositionorgVos != null
				&& selectedUserPositionorgVos.length != 0) {
			for (PositionorgVo pv : selectedUserPositionorgVos) {
				list.add(pv);
			}
		}

		if (selectedPositionorgVos != null
				&& selectedPositionorgVos.length != 0) {
			boolean b = false;
			Userpositionorg upo = null;

			for (PositionorgVo pv : selectedPositionorgVos) {
				b = false;
				for (PositionorgVo positionVo : list) {
					if (pv.getPositionorg()
							.getId()
							.compareTo(
									positionVo.getUserpositionorg()
											.getPositionorg().getId()) == 0) {
						b = true;
						break;
					}
				}
				if (!b) {
					upo = new Userpositionorg();
					upo.setPositionorg(pv.getPositionorg());
					upo.setUsermstr(selectedUsermstrVo.getUsermstr());
					pv.setUserpositionorg(upo);
					list.add(pv);
				}
			}
		}

		if (list.size() != 0) {
			selectedUserPositionorgVos = new PositionorgVo[list.size()];
			for (int i = 0; i < list.size(); i++) {
				selectedUserPositionorgVos[i] = list.get(i);
			}
			lazyUserPositionorgVoModel = new PageModel<PositionorgVo>(list,
					false);
		} else {
			selectedUserPositionorgVos = null;
			lazyUserPositionorgVoModel = null;
		}

	}

	public void userRole() {

		roleVos = new HashMap<String, Long>();
		selectedRoleVos = new ArrayList<Long>();

		this.usermstr = selectedUsermstrVo.getUsermstr();
		this.p = selectedUsermstrVo.getP();

		List<RoleVo> rolemstrList = this.userService.getAllRoleVo();
		if (rolemstrList.size() != 0) {
			for (RoleVo rv : rolemstrList) {
				roleVos.put(rv.getRolemstr().getName(), rv.getRolemstr()
						.getId());
			}
		}

		List<Userrole> userroleList = this.usermstr.getUserroles();
		Userrole ur = null;
		if (userroleList != null && userroleList.size() != 0) {
			for (int i = 0; i < userroleList.size(); i++) {
				ur = userroleList.get(i);
				if ("N".equals(ur.getDefunctInd().trim())) {
					selectedRoleVos.add(ur.getRolemstr().getId());
				}
			}
		}

	}

	public void saveUserRole() {

		FacesContext context = FacesContext.getCurrentInstance();
		boolean b = this.userService.saveUserRole(selectedRoleVos,
				this.selectedUsermstrVo.getUsermstr(),
				loginService.getCurrentUserName());
		if (b) {
			context.addMessage(null, new FacesMessage("成功", "用户角色分配成功"));
		} else {
			context.addMessage(null, new FacesMessage("失败", "用户角色分配失败"));
		}
		search();

	}

	public void userCompany() {

		this.lazyUserCompanymstrVoModel = null;
		this.selectedUserCompanymstrVos = null;

		this.usermstr = selectedUsermstrVo.getUsermstr();
		this.p = selectedUsermstrVo.getP();

		if (this.usermstr.getUsercompanies().size() != 0) {
			List<Usercompany> allExistUsercompanyList = this.usermstr
					.getUsercompanies();
			List<Usercompany> existUsercompanyList = new ArrayList<Usercompany>();
			if (allExistUsercompanyList.size() != 0) {
				for (Usercompany uc : allExistUsercompanyList) {
					if ("N".equals(uc.getDefunctInd().trim())) {
						existUsercompanyList.add(uc);
					}
				}
			}

			if (existUsercompanyList.size() != 0) {
				selectedUserCompanymstrVos = new CompanymstrVo[existUsercompanyList
						.size()];
				List<CompanymstrVo> usercompanyList = new ArrayList<CompanymstrVo>();
				CompanymstrVo cv = null;
				Usercompany uc = null;
				for (int i = 0; i < existUsercompanyList.size(); i++) {
					uc = existUsercompanyList.get(i);
					cv = new CompanymstrVo();
					cv.setId(uc.getId());
					cv.setUsercompany(uc);
					cv.setO(this.userService.getO(uc.getCompanymstr().getOid()));
					selectedUserCompanymstrVos[i] = cv;
					usercompanyList.add(cv);
				}
				lazyUserCompanymstrVoModel = new PageModel<CompanymstrVo>(
						usercompanyList, false);
			} else {
				this.lazyUserCompanymstrVoModel = null;
				this.selectedUserCompanymstrVos = null;
			}
		} else {
			this.lazyUserCompanymstrVoModel = null;
			this.selectedUserCompanymstrVos = null;
		}
	}

	public void saveUserCompany() {

		FacesContext context = FacesContext.getCurrentInstance();
		boolean b = this.userService.saveUserCompany(
				this.usermstr.getUsercompanies(), selectedUserCompanymstrVos,
				this.usermstr, loginService.getCurrentUserName());
		if (b) {
			context.addMessage(null, new FacesMessage("成功", "用户服务公司分配成功"));
		} else {
			context.addMessage(null, new FacesMessage("失败", "用户服务公司分配失败"));
		}
		this.search();

	}

	public void addUserCompany() {

		showBtn3 = true;
		this.selectedCompanymstrVos = null;
		this.lazyCompanymstrVoModel = null;

	}

	public void searchCompany() {

		showBtn3 = true;
		List<CompanymstrVo> companymstrVoList = this.userService
				.getAllCompanymstr(companymstrFormItems);
		lazyCompanymstrVoModel = new PageModel<CompanymstrVo>(
				companymstrVoList, false);
		this.selectedCompanymstrVos = null;

	}

	public void saveAddUserCompany() {

		List<CompanymstrVo> companymstrVoList = new ArrayList<CompanymstrVo>();

		if (selectedUserCompanymstrVos != null
				&& selectedUserCompanymstrVos.length != 0) {
			for (CompanymstrVo cv : selectedUserCompanymstrVos) {
				companymstrVoList.add(cv);
			}
		}

		if (selectedCompanymstrVos != null
				&& selectedCompanymstrVos.length != 0) {
			boolean bb = false;
			Usercompany uc = null;
			for (CompanymstrVo cv1 : selectedCompanymstrVos) {
				bb = false;
				if (selectedUserCompanymstrVos != null
						&& selectedUserCompanymstrVos.length != 0) {
					for (CompanymstrVo cv2 : selectedUserCompanymstrVos) {
						if (cv1.getCompanymstr()
								.getId()
								.compareTo(
										cv2.getUsercompany().getCompanymstr()
												.getId()) == 0) {
							bb = true;
							break;
						}
					}
				}

				if (!bb) {
					uc = new Usercompany();
					uc.setUsermstr(this.usermstr);
					uc.setCompanymstr(cv1.getCompanymstr());
					cv1.setUsercompany(uc);
					companymstrVoList.add(cv1);
				}
			}
		}

		if (companymstrVoList.size() != 0) {
			this.selectedUserCompanymstrVos = new CompanymstrVo[companymstrVoList
					.size()];
			CompanymstrVo cv = null;
			for (int i = 0; i < companymstrVoList.size(); i++) {
				cv = companymstrVoList.get(i);
				selectedUserCompanymstrVos[i] = cv;
			}
			lazyUserCompanymstrVoModel = new PageModel<CompanymstrVo>(
					companymstrVoList, false);
		} else {
			selectedUserCompanymstrVos = null;
			lazyUserCompanymstrVoModel = null;
		}

	}

	public void resetCompany() {
		companymstrFormItems = new CompanymstrFormItems();
	}

	public LazyDataModel<CompanymstrVo> getLazyUserCompanymstrVoModel() {
		return lazyUserCompanymstrVoModel;
	}

	public void setLazyUserCompanymstrVoModel(
			LazyDataModel<CompanymstrVo> lazyUserCompanymstrVoModel) {
		this.lazyUserCompanymstrVoModel = lazyUserCompanymstrVoModel;
	}

	public CompanymstrVo[] getSelectedUserCompanymstrVos() {
		return selectedUserCompanymstrVos;
	}

	public void setSelectedUserCompanymstrVos(
			CompanymstrVo[] selectedUserCompanymstrVos) {
		this.selectedUserCompanymstrVos = selectedUserCompanymstrVos;
	}

	public Map<String, Long> getRoleVos() {
		return roleVos;
	}

	public void setRoleVos(Map<String, Long> roleVos) {
		this.roleVos = roleVos;
	}

	public List<Long> getSelectedRoleVos() {
		return selectedRoleVos;
	}

	public void setSelectedRoleVos(List<Long> selectedRoleVos) {
		this.selectedRoleVos = selectedRoleVos;
	}

	public PVo getSelectedPVo() {
		return selectedPVo;
	}

	public void setSelectedPVo(PVo selectedPVo) {
		this.selectedPVo = selectedPVo;
	}

	public List<PVo> getpVos() {
		return pVos;
	}

	public void setpVos(List<PVo> pVos) {
		this.pVos = pVos;
	}

	public PVo[] getSelectedPVos() {
		return selectedPVos;
	}

	public void setSelectedPVos(PVo[] selectedPVos) {
		this.selectedPVos = selectedPVos;
	}

	public LazyDataModel<PVo> getLazyPVoModel() {
		return lazyPVoModel;
	}

	public void setLazyPVoModel(LazyDataModel<PVo> lazyPVoModel) {
		this.lazyPVoModel = lazyPVoModel;
	}

	public UsermstrFormItemsVo getUsermstrFormItemsVo() {
		return usermstrFormItemsVo;
	}

	public void setUsermstrFormItemsVo(UsermstrFormItemsVo usermstrFormItemsVo) {
		this.usermstrFormItemsVo = usermstrFormItemsVo;
	}

	public List<UsermstrVo> getUsermstrVos() {
		return usermstrVos;
	}

	public void setUsermstrVos(List<UsermstrVo> usermstrVos) {
		this.usermstrVos = usermstrVos;
	}

	public Usermstr getUsermstr() {
		return usermstr;
	}

	public void setUsermstr(Usermstr usermstr) {
		this.usermstr = usermstr;
	}

	public P getP() {
		return p;
	}

	public void setP(P p) {
		this.p = p;
	}

	public UsermstrVo getSelectedUsermstrVo() {
		return selectedUsermstrVo;
	}

	public void setSelectedUsermstrVo(UsermstrVo selectedUsermstrVo) {
		this.selectedUsermstrVo = selectedUsermstrVo;
	}

	public UsermstrVo[] getSelectedUsermstrVos() {
		return selectedUsermstrVos;
	}

	public void setSelectedUsermstrVos(UsermstrVo[] selectedUsermstrVos) {
		this.selectedUsermstrVos = selectedUsermstrVos;
	}

	public LazyDataModel<UsermstrVo> getLazyUsermstrVoModel() {
		return lazyUsermstrVoModel;
	}

	public void setLazyUsermstrVoModel(
			LazyDataModel<UsermstrVo> lazyUsermstrVoModel) {
		this.lazyUsermstrVoModel = lazyUsermstrVoModel;
	}

	public List<CompanymstrVo> getCompanymstrList() {
		return companymstrList;
	}

	public void setCompanymstrList(List<CompanymstrVo> companymstrList) {
		this.companymstrList = companymstrList;
	}

	public List<PositionorgVo> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<PositionorgVo> positionList) {
		this.positionList = positionList;
	}

	public List<RoleVo> getRolemstrList() {
		return rolemstrList;
	}

	public void setRolemstrList(List<RoleVo> rolemstrList) {
		this.rolemstrList = rolemstrList;
	}

	public PFormItemsVo getpFormItemsVo() {
		return pFormItemsVo;
	}

	public void setpFormItemsVo(PFormItemsVo pFormItemsVo) {
		this.pFormItemsVo = pFormItemsVo;
	}

	public Date getOnboardDate() {
		return onboardDate;
	}

	public void setOnboardDate(Date onboardDate) {
		this.onboardDate = onboardDate;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public LazyDataModel<PositionorgVo> getLazyUserPositionorgVoModel() {
		return lazyUserPositionorgVoModel;
	}

	public void setLazyUserPositionorgVoModel(
			LazyDataModel<PositionorgVo> lazyUserPositionorgVoModel) {
		this.lazyUserPositionorgVoModel = lazyUserPositionorgVoModel;
	}

	public PositionorgVo[] getSelectedUserPositionorgVos() {
		return selectedUserPositionorgVos;
	}

	public void setSelectedUserPositionorgVos(
			PositionorgVo[] selectedUserPositionorgVos) {
		this.selectedUserPositionorgVos = selectedUserPositionorgVos;
	}

	public LazyDataModel<PositionorgVo> getLazyPositionorgVoModel() {
		return lazyPositionorgVoModel;
	}

	public void setLazyPositionorgVoModel(
			LazyDataModel<PositionorgVo> lazyPositionorgVoModel) {
		this.lazyPositionorgVoModel = lazyPositionorgVoModel;
	}

	public PositionorgVo[] getSelectedPositionorgVos() {
		return selectedPositionorgVos;
	}

	public void setSelectedPositionorgVos(PositionorgVo[] selectedPositionorgVos) {
		this.selectedPositionorgVos = selectedPositionorgVos;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public LazyDataModel<CompanymstrVo> getLazyCompanymstrVoModel() {
		return lazyCompanymstrVoModel;
	}

	public void setLazyCompanymstrVoModel(
			LazyDataModel<CompanymstrVo> lazyCompanymstrVoModel) {
		this.lazyCompanymstrVoModel = lazyCompanymstrVoModel;
	}

	public CompanymstrVo[] getSelectedCompanymstrVos() {
		return selectedCompanymstrVos;
	}

	public void setSelectedCompanymstrVos(CompanymstrVo[] selectedCompanymstrVos) {
		this.selectedCompanymstrVos = selectedCompanymstrVos;
	}

	public CompanymstrFormItems getCompanymstrFormItems() {
		return companymstrFormItems;
	}

	public void setCompanymstrFormItems(
			CompanymstrFormItems companymstrFormItems) {
		this.companymstrFormItems = companymstrFormItems;
	}

	public String getBukrsName() {
		return bukrsName;
	}

	public void setBukrsName(String bukrsName) {
		this.bukrsName = bukrsName;
	}

	public boolean isShowBtn1() {
		return showBtn1;
	}

	public void setShowBtn1(boolean showBtn1) {
		this.showBtn1 = showBtn1;
	}

	public boolean isShowBtn2() {
		return showBtn2;
	}

	public void setShowBtn2(boolean showBtn2) {
		this.showBtn2 = showBtn2;
	}

	public boolean isShowBtn3() {
		return showBtn3;
	}

	public void setShowBtn3(boolean showBtn3) {
		this.showBtn3 = showBtn3;
	}

	public String getTempAdAccount() {
		return tempAdAccount;
	}

	public void setTempAdAccount(String tempAdAccount) {
		this.tempAdAccount = tempAdAccount;
	}

}
