package com.example.ruru.library.listener;

/**
 * 分页加载数据监听器
 */
public interface LoadDataListener<T> {
    /**
     * 加载数据：从服务器或者本地获取数据
     */
    void loadingData(int loadPage, LoadDataStatus loadDataStatus);
}
