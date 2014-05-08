package com.wcs.demo.goc.strategy.report.jasper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wcs.common.model.Resourcemstr;

public class ResourceSummery extends AbstractSummery implements SummeryInterface {

	private static String TEMPLATE_NAME = "D:\\workspace\\base\\src\\main\\webapp\\jasperreports\\Resource.jrxml";
	private static String REPORT_NAME = "Resource";
	
	private Collection<Resourcemstr> data = null;
	
	
	public ResourceSummery(Collection<Resourcemstr> data) {
		System.out.println("ResourceSummery.ResourceSummery()");
		this.data = data;
	}
	
	@Override
	public void summery() {
		OutputStream os = null;
		try {
			String name = REPORT_NAME + new Date().getTime() + ".xls";
			os = new FileOutputStream(name);
			Map<String, Object> parameters = new HashMap<String, Object>();
			createXlsReport(TEMPLATE_NAME, data, os , parameters);
			System.out.println("ResourceSummery.summery() " + name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

}
