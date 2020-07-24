package com.example.job.jobservice.service.impl;

import com.example.job.jobservice.dao.ProjectAutoReportJobMapper;
import com.example.job.jobservice.domain.ProjectAutoReportJob;
import com.example.job.jobservice.quartz.QuartzManager;
import com.example.job.jobservice.quartz.enums.ExecutionType;
import com.example.job.jobservice.quartz.enums.JobStatus;
import com.example.job.jobservice.service.IJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class JobServiceImpl implements IJobService {

    @Resource
    private ProjectAutoReportJobMapper autoReportJobDao;
    @Resource
    private QuartzManager quartzManager;

    @Override
    public List<ProjectAutoReportJob> getJob() {
        return autoReportJobDao.selectAllJob();
    }

    @Override
    public List<ProjectAutoReportJob> selectAllOpenJob() {
        return autoReportJobDao.selectAllOpenJob();
    }

    @Override
    public boolean updateStatus(ProjectAutoReportJob projectAutoReportJob) {
        ProjectAutoReportJob autoReportJob = autoReportJobDao.selectByPrimaryKey(projectAutoReportJob.getId());
        boolean result = true;
        // 同时要更新任务状态
        if(projectAutoReportJob.getAutoReportStatus() == JobStatus.STARTED.getKey()) {
            autoReportJob.setAutoReportStatus(JobStatus.STARTED.getKey());
            result = quartzManager.scheduleJob(autoReportJob);
        }
        if(projectAutoReportJob.getAutoReportStatus() == JobStatus.STOPPED.getKey()) {
            autoReportJob.setAutoReportStatus(JobStatus.STOPPED.getKey());
            result = quartzManager.unscheduleJob(autoReportJob);
        }
        return result && autoReportJobDao.updateByPrimaryKeySelective(projectAutoReportJob) > 0;
    }

    @Override
    public int updateById(ProjectAutoReportJob autoReportJob) {
        return autoReportJobDao.updateByPrimaryKeySelective(autoReportJob);
    }

    @Override
    public void autoSendEmail(ProjectAutoReportJob projectAutoReportJob) {
        log.info("开始任务:{} 【{}】业务数据处理", projectAutoReportJob.getId(), projectAutoReportJob.getAutoReportTitle());
        // 这里根据任务使用的模板不同写自己的业务逻辑 并且发送邮件。
    }

    @Override
    public int deleteJob(Integer id) {
        // 先删除定时任务
        quartzManager.deleteJob(autoReportJobDao.selectByPrimaryKey(id));
        return autoReportJobDao.deleteByKey(id);
    }

    @Override
    public boolean start(Integer id) {
        ProjectAutoReportJob projectAutoReportJob = autoReportJobDao.selectByPrimaryKey(id);
        quartzManager.scheduleJob(projectAutoReportJob);
        projectAutoReportJob.setAutoReportStatus(JobStatus.STARTED.getKey());
        autoReportJobDao.updateByPrimaryKeySelective(projectAutoReportJob);
        return autoReportJobDao.updateByPrimaryKeySelective(projectAutoReportJob) > 0;
    }

    @Override
    public boolean stop(Integer id) {
        ProjectAutoReportJob projectAutoReportJob = autoReportJobDao.selectByPrimaryKey(id);
        quartzManager.unscheduleJob(projectAutoReportJob);
        projectAutoReportJob.setAutoReportStatus(JobStatus.STOPPED.getKey());
        return autoReportJobDao.updateByPrimaryKeySelective(projectAutoReportJob) > 0;
    }

    @Override
    public boolean manual(Integer id) {
        ProjectAutoReportJob projectAutoReportJob = autoReportJobDao.selectByPrimaryKey(id);
        return quartzManager.triggerJob(projectAutoReportJob, ExecutionType.MANUAL);
    }

    @Override
    public int saveJobInfo(ProjectAutoReportJob projectAutoReportJob) {
        if (projectAutoReportJob.getAutoReportStatus() == null) {
            projectAutoReportJob.setAutoReportStatus(JobStatus.STOPPED.getKey());
        }
        return autoReportJobDao.insertSelective(projectAutoReportJob);
    }

    @Override
    public int updateJobInfo(ProjectAutoReportJob projectAutoReportJob) {
        quartzManager.rescheduleJob(projectAutoReportJob);
        return autoReportJobDao.updateByPrimaryKeySelective(projectAutoReportJob);
    }
}
