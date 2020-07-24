package com.example.job.jobservice.quartz.enums;

public enum TemplateType {

    COMPANY(1, "公司级项目周报模板"),
    BU(2, "BU集项目周报模板"),
    PROJECT(3, "BU项目周报模板"),
    PROJECT_RISK(4, "项目集风险日报模板"),
    PROJECT_SINGLE_RISK(5, "单项目风险日报模板"),
    AUTO_PMP_SYS(6, "平台使用模板");

    private int value;
    private String desc;

    TemplateType(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getKey() {
        return this.value;
    }
}
