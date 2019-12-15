package com.example.ruru.library.builder

import com.example.ruru.library.viewholder.BaseViewHolder

/**
 * Created by lyr on 2019/12/15
 */
abstract class SimpleFootBuilder: FootBuilder {
  /**
   * 正常底部布局：不用于分页加载数据时的布局
   */
  override fun onNormal(holder: BaseViewHolder) {}

  /**
   * 正在加载时底部的布局
   */
  override fun onLoading(holder: BaseViewHolder) {}

  /**
   * 加载失败时底部的布局
   */
  override fun onLoadingFailure(holder: BaseViewHolder) {}

  /**
   * 没有数据时底部的布局
   */
  override fun onNoMoreData(holder: BaseViewHolder) {}
}