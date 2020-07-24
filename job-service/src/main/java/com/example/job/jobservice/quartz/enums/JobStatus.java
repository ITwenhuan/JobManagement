package com.example.job.jobservice.quartz.enums;

public enum JobStatus {

    STARTED(1, "已启动"),
    STOPPED(2, "已停止"),
    PAUSED(3, "已暂停");

    private int value;
    private String desc;

    JobStatus(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getKey() {
        return this.value;
    }
}
