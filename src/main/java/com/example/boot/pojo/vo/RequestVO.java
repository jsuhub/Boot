package com.example.boot.pojo.vo;

public class RequestVO<T> {
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestVO{" +
                "data=" + data +
                '}';
    }
}
