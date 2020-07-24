package com.example.job.jobservice.quartz;


import com.example.job.jobservice.domain.ProjectAutoReportJob;
import com.example.job.jobservice.service.IJobService;
import jdk.internal.util.EnvUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

@Slf4j
public class HandlerDelegate implements Job {

    @Resource
    private IJobService iJobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ProjectAutoReportJob projectAutoReportJob = (ProjectAutoReportJob) jobExecutionContext.getMergedJobDataMap().get(JobConstants.JOB_INFO);
        log.info("----execute job-----<{}>---- time :{} -------" , projectAutoReportJob.getAutoReportTitle(), jobExecutionContext.getFireTime());
        //开始处理业务数据 发送邮件 这里也可以改造用反射加载定时任务类，扩展性好些。我这里是根据我们业务限制的。
        iJobService.autoSendEmail(projectAutoReportJob);
        ProjectAutoReportJob autoReportJob = new ProjectAutoReportJob();
        autoReportJob.setId(projectAutoReportJob.getId());
        autoReportJob.setAutoReportLastExecuteTime(jobExecutionContext.getFireTime());
        iJobService.updateById(autoReportJob);
    }
}
