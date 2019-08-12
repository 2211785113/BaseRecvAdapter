package com.example.ruru.listener;

public interface Callback<T> {

    /**
     * 请求数据成功
     */
    void onSuccess(T t);

    /**
     * 请求数据失败
     */
    void onFailure();
}
