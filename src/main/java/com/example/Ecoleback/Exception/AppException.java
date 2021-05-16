package com.example.Ecoleback.Exception;

public class AppException extends RuntimeException {
    private String code ;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AppException(String msg,String code){
       super(msg);
        this.code=code;

    }
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
