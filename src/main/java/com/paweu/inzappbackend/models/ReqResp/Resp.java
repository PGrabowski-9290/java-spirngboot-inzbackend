package com.paweu.inzappbackend.models.ReqResp;

public class Resp<T> {
    private T data;
    private String msg;

    public Resp(String msg, T data){
        this.msg = msg;
        this.data = data;
    }
    public String getMessage() {
        return this.msg;
    }


    public T getData() {
        return this.data;
    }

    public void setMsg(String message){
        this.msg = message;
    }

    public void setData(T data){
        this.data = data;
    }
}
