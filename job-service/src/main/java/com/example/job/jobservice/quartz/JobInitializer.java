package com.example.job.jobservice.quartz;


import com.example.job.jobservice.domain.ProjectAutoReportJob;
import com.example.job.jobservice.service.IJobService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;


@Component
public class JobInitializer {

    private final static Logger logger = LoggerFactory.getLogger(JobInitializer.class);

    @Resource
    private QuartzManager quartzManager;
    @Resource
    private IJobService projectAutoReportJobService;

    @PostConstruct
    public void init() {
        List<ProjectAutoReportJob> jobInfoList = projectAutoReportJobService.selectAllOpenJob();
        logger.info("存在[{}]个待初始化的任务", jobInfoList.size());
        if (CollectionUtils.isNotEmpty(jobInfoList)) {
            quartzManager.scheduleJob(jobInfoList);
            logger.info("初始化任务完成");
        }
    }
}
