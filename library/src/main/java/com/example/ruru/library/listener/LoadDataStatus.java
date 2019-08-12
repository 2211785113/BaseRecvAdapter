package com.example.ruru.library.listener;

import java.util.List;

/**
 * 加载数据监听器
 */
public interface LoadDataStatus<T> {

    /**
     * 加载数据成功
     */
    void onSuccess(List<T> t);

    /**
     * 加载数据失败
     */
    void onFailure(String msg);

    /**
     * 没有数据了
     */
    void onNoMoreData();
}
