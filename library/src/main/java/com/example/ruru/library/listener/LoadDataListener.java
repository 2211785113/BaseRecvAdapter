package com.example.ruru.library.listener;

/**
 * 加载数据监听器
 */
public interface LoadDataListener<T> {
    /**
     * 加载数据
     */
    void loadingData(int loadPage, LoadDataStatus<T> loadDataStatus);
}
