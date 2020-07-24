package com.example.job.jobservice.quartz;


import com.example.job.jobservice.domain.ProjectAutoReportJob;
import com.example.job.jobservice.quartz.enums.ExecutionType;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * quartz任务管理器
 *
 */
@Component
public class QuartzManager {
    private static Logger logger = LoggerFactory.getLogger(QuartzManager.class);

    @Autowired
    private Scheduler scheduler;

    private Scheduler scheduler() {
        return scheduler;
    }

    public void start() {
        try {
            if (!scheduler().isStarted()) {
                scheduler().start();
            }
        } catch (SchedulerException e) {
            logger.error("开启Quartz的Scheduler实例失败", e);
        }
    }

    public void forceShutdown() {
        try {
            if (!scheduler().isShutdown()) {
                scheduler().shutdown();
            }
        } catch (SchedulerException e) {
            logger.error("强制关闭Quartz的Scheduler实例失败", e);
        }
    }

    public void shutdown() {
        try {
            if (!scheduler().isShutdown()) {
                scheduler().shutdown(true);
            }
        } catch (SchedulerException e) {
            logger.error("关闭Quartz的Scheduler实例失败", e);
        }
    }

    public void scheduleJob(List<ProjectAutoReportJob> jobList) {
        for (ProjectAutoReportJob jobInfo : jobList) {
            scheduleJob(jobInfo);
        }
    }

    public boolean scheduleJob(ProjectAutoReportJob jobInfo) {
        try {
            scheduler().scheduleJob(getJob(jobInfo, ExecutionType.SCHEDULE), getTrigger(jobInfo));
            logger.info("调度任务[{}]成功", jobInfo.getAutoReportTitle());
            return true;
        } catch (SchedulerException e) {
            logger.error("调度任务[{}]失败", jobInfo.getAutoReportTitle());
            logger.error("失败信息：", e);
            return false;
        }
    }

    /**
     * 取消任务调度
     * @param jobInfo
     * @return
     */
    public boolean unscheduleJob(ProjectAutoReportJob jobInfo) {
        try {
            scheduler().unscheduleJob(newTriggerKey(jobInfo));
            logger.info("取消调度任务[{}]成功", jobInfo.getAutoReportTitle());
            return true;
        } catch (SchedulerException e) {
            logger.error("取消调度任务[{}]失败", jobInfo.getAutoReportTitle());
            logger.error("失败信息：", e);
            return false;
        }
    }

    /***
     * 重新调度
     * @param jobInfo
     * @return
     */
    public boolean rescheduleJob(ProjectAutoReportJob jobInfo) {
        try {
            JobKey jobKey = newJobKey(jobInfo, ExecutionType.SCHEDULE);
            boolean exists = scheduler().checkExists(jobKey);

            TriggerKey triggerKey = newTriggerKey(jobInfo);
            if (exists) {
                scheduler().rescheduleJob(triggerKey, newTrigger(triggerKey, jobInfo));
                logger.info("重新调度任务[{}]成功", jobInfo.getAutoReportTitle());
                return true;
            } else {
                logger.info("任务[{}]不存在，不能重新调度", jobInfo.getAutoReportTitle());
                return false;
            }
        } catch (SchedulerException e) {
            logger.info("重新调度任务[{}]失败", jobInfo.getAutoReportTitle());
            logger.error("失败信息：", e);
            return false;
        }
    }

    /**
     * 删除任务
     * @param jobInfo
     * @return
     */
    public boolean deleteJob(ProjectAutoReportJob jobInfo) {
        try {
            scheduler().deleteJob(newJobKey(jobInfo, ExecutionType.SCHEDULE));
            logger.info("删除调度任务[{}]成功", jobInfo.getAutoReportTitle());
            return true;
        } catch (SchedulerException e) {
            logger.error("删除调度任务[{}]失败", jobInfo.getAutoReportTitle());
            logger.error("失败信息：", e);
            return false;
        }
    }

    /**
     * 暂停任务
     *
     * @param jobInfo
     */
    public boolean pauseJob(ProjectAutoReportJob jobInfo) {
        try {
            scheduler().pauseJob(newJobKey(jobInfo, ExecutionType.SCHEDULE));
            logger.info("暂停任务[{}]成功", jobInfo.getAutoReportTitle());
            return true;
        } catch (SchedulerException e) {
            logger.error("暂停任务[{}]失败", jobInfo.getAutoReportTitle());
            logger.error("失败信息：", e);
            return false;
        }
    }

    /**
     * 恢复任务
     *
     * @param jobInfo
     */
    public boolean resumeJob(ProjectAutoReportJob jobInfo) {
        try {
            scheduler().resumeJob(newJobKey(jobInfo, ExecutionType.SCHEDULE));
            logger.info("恢复任务[{}]成功", jobInfo.getAutoReportTitle());
            return true;
        } catch (SchedulerException e) {
            logger.error("恢复任务[{}]失败", jobInfo.getAutoReportTitle());
            logger.error("失败信息：", e);
            return false;
        }
    }

    /**
     * 立即执行触发器
     * @param jobInfo
     * @param executionType
     * @return
     */
    public boolean triggerJob(ProjectAutoReportJob jobInfo, ExecutionType executionType) {
        try {
            JobKey jobKey = newJobKey(jobInfo, executionType);
            boolean exists = scheduler().checkExists(jobKey);
            if (!exists) {
                scheduler().addJob(newJob(jobKey, jobInfo), false, true);
            }
            scheduler().triggerJob(jobKey);
            logger.info("触发任务[{}]成功", jobInfo.getAutoReportTitle());
            return true;
        } catch (SchedulerException e) {
            logger.error("触发任务[{}]失败", jobInfo.getAutoReportTitle());
            logger.error("失败信息：", e);
            return false;
        }
    }

    /**
     * 暂停触发器
     *
     * @param jobInfo
     */
    public boolean pauseTrigger(ProjectAutoReportJob jobInfo) {
        try {
            scheduler().pauseTrigger(newTriggerKey(jobInfo));
            logger.info("暂停触发器[{}]成功", jobInfo.getAutoReportTitle());
            return true;
        } catch (SchedulerException e) {
            logger.error("暂停触发器[{}]失败", jobInfo.getAutoReportTitle());
            logger.error("失败信息：", e);
            return false;
        }
    }

    /**
     * 恢复触发器
     *
     * @param jobInfo
     */
    public boolean resumeTrigger(ProjectAutoReportJob jobInfo) {
        try {
            scheduler().resumeTrigger(newTriggerKey(jobInfo));
            logger.error("恢复触发器[{}]成功", jobInfo.getAutoReportTitle());
            return true;
        } catch (SchedulerException e) {
            logger.error("恢复触发器[{}]失败", jobInfo.getAutoReportTitle());
            logger.error("失败信息：", e);
            return false;
        }
    }

    /**
     * 暂停所有的触发器
     */
    public boolean pauseAll() {
        try {
            scheduler().pauseAll();
            logger.info("暂停所有任务成功");
            return true;
        } catch (SchedulerException e) {
            logger.error("暂停所有任务失败");
            logger.error("失败信息：", e);
            return false;
        }
    }

    /**
     * 恢复所有的触发器
     */
    public boolean resumeAll() {
        try {
            scheduler().resumeAll();
            logger.info("恢复所有任务成功");
            return true;
        } catch (SchedulerException e) {
            logger.error("恢复所有任务失败");
            logger.error("失败信息：", e);
            return false;
        }
    }

    private JobDetail newJob(JobKey jobKey, ProjectAutoReportJob jobInfo) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(JobConstants.JOB_INFO, jobInfo);
        jobDataMap.put(JobConstants.EXECUTOR, getExecutor());
        return JobBuilder.newJob(HandlerDelegate.class).withIdentity(jobKey).usingJobData(jobDataMap)
                .withDescription(jobInfo.getAutoReportTitle()).build();
    }

    private JobDetail getJob(ProjectAutoReportJob jobInfo, ExecutionType executionType) {
        try {
            JobKey jobKey = newJobKey(jobInfo, executionType);
            boolean exists = scheduler().checkExists(jobKey);
            return (exists ? scheduler().getJobDetail(jobKey) : newJob(jobKey, jobInfo));
        } catch (SchedulerException e) {
            return null;
        }
    }

    private JobKey newJobKey(ProjectAutoReportJob jobInfo, ExecutionType executionType) {
        String id = jobInfo.getId().toString();
        return new JobKey(id, executionType.name());
    }

    private CronTrigger newTrigger(TriggerKey triggerKey, ProjectAutoReportJob jobInfo) {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.getAutoReportCron());
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(JobConstants.JOB_INFO, jobInfo);
        jobDataMap.put(JobConstants.EXECUTOR, getExecutor());
        return TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).usingJobData(jobDataMap)
                .withDescription(jobInfo.getAutoReportTitle()).build();
    }

    private Trigger getTrigger(ProjectAutoReportJob jobInfo) {
        try {
            TriggerKey triggerKey = newTriggerKey(jobInfo);
            boolean exists = scheduler().checkExists(triggerKey);
            return exists ? scheduler().getTrigger(triggerKey) : newTrigger(triggerKey, jobInfo);
        } catch (SchedulerException e) {
            return null;
        }
    }

    private static TriggerKey newTriggerKey(ProjectAutoReportJob jobInfo) {
        String id = jobInfo.getId().toString();
        return new TriggerKey(id, id);
    }

    private String getExecutor() {
        return JobConstants.SYSTEM_SCHEDULE_EXECUTOR;
    }
}
