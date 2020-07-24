package com.example.job.jobservice.domain;

import java.util.Date;

public class ProjectAutoReportJob {
    private Integer id;

    private String autoReportTitle;

    private String autoReportTo;

    private String autoReportToOther;

    private String autoReportToEmail;

    private String autoReportCc;

    private String autoReportCcOther;

    private String autoReportCcEmail;

    private Integer autoReportTemplate;

    private String autoReportData;

    private String autoReportCron;

    private String autoReportCreator;

    private String autoReportCreatorShow;

    private Integer autoReportStatus;

    private Date autoReportLastExecuteTime;

    private String autoReportLastExecuteTimeShow;

    private Byte isDel;

    private Date createdStime;

    private String createdStimeShow;

    private Date modifiedStime;

    private String autoReportFrom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutoReportTitle() {
        return autoReportTitle;
    }

    public void setAutoReportTitle(String autoReportTitle) {
        this.autoReportTitle = autoReportTitle == null ? null : autoReportTitle.trim();
    }

    public String getAutoReportTo() {
        return autoReportTo;
    }

    public void setAutoReportTo(String autoReportTo) {
        this.autoReportTo = autoReportTo == null ? null : autoReportTo.trim();
    }

    public String getAutoReportCc() {
        return autoReportCc;
    }

    public void setAutoReportCc(String autoReportCc) {
        this.autoReportCc = autoReportCc == null ? null : autoReportCc.trim();
    }

    public Integer getAutoReportTemplate() {
        return autoReportTemplate;
    }

    public void setAutoReportTemplate(Integer autoReportTemplate) {
        this.autoReportTemplate = autoReportTemplate;
    }

    public String getAutoReportData() {
        return autoReportData;
    }

    public void setAutoReportData(String autoReportData) {
        this.autoReportData = autoReportData == null ? null : autoReportData.trim();
    }

    public String getAutoReportCron() {
        return autoReportCron;
    }

    public void setAutoReportCron(String autoReportCron) {
        this.autoReportCron = autoReportCron == null ? null : autoReportCron.trim();
    }

    public String getAutoReportCreator() {
        return autoReportCreator;
    }

    public void setAutoReportCreator(String autoReportCreator) {
        this.autoReportCreator = autoReportCreator == null ? null : autoReportCreator.trim();
    }

    public Integer getAutoReportStatus() {
        return autoReportStatus;
    }

    public void setAutoReportStatus(Integer autoReportStatus) {
        this.autoReportStatus = autoReportStatus;
    }

    public Date getAutoReportLastExecuteTime() {
        return autoReportLastExecuteTime;
    }

    public void setAutoReportLastExecuteTime(Date autoReportLastExecuteTime) {
        this.autoReportLastExecuteTime = autoReportLastExecuteTime;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getCreatedStime() {
        return createdStime;
    }

    public void setCreatedStime(Date createdStime) {
        this.createdStime = createdStime;
    }

    public Date getModifiedStime() {
        return modifiedStime;
    }

    public void setModifiedStime(Date modifiedStime) {
        this.modifiedStime = modifiedStime;
    }

    public String getAutoReportCreatorShow() {
        return autoReportCreatorShow;
    }

    public void setAutoReportCreatorShow(String autoReportCreatorShow) {
        this.autoReportCreatorShow = autoReportCreatorShow;
    }

    public String getAutoReportLastExecuteTimeShow() {
        return autoReportLastExecuteTimeShow;
    }

    public void setAutoReportLastExecuteTimeShow(String autoReportLastExecuteTimeShow) {
        this.autoReportLastExecuteTimeShow = autoReportLastExecuteTimeShow;
    }

    public String getCreatedStimeShow() {
        return createdStimeShow;
    }

    public void setCreatedStimeShow(String createdStimeShow) {
        this.createdStimeShow = createdStimeShow;
    }

    public String getAutoReportToOther() {
        return autoReportToOther;
    }

    public void setAutoReportToOther(String autoReportToOther) {
        this.autoReportToOther = autoReportToOther;
    }

    public String getAutoReportToEmail() {
        return autoReportToEmail;
    }

    public void setAutoReportToEmail(String autoReportToEmail) {
        this.autoReportToEmail = autoReportToEmail;
    }

    public String getAutoReportCcOther() {
        return autoReportCcOther;
    }

    public void setAutoReportCcOther(String autoReportCcOther) {
        this.autoReportCcOther = autoReportCcOther;
    }

    public String getAutoReportCcEmail() {
        return autoReportCcEmail;
    }

    public void setAutoReportCcEmail(String autoReportCcEmail) {
        this.autoReportCcEmail = autoReportCcEmail;
    }

    public String getAutoReportFrom() {
        return autoReportFrom;
    }

    public void setAutoReportFrom(String autoReportFrom) {
        this.autoReportFrom = autoReportFrom;
    }
}