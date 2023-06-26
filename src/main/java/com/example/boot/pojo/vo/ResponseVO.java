package com.example.boot.pojo.vo;

public class ResponseVO<T> {

    int status;

    String statusInfo;

    T data;

    public ResponseVO() {
    }

    public ResponseVO(int status, String statusInfo, T data) {
        this.status = status;
        this.statusInfo = statusInfo;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
