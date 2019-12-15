package com.example.ruru.library.builder

import com.example.ruru.library.viewholder.BaseViewHolder

/**
 * Created by lyr on 2019/12/15
 * 单纯的底部布局支持类
 */
interface FootBuilder {

  fun getFootLayoutId(): Int

  /**
   * 正常显示的foot：不用于分页加载数据
   */
  fun onNormal(holder: BaseViewHolder)

  /**
   * 正在加载时底部的布局
   */
  fun onLoading(holder: BaseViewHolder)

  /**
   * 加载失败时底部的布局
   */
  fun onLoadingFailure(holder: BaseViewHolder)

  /**
   * 没有数据时底部的布局
   */
  fun onNoMoreData(holder: BaseViewHolder)
}