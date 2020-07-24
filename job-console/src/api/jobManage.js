/**
 * 任务中心服务
 */

import { ajaxPostJsonData } from '@/commonjs/BasicAuthUtil'

let jobUrl = process.env.jobService;

const jobCenterService = {

    getJobList: params => {
        return ajaxPostJsonData( `${jobUrl}/job/v1/api/getJob`, params)
    },

    updateJobStatus: params => {
        return ajaxPostJsonData( `${jobUrl}/job/v1/api/updateStatus`, params)
    },

    saveJob: params => {
        return ajaxPostJsonData( `${jobUrl}/job/v1/api/saveOrUpdate`, params )
    },

    delJob: params => {
        return ajaxPostJsonData( `${jobUrl}/job/v1/api/deleteJob/` + params.id, params )
    },

    manualJob: params => {
        return ajaxPostJsonData( `${jobUrl}/job/v1/api/` + params.id + "/manual", params )
    },
};

export { jobCenterService }
