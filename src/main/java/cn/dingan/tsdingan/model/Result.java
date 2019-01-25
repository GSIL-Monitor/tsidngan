package cn.dingan.tsdingan.model;

import java.io.Serializable;

public class Result implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    
    /**
     * 信息
     */
    private String message;

    /**
     * 状态码
     */
    
    private int statusCode;
    
    /**
     * 是否成功
     */
    private boolean success;
    
    private Object object;
    
    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public int getStatusCode() {
        return statusCode;
    }


    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public boolean isSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Result() {
        // TODO Auto-generated constructor stub
    }


    public Object getObject() {
        return object;
    }


    public void setObject(Object object) {
        this.object = object;
    }
}
