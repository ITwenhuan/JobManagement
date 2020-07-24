package com.example.job.jobservice.api;

import com.alibaba.fastjson.JSONObject;
import com.example.job.jobservice.common.ResultDTO;
import com.example.job.jobservice.common.ResultUtils;
import com.example.job.jobservice.domain.ProjectAutoReportJob;
import com.example.job.jobservice.service.IJobService;
import com.example.job.jobservice.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/job/v1/api/")
public class JobController {

    @Resource
    private IJobService jobService;

    /**
     * 获取任务列表
     * @param projectAutoReportJob
     * @return
     */
    @PostMapping(value = "/getJob", produces = "application/json")
    public ResultDTO getJob(@RequestBody(required = false) ProjectAutoReportJob projectAutoReportJob){
        List<ProjectAutoReportJob> list = jobService.getJob();
        list.stream().forEach(projectAutoReport -> {
            projectAutoReport.setCreatedStimeShow(DateUtils.format(projectAutoReport.getCreatedStime(), DateUtils.YYYMMDDHHMMSS));
            if(Objects.nonNull(projectAutoReport.getAutoReportLastExecuteTime())) {
                projectAutoReport.setAutoReportLastExecuteTimeShow(DateUtils.format(projectAutoReport.getAutoReportLastExecuteTime(), DateUtils.YYYMMDDHHMMSS));
            }
            projectAutoReport.setAutoReportCreatorShow(projectAutoReport.getAutoReportCreator());
            log.info("获取定时任务结果：{}, {}", projectAutoReport.getId(), projectAutoReport.getAutoReportTitle());
        });
        return ResultUtils.getSuccessResultDTO(list);
    }

    /**
     * 创建任务
     * @param projectAutoReportJob
     * @return
     */
    @PostMapping(value = "/saveOrUpdate", produces = "application/json")
    public ResultDTO saveOrUpdateJobInfo(@RequestBody(required = false) ProjectAutoReportJob projectAutoReportJob){
        projectAutoReportJob.setAutoReportCreator("zhengweishan"); // 这里我写死了，应该根据登录人赋值
        log.info("登录人:{} 创建/修改任务入参：{}", "zhengweishan", JSONObject.toJSONString(projectAutoReportJob));
        if(Objects.nonNull(projectAutoReportJob.getId())) {
            jobService.updateJobInfo(projectAutoReportJob);
        } else {
            jobService.saveJobInfo(projectAutoReportJob);
        }
        return ResultUtils.getSuccessResultDTO(true);
    }

    /**
     * 启动/暂停任务
     * @param projectAutoReportJob
     * @return
     */
    @PostMapping(value = "/updateStatus", produces = "application/json")
    public ResultDTO updateStatus(@RequestBody(required = false) ProjectAutoReportJob projectAutoReportJob){
        log.info("更新任务状态入参：{}", JSONObject.toJSONString(projectAutoReportJob));
        return ResultUtils.getSuccessResultDTO(jobService.updateStatus(projectAutoReportJob));
    }

    @PostMapping(value = "/deleteJob/{id}", produces = "application/json")
    public ResultDTO deleteJob(@PathVariable(value = "id") Integer id){
        log.info("删除任务入参id：{}", id);
        jobService.deleteJob(id);
        return ResultUtils.getSuccessResultDTO(true);
    }

    /**
     * 开启任务
     * @param id
     * @return
     */
    @PostMapping("/{id}/start")
    public ResultDTO start(@PathVariable("id") Integer id) {
        log.info("开启任务:{}", id);
        return ResultUtils.getSuccessResultDTO(jobService.start(id));
    }

    /**
     * 停止任务
     * @param id
     * @return
     */
    @PostMapping("/{id}/stop")
    public ResultDTO stop(@PathVariable("id") Integer id) {
        log.info("停止任务:{}", id);
        return ResultUtils.getSuccessResultDTO(jobService.stop(id));
    }

    /**
     * 立即执行
     * @param id
     * @return
     */
    @PostMapping("/{id}/manual")
    public ResultDTO manual(@PathVariable("id") Integer id) {
        log.info("立即执行任务:{}", id);
        return ResultUtils.getSuccessResultDTO(jobService.manual(id));
    }
}
