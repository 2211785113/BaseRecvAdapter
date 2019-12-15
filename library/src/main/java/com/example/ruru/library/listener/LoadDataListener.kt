package com.example.ruru.library.listener

/**
 * 加载数据监听器
 */
interface LoadDataListener<T> {
  /**
   * 加载数据
   */
  fun loadingData(loadPage: Int, loadDataStatus: LoadDataStatus<T>?)
}