/** * ReportFileBean.java 
* Created on 2011-12-6 下午1:47:38 
*/

package com.wcs.report.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.wcs.base.controller.ViewBaseBean;
import com.wcs.base.exception.ServiceException;
import com.wcs.base.util.JSFUtils;
import com.wcs.base.util.MessageUtils;
import com.wcs.report.model.ReportFile;
import com.wcs.report.model.ReportMstr;
import com.wcs.report.service.ReportFileService;
import com.wcs.report.util.FileUtil;

/**
 * 
 * <p>Project: btcbase</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2011 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chenlong@wcs-global.com">Chen Long</a>
 */
@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ReportFileBean extends ViewBaseBean<ReportFile> {
    /** 报表文件List*/
    private List<ReportFile> reportFileList = new ArrayList<ReportFile>();
    /** 报表主数据Id*/
    private Long mastrId;
    /** 上传模板文件对象*/
    private UploadedFile rptFile;
    @Inject
    private ReportFileService reportFileService;

    /**
     * 构造函数
     */
    public ReportFileBean() {

    }

    /**
     * 
     * <p>Description: 对象构造之后调用此方法</p>
     */
    @SuppressWarnings("unused")
    @PostConstruct
    private void postConstruct() {
        reportFileList.clear();
        File file = new File(FileUtil.getProjectAbsolute());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getInstance().setUpLoadedBy((String) JSFUtils.getSession().get("userName"));
    }

    public void saveRptFile() {
        try {
            if (rptFile == null) {
                MessageUtils.addErrorMessage("rptFileId", "请选择上传文件");
                return;
            }
            String str = rptFile.getFileName();
            str = str.substring(str.indexOf(".") + 1);
            boolean flag = reportFileService.isUpload(str, rptFile.getSize());
            if (!flag) {
                MessageUtils.addErrorMessage("rptFileId", "文件大小或者文件类型不符合标准");
                return;
            }
            ReportMstr rptMstr = reportFileService.findRepMstrById(mastrId);
            String rptCode = rptMstr.getReportCode();
            int version = reportFileService.findReptFileNumber(mastrId);
            String fileName = rptCode.concat("_").concat(String.valueOf(version)).concat("_").concat(rptFile.getFileName());
            File file = createRptFile(rptCode, fileName);
            getInstance().setFileName(fileName);
            reportFileService.saveRptFile(getInstance(), rptFile.getInputstream(), file);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        this.setRptFile(file);
    }

    private File createRptFile(String rptCode, String fileName) {
        try {
            String path = FileUtil.getProjectAbsolute();
            path.concat(File.separator).concat(rptCode);
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            path.concat(File.separator).concat(fileName);
            file = new File(path);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // -------------------- setter & getter --------------------//
    public List<ReportFile> getReportFileList() {
        return reportFileList;
    }

    public void setReportFileList(List<ReportFile> reportFileList) {
        this.reportFileList = reportFileList;
    }

    public Long getMastrId() {
        return mastrId;
    }

    public void setMastrId(Long mastrId) {
        this.mastrId = mastrId;
    }

    public UploadedFile getRptFile() {
        return rptFile;
    }

    public void setRptFile(UploadedFile rptFile) {
        this.rptFile = rptFile;
    }

}
