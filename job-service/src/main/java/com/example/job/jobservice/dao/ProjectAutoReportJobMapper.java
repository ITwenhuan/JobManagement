package com.example.job.jobservice.dao;

import com.example.job.jobservice.domain.ProjectAutoReportJob;

import java.util.List;

public interface ProjectAutoReportJobMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectAutoReportJob record);

    int insertSelective(ProjectAutoReportJob record);

    ProjectAutoReportJob selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectAutoReportJob record);

    int updateByPrimaryKey(ProjectAutoReportJob record);

    List<ProjectAutoReportJob> selectAllJob();

    List<ProjectAutoReportJob> selectAllOpenJob();

    int deleteByKey(Integer id);
}