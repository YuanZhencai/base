package com.wcs.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.wcs.base.security.controller.vo.UsermstrVo;
import com.wcs.base.security.model.UserRole;
import com.wcs.base.security.model.Usermstr;
import com.wcs.base.security.service.LoginService;
import com.wcs.base.security.service.UserService;
import org.primefaces.model.LazyDataModel;

import com.wcs.common.controller.helper.PageModel;
import com.wcs.base.security.controller.vo.RoleVo;
import com.wcs.common.controller.vo.UsermstrFormItemsVo;
import com.wcs.base.security.model.master.P;

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

	private P p = new P();
	private Map<String, Long> roleVos;
	private List<Long> selectedRoleVos;
	private List<RoleVo> rolemstrList = new ArrayList<RoleVo>();

	private String method;
	private String bukrsName;
	private String tempAdAccount;

	public UserBean() {

	}

	@PostConstruct
	public void init() {
		rolemstrList = this.userService.getAllRoleVo();
		search();
	}

	public void search() {
		usermstrVos = userService.getAllUsermstrVo(usermstrFormItemsVo);
		lazyUsermstrVoModel = new PageModel<UsermstrVo>(usermstrVos, false);
	}

	public void reset() {
		usermstrFormItemsVo = new UsermstrFormItemsVo();
	}
	
	public void updateUser() {
		this.usermstr = selectedUsermstrVo.getUsermstr();
		this.p = selectedUsermstrVo.getP();

		this.bukrsName = selectedUsermstrVo.getO().getStext();
		this.tempAdAccount = usermstr.getAdAccount();
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

		List<UserRole> userroleList = this.usermstr.getUserRoles();
        UserRole ur = null;
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

	public List<RoleVo> getRolemstrList() {
		return rolemstrList;
	}

	public void setRolemstrList(List<RoleVo> rolemstrList) {
		this.rolemstrList = rolemstrList;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getBukrsName() {
		return bukrsName;
	}

	public void setBukrsName(String bukrsName) {
		this.bukrsName = bukrsName;
	}

	public String getTempAdAccount() {
		return tempAdAccount;
	}

	public void setTempAdAccount(String tempAdAccount) {
		this.tempAdAccount = tempAdAccount;
	}

}
