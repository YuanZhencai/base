package com.wcs.base.security.controller.vo;

import java.io.Serializable;

import com.wcs.base.security.model.Usermstr;
import com.wcs.common.controller.helper.IdModel;
import com.wcs.common.model.O;
import com.wcs.common.model.P;

public class UsermstrVo extends IdModel implements Serializable {
	private static final long serialVersionUID = 7922530371478759792L;
	private Usermstr usermstr;
	private P p;
	private O o;

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

	public O getO() {
		return o;
	}

	public void setO(O o) {
		this.o = o;
	}

}
