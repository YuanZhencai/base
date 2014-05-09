package com.wcs.demo.goc.strategy.report.jasper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wcs.common.model.Resourcemstr;

public class ResourceSummery extends AbstractSummery implements SummeryInterface {

	// 这边要注意模版文件的类型，如果是jasper文件的话要注意IReport的版本要和jar包的版本号对应
	private static String TEMPLATE_NAME = "/src/main/webapp/jasperreports/Resource.jasper";
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
			File file = new File(name);
			os = new FileOutputStream(file);
			Map<String, Object> parameters = new HashMap<String, Object>();
			createXlsReport(TEMPLATE_NAME, data, os, parameters);
			System.out.println("ResourceSummery.summery() " + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
