/** * AbstractSummery.java 
* Created on 2014年5月8日 下午5:51:47 
*/

package com.wcs.demo.goc.strategy.report.jasper;

import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: AbstractSummery.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public abstract class AbstractSummery {
	private static Logger logger = LoggerFactory.getLogger(AbstractSummery.class.getName());

	public static StringBuffer createXhtmlReport(String templateRealPath, Collection datas) {
		JRDataSource dataSource = new JRBeanCollectionDataSource(datas);
		return exportXhtmlReport(templateRealPath, dataSource, null);
	}

	private static StringBuffer exportXhtmlReport(String templateRealPath, JRDataSource dataSource, Map<String, Object> parameters) {
		JRXhtmlExporter exporter = new JRXhtmlExporter();
		StringBuffer sbuffer = new StringBuffer();
		exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		exportReport(templateRealPath, dataSource, parameters, exporter);
		logger.info("[sbuffer]" + sbuffer.toString());
		return sbuffer;
	}

	public static void createXlsReport(String templateRealPath, Collection datas, OutputStream os, Map<String, Object> parameters) {
		JRDataSource dataSource = new JRBeanCollectionDataSource(datas);
		exportExcelReport(templateRealPath, dataSource, parameters, os);
	}

	private static void exportExcelReport(String templateRealPath, JRDataSource dataSource, Map<String, Object> parameters, OutputStream os) {
		try {
			JRAbstractExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, os);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exportReport(templateRealPath, dataSource, parameters, exporter);
			os.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private static void exportReport(String templateRealPath, JRDataSource dataSource, Map<String, Object> parameters, JRAbstractExporter exporter) {
		logger.debug("[templatePath]" + templateRealPath);
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(System.getProperty("user.dir") + templateRealPath, parameters, dataSource);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.exportReport();
		} catch (Exception e) {
			logger.error("", e);
		}
	}	
	
}
