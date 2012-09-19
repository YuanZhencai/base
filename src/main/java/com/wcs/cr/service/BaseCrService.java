package com.wcs.cr.service;

import java.util.List;

import javax.ejb.Local;

import com.wcs.cr.controller.vo.BaseCrVo;

@Local
public interface BaseCrService {

	//get report list with no cr source
	public List<BaseCrVo> getBaseCrVoList() throws Exception;
	//fetch a report by name with cr source
	public BaseCrVo fetchBaseCrVo(String reportName)throws Exception;
	
}
