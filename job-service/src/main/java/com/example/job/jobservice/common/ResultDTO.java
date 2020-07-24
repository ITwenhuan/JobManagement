package com.example.job.jobservice.common;

/**
 * 描述：基础数据传输对象
 *
 */
public class ResultDTO<T> {

    private Integer code;

    private Integer status;

    private String info;

    private T returnObject;


    /**
     * 默认的构造方法
     */
    public ResultDTO() {

    }

    /**
     * 初始化基础数据，重载的构造方法
     *
     * @param code
     * @param status
     * @param info
     */
    public ResultDTO(Integer code, Integer status, String info) {
        this.code = code;
        this.status = status;
        this.info = info;
    }

    /**
     * 初始化基础数据，重载的构造方法
     *
     * @param status
     * @param info
     * @param returnObject
     */
    public ResultDTO(Integer code, Integer status, String info, T returnObject) {
        this(code, status, info);
        this.returnObject = returnObject;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(T returnObject) {
        this.returnObject = returnObject;
    }
}
