package com.example.job.jobservice.quartz.enums;

public enum ExecutionType {

    SCHEDULE(1, "调度"), MANUAL(2, "手动");

    private int value;
    private String desc;

    ExecutionType(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }
}
