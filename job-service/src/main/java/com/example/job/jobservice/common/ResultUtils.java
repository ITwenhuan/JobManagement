package com.example.job.jobservice.common;


import com.alibaba.fastjson.JSON;

/**
 * 描述：抽象工具类，对返回结果进行封装
 *
 */
public final class ResultUtils {

    /**
     * 隐藏构造方法
     */
    private ResultUtils() {

    }


    /**
     * 封装成功的返回结果
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> getSuccessResultDTO(T result) {
        return new ResultDTO<>(BaseConstants.MAGIC_NUM_200, BaseConstants.MAGIC_NUM_1, BaseConstants.SUCCESS, result);
    }


    /**
     * 封装失败的返回结果
     *
     * @param code
     * @param status
     * @param info
     * @return
     */
    public static ResultDTO getErrorResultDTO(Integer code, Integer status, String info) {
        return new ResultDTO<>(code, status, info);
    }

    /**
     * 封装错误信息，返回json格式字符串
     *
     * @param message
     * @return
     */
    public static String getErrorResultJsonStr(String message) {
        return JSON.toJSONString(new ResultDTO<>(BaseConstants.MAGIC_NUM_201, BaseConstants.MAGIC_NUM_0, message));
    }

    /**
     * 封装失败的返回结果
     *
     * @param info
     * @return
     */
    public static ResultDTO getErrorResultDTO(String info) {
        return new ResultDTO<>(BaseConstants.MAGIC_NUM_201, BaseConstants.MAGIC_NUM_0, info);
    }

}
