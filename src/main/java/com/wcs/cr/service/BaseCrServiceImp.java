package com.wcs.cr.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.wcs.cr.controller.vo.BaseCrVo;

/**
 * @author chengchao
 * 
 */
@Stateless
public class BaseCrServiceImp{

	public List<BaseCrVo> getBaseCrVoList() throws Exception {
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
//		String crConfigPath = request.getRealPath("/")
//				+ "/WEB-INF/classes/CRConfig.xml";
//		File file = new File(crConfigPath);
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		Document doc = db.parse(file);
//		doc.getDocumentElement().normalize();
//		NodeList nodeLst = doc.getElementsByTagName("reportlocation");
//		//System.out.println("nodeLst.getLength():" + nodeLst.getLength());
//		System.out.println("reportlocation value:"+nodeLst.item(0).getNodeName()+nodeLst.item(0).getFirstChild().getNodeValue());
//		String crPath = nodeLst.item(0).getFirstChild().getNodeValue();
//		System.out.println(file.toURI());
//		System.out.println(request.getRealPath(crPath));
//		String[] crPathStr = crPath.split("/");
//		
//		int back = 0;
//		for (int i = 0; i < crPathStr.length; i++) {
//			if (crPathStr[i].matches("\\.\\.")) {
//				back++;
//			}
//
//		}
		
//		SAXParserFactory factory = SAXParserFactory.newInstance();
//		SAXParser saxParser = factory.newSAXParser();
//		saxParser.parse(crConfigPath, arg1);
//		BaseCrVo baseCrVo1 = new BaseCrVo();
		//ReportClientDocument reportClientDoc = new ReportClientDocument();
		//reportClientDoc.open("cr_db2_sample.rpt", 0);
		//baseCrVo1.setReportClientDoc(reportClientDoc);
//		baseCrVo1.setName("cr_db2_sample.rpt");

//		BaseCrVo baseCrVo2 = new BaseCrVo();
		//ReportClientDocument reportClientDoc2 = new ReportClientDocument();
		//reportClientDoc2.open("CrystalReport1.rpt", 0);
		//baseCrVo2.setReportClientDoc(reportClientDoc2);
//		baseCrVo2.setName("CrystalReport1.rpt");

//		List<BaseCrVo> returnList = new ArrayList<BaseCrVo>();
//		returnList.add(baseCrVo1);
//		returnList.add(baseCrVo2);
		
		List<String> nameList = new ArrayList<String>();
		List<BaseCrVo> returnList = new ArrayList<BaseCrVo>();
		nameList.add("Consolidated Balance Sheet.rpt");
		nameList.add("cr_db2_sample.rpt");
		nameList.add("CrystalReport1.rpt");
		nameList.add("Customer Profile Report.rpt");
		nameList.add("Custom Functions.rpt");
		nameList.add("HelloWorldRpt.rpt");
		nameList.add("Statement of Account.rpt");
		nameList.add("竞品市场报表.rpt");
		for (String name : nameList) {
			BaseCrVo baseCrVo = new BaseCrVo();
			baseCrVo.setName(name);
			returnList.add(baseCrVo);
		}
		return returnList;
	}

	public BaseCrVo fetchBaseCrVo(String reportName) throws Exception {
		BaseCrVo baseCrVo1 = new BaseCrVo();
//		ReportClientDocument reportClientDoc = new ReportClientDocument();
//		reportClientDoc.open(reportName, 0);
//		baseCrVo1.setReportClientDoc(reportClientDoc);
		baseCrVo1.setName(reportName);
		return baseCrVo1;
	}
	
	
}
