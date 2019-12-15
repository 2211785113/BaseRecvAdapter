package com.example.ruru.library.adapter

import android.support.v7.widget.RecyclerView
import com.example.ruru.library.builder.*
import com.example.ruru.library.listener.LoadDataListener
import com.example.ruru.library.viewholder.BaseViewHolder

/**
 * Created by lyr on 2019/12/15
 */
abstract class BaseAdapter<T>: RecyclerView.Adapter<BaseViewHolder>() {

  //计头部和尾部的数量
  protected var count: Int = 0

  //计所有item的数量
  protected var itemCount: Int = 0

  //分页加载开始的页数
  protected var loadPage: Int = 0

  //分类item的数量：即每sortCount个普通item上加一个sortHeadItem
  protected var sortCount: Int = 0

  //多种类型type支持类
  protected var multiItemBuilder: MultiItemBuilder? = null

  //头部布局支持类：只能添加一个
  protected var headBuilder: HeadBuilder? = null

  //底部布局支持类：只能添加一个
  protected var footBuilder: FootBuilder? = null

  //上拉加载支持类
  protected var loadDataListener: LoadDataListener<T>? = null

  //空布局支持类：空布局添加在尾部
  //用途：分页加载数据没有数据显示一个空布局
  protected var emptyBuilder: EmptyBuilder? = null

  //分类列表支持类
  protected var sortHeadBuilder: SortHeadBuilder<T>? = null

  //判断是否是多级列表
  fun isMultiView(): Boolean {
    return multiItemBuilder != null
  }

  //判断是否添加了头部view
  fun isHeadView(): Boolean {
    return headBuilder != null
  }

  //判断是否添加了底部view
  fun isFootView(): Boolean {
    return footBuilder != null
  }

  //判断是否分页加载view
  fun isLoadingView(): Boolean {
    return isFootView() && loadDataListener != null
  }

  //判断是否添加了空view
  fun isEmptyView(): Boolean {
    return emptyBuilder != null
  }

  //判断是否是分类列表view
  fun isSortHeadView(): Boolean {
    return sortHeadBuilder != null
  }

  class FootBuilderNotLoadedException: RuntimeException {    //分页加载底部未加载异常
    constructor () {
      ("The FootBuilder is not loaded")
    }
  }
}