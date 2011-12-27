/** * ReportBase.java 
* Created on 2011-11-25 上午10:05:18 
*/

package com.wcs.report.controller;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.lang.StringUtils;

import com.wcs.base.util.JSFUtils;

/** 
* <p>Project: btcbase</p> 
* <p>Title: ReportBase.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2011.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yangshiyun@wcs-global.com">Yang Shiyun</a> 
*/

@SuppressWarnings("serial")
public abstract class ReportBase implements Serializable {
    private DataSource ds;
    /** 模板页 */
    private String templateFile = null;
    /** 编译后的模板页 */
    private String compiledFile = null;
    private String reportContent = "";
    private String printFile;

    public ReportBase() {

    }

    /**
     * Description: 将模板页进行编译
     */
    public void initComplier() {
        try {
            if (StringUtils.isEmpty(this.getTemplateFile())) { return; }
            JasperCompileManager.compileReportToFile(this.getTemplateFile(), this.getCompiledFile());
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    /**
     * Description:初始化模板页和编译文件路径
     * @param template
     * @param compiled
     */
    public void setupPage(String template, String compiled, String print) {
        if (!StringUtils.isEmpty(template)) this.templateFile = this.getProjectPath().concat(template);
        if (!StringUtils.isEmpty(compiled)) this.compiledFile = this.getProjectPath().concat(compiled);
        if (!StringUtils.isEmpty(compiled)) {
            this.printFile = this.getProjectPath().concat(print);
        }
    }

    /**
     * 
     * <p>Description: 生成报表</p>
     * @param parameters
     * @throws JRException
     */
    public void genneralReport(Map<String, Object> parameters) throws JRException {
        File reportFile = new File(this.getCompiledFile());
        if (!reportFile.exists()) {
            this.initComplier();
        }
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
        JasperPrint jasperPrint = null;
        try {
            // jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, getConnection());
            JasperFillManager.fillReportToFile(jasperReport, this.getPrintFile(), parameters, getConnection());
            jasperPrint = loadReportPrint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JRXhtmlExporter exporter = new JRXhtmlExporter();
        JSFUtils.getSession().put(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
        StringBuffer sbuffer = new StringBuffer();
        exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
        exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
        exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
        exporter.exportReport();
        this.setReportContent(sbuffer.toString());
        // System.out.println(sbuffer.toString());
    }

    public JasperPrint loadReportPrint() throws JRException {
        return (JasperPrint) JRLoader.loadObject(this.getPrintFile());
    }
    
    public void exportPdf() throws JRException{
        JasperExportManager.exportReportToPdfFile(this.getPrintFile());
    }
   
    public void exportHtml() throws JRException{
        JasperExportManager.exportReportToHtmlFile(this.getPrintFile());
    }
    
    

    /**
     * 获得连接conn
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private DataSource getDataSource() throws NamingException {
        if (ds == null) {
            javax.naming.Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/btcbase");

        }
        return ds;
    }

    private Connection getConnection() throws SQLException, NamingException {
        Connection conn = getDataSource().getConnection();
        /*
         * Statement stmt=conn.createStatement(); ResultSet rs = stmt.executeQuery("select * from product"); while(rs.next()) {
         * System.out.println("aaaaaaa" + rs.getString(11)); }
         */
        return conn;
    }

    /**
     * 获得项目路径
     * @return projectPath
     */
    protected String getProjectPath() {
        String projectPath = "";
        String realPath = JSFUtils.getRequest().getSession().getServletContext().getRealPath("");
        String[] array = realPath.replaceAll("\\\\", "/").split("/");
        int leng = array.length;
        for (int i = 0; i < leng - 1; i++) {
            projectPath += array[i] + "\\";
        }

        return projectPath;
    }

    // -------------------- setter & getter --------------------//

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public String getCompiledFile() {
        return compiledFile;
    }

    public void setCompiledFile(String compiledFile) {
        this.compiledFile = compiledFile;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getPrintFile() {
        return printFile;
    }

    public void setPrintFile(String printFile) {
        this.printFile = printFile;
    }

}
