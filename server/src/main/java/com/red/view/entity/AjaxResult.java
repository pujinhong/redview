package com.red.view.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class AjaxResult implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean success;
    private int code;
    private String msg;
    private Object data;
    private long timestamp;
	
	private AjaxResult() {}
    
    public static AjaxResult error() {
        return error(null);
    }

    public static AjaxResult error(String message) {
        return error(null, message);
    }

    public static AjaxResult error(Integer code, String message) {
        if(code == null) {
            code = 500;
        }
        if(message == null) {
            message = "服务器内部错误";
        }
        return build(code, false, message);
    }
    
    public static AjaxResult ok() {
        return ok(null);
    }

    public static AjaxResult ok(String message) {
        return ok(null, message);
    }

    public static AjaxResult ok(Integer code, String message) {
        if(code == null) {
            code = 200;
        }
        if(message == null) {
            message = "操作成功";
        }
        return build(code, true, message);
    }
    
    public static AjaxResult build(int code, boolean success, String message) {
        return new AjaxResult()
                .setCode(code)
                .setSuccess(success)
                .setMessage(message)
                .setTimestamp(System.currentTimeMillis());
    }
    
    public AjaxResult setCode(int code) {
        this.code = code;
        return this;
    }

    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }
    public AjaxResult setMessage(String msg) {
        this.msg = msg;
        return this;
    }

    public AjaxResult setData(Object data) {
        this.data = data;
        return this;
    }

    public AjaxResult setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
}
