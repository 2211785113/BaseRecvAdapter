package com.example.ruru.library.listener

/**
 * 加载数据状态监听器
 */
interface LoadDataStatus<T> {
  /**
   * 加载数据成功
   */
  fun onSuccess(t: List<T>?)

  /**
   * 加载数据失败
   */
  fun onFailure(msg: String?)

  /**
   * 没有数据了
   */
  fun onNoMoreData()
}