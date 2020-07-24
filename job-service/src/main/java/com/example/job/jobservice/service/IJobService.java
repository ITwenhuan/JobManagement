package com.example.job.jobservice.service;

import com.example.job.jobservice.domain.ProjectAutoReportJob;

import java.util.List;

public interface IJobService {

    List<ProjectAutoReportJob> getJob();

    List<ProjectAutoReportJob> selectAllOpenJob();

    boolean updateStatus(ProjectAutoReportJob projectAutoReportJob);

    int updateById(ProjectAutoReportJob autoReportJob);

    void autoSendEmail(ProjectAutoReportJob projectAutoReportJob);

    int deleteJob(Integer id);

    boolean start(Integer id);

    boolean stop(Integer id);

    boolean manual(Integer id);

    int saveJobInfo(ProjectAutoReportJob projectAutoReportJob);

    int updateJobInfo(ProjectAutoReportJob projectAutoReportJob);
}
