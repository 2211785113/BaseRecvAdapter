package com.example.ruru.library

import android.view.View
import android.view.ViewGroup
import com.example.ruru.library.adapter.BaseAdapter
import com.example.ruru.library.builder.*
import com.example.ruru.library.click.OnClickListener
import com.example.ruru.library.click.OnLongClickListener
import com.example.ruru.library.listener.LoadDataListener
import com.example.ruru.library.listener.LoadDataStatus
import com.example.ruru.library.viewholder.BaseViewHolder

/**
 * Created by lyr on 2019/12/15
 */
abstract class BaseRecvAdapter<T>: BaseAdapter<T> {

  //list数据集合
  private var list: List<T> = ArrayList()

  //普通item布局id
  private var layoutId: Int = 0

  //各种形式item布局id
  private var layId: Int = 0

  //点击事件
  private var onClickListener: OnClickListener? = null

  //长按事件
  private var onLongClickListener: OnLongClickListener? = null

  //分页加载上锁
  private var mLoadingLock: Boolean = true

  private var status: Int = LOADING

  constructor(layoutId: Int) { //普通item布局
    this.layoutId = layoutId
  }

  constructor(multiItemBuilder: MultiItemBuilder) { //多级item布局
    this.multiItemBuilder = multiItemBuilder
  }

  override fun getItemCount(): Int {
    return if (isSortHeadView()) getSortCount() else getCount()
  }

  override fun getItemViewType(position: Int): Int {

    if (isMultiView()) { //多级item type
      return multiItemBuilder !!.getItemViewType(position)
    }

    if (isHeadView() && position == 0) { //头部view
      return TYPE_HEAD
    }

    if (isFootView() && isSortHeadView() && position == getSortCount() - 1) { //底部view
      return TYPE_FOOT
    }

    if (isFootView() && ! isSortHeadView() && position == getCount() - 1) {
      return TYPE_FOOT
    }

    if (isEmptyView() && isSortHeadView() && position == getSortCount() - 1) { //空view
      return TYPE_EMPTY
    }

    if (isEmptyView() && ! isSortHeadView() && position == getCount() - 1) {
      return TYPE_EMPTY
    }

    if (isHeadView() && position > 0) { //分类头部view
      position -= 1
    }

    if (isSortHeadView() && position % (sortCount + 1) == 0) {
      return TYPE_SORT_HEAD
    }

    return position
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
    when(viewType) {
      TYPE_HEAD -> {
        layId = headBuilder !!.getHeadLayoutId()
      }


      TYPE_FOOT -> {
        layId = footBuilder !!.getFootLayoutId()
      }

      TYPE_EMPTY -> {
        layId = emptyBuilder !!.getEmptyLayoutId()
      }

      TYPE_SORT_HEAD -> {
        layId = sortHeadBuilder !!.getSortHeadLayoutId()
      }

      else ->
        layId = layoutId
    }

    if (isMultiView()) {
      layId = multiItemBuilder !!.getLayoutId(viewType)
    }

    return BaseViewHolder.bindView(viewGroup, layId)
  }

  override fun onBindViewHolder(holder: BaseViewHolder, pos: Int) {
    //头部view
    if (isHeadView() && pos == 0) {
      headBuilder !!.bindHeadView(holder)
      //这里要加return：如果不加会继续执行bindData绑定数据，onCreateViewHolder负责存放View，这里负责绑定数据。
      return
    }

    //分页加载view，联动改变footBuilder的状态。
    if (isLoadingView() && isSortHeadView() && pos == getSortCount() - 1) {
      pageLoading(holder)
      return
    }

    if (isLoadingView() && ! isSortHeadView() && pos == getCount() - 1) {
      pageLoading(holder)
      return
    }

    //底部view
    if (isFootView() && isSortHeadView() && pos == getSortCount() - 1) {
      footBuilder !!.onNormal(holder)
      return
    }

    if (isFootView() && ! isSortHeadView() && pos == getCount() - 1) {
      footBuilder !!.onNormal(holder)
      return
    }

    //空view
    if (isEmptyView() && isSortHeadView() && pos == getSortCount() - 1) {
      emptyBuilder !!.bindEmptyData(holder, pos)
      return
    }

    if (isEmptyView() && ! isSortHeadView() && pos == getCount() - 1) {
      emptyBuilder !!.bindEmptyData(holder, pos)
      return
    }

    //分类头部view
    if (isHeadView() && pos > 0)
      pos = pos - 1

    if (isSortHeadView() && pos % (sortCount + 1) == 0) {
      sortHeadBuilder !!.bindSortHeadData(holder, pos)
      return
    }

    if (isSortHeadView() && pos % (sortCount + 1) != 0) {
      pos = pos - (pos / (sortCount + 1) + 1)
    }

    //普通item绑定数据
    bindData(holder, list.get(pos))

    holder.getView().setTag(pos)

    //点击事件
    holder.getView().setOnClickListener {view ->
      if (onClickListener != null) {
        val posTag = holder.getView().tag
        onClickListener !!.onClick(holder.getView(), posTag as Int)
      }
    }

    //长按事件
    holder.getView().setOnLongClickListener {view ->
      if (onLongClickListener != null) {
        val posTag = holder.getView().tag
        onLongClickListener !!.onLongClick(holder.getView(), posTag as Int)

      }
      true
    }
  }

  private fun pageLoading(holder: BaseViewHolder) {
    //监听事件回调正在加载数据
    if (status == LOADING) {
      //                if (!mLoadingLock) {
      //                    footBuilder.onLoading(holder)
      //                } else {
      //                    mLoadingLock = false
      footBuilder !!.onLoading(holder)
      loadDataListener !!.loadingData(loadPage + 1, loadDataStatus)
      //                }
    }

    if (status == LOADING_FAILURE) {
      footBuilder !!.onLoadingFailure(holder)
      //加载状态改成正在加载，下次即可重新加载
      status = LOADING
      //点击footer重新加载
      holder.itemView.setOnClickListener()
      holder.itemView.setOnClickListener(object: OnClickListener() {
        override fun onClick(view: View, position: Int) {
          notifyDataSetChanged()
        }
      })
    }

    if (status == LOADING_NOMORE) {
      footBuilder.onNoMoreData(holder)
    }
  }

  var loadDataStatus = LoadDataStatus()
  {
    fun onSuccess(list: List<T>) {
      addData(list)
      loadPage ++
      //            mLoadingLock = true
    }

    fun onFailure(msg: String) {
      onLoadingStatusChanged(LOADING_FAILURE)
    }

    fun onNoMoreData() {
      onLoadingStatusChanged(LOADING_NOMORE)
    }
  }

  fun onLoadingStatusChanged(status: Int) {
    this.status = status
    //        mLoadingLock = true
    notifyDataSetChanged()
  }

  /**
   * 绑定数据，由子类来实现
   */
  abstract fun bindData(viewHolder: BaseViewHolder, t: T)

  /**
   * 适配数据
   */
  fun setData(list: List<T>) {
    this.list = list
    notifyDataSetChanged()
  }

  /**
   * 清空数据
   */
  fun clearData() {
    this.list.clear()
    notifyDataSetChanged()
  }

  fun addData(list: List<T>) {
    this.list.addAll(list)
    notifyDataSetChanged()
  }

  /**
   * 分页加载数据(无刷新提示)
   */
  fun setPaginationData(list: List<T>) {
    this.list.addAll(list)
    notifyDataSetChanged()
  }

  /**
   * 分页加载数据(有刷新提示)
   *
   * @param loadPage
   * @param loadDataListener
   */
  fun setPaginationData(loadPage: Int, loadDataListener: LoadDataListener<T>) {
    if (this.footBuilder == null) {
      throw FootBuilderNotLoadedException()
    }
    this.loadPage = loadPage
    this.loadDataListener = loadDataListener
  }

  /**
   * @param headBuilder 添加头部
   */
  fun addHead(headBuilder: HeadBuilder) {
    this.headBuilder = headBuilder
    count ++
  }

  /**
   * @param footBuilder 添加尾部
   */
  fun addFoot(footBuilder: FootBuilder) {
    this.footBuilder = footBuilder
    count ++
  }

  /**
   * @param emptyBuilder 添加空布局
   */
  fun addEmpty(emptyBuilder: EmptyBuilder) {
    this.emptyBuilder = emptyBuilder
    count ++
  }

  /**
   * @param sortHeadBuilder 添加分类头部
   */
  fun addSortHead(sortHeadBuilder: SortHeadBuilder<T>, sortSize: Int) {
    this.sortHeadBuilder = sortHeadBuilder
    this.sortCount = sortSize
  }

  /**
   * @param onClickListener 设置点击事件
   */
  fun setOnClickListener(onClickListener: OnClickListener) {
    this.onClickListener = onClickListener
  }

  /**
   * @param onLongClickListener 设置长按事件
   */
  fun setOnLongClickListener(onLongClickListener: OnLongClickListener) {
    this.onLongClickListener = onLongClickListener
  }

  /**
   * @return 返回有分类头部view的总item数
   */
  fun getSortCount(): Int {
    return list.size + count + if (list.size % sortCount == 0) list.size / sortCount else list.size / sortCount + 1
  }

  /**
   * @return 返回总item数
   */
  fun getCount(): Int {
    return list.size + count
  }

  /**
   * 测试建造者模式
   */
  /*public static class Builder {

      private int layoutId
      private HeadBuilder headBuilder
      private MultiItemBuilder multiItemBuilder

      public Builder(int layoutId) {
          this.layoutId = layoutId
      }

      public Builder setHeadBuilder(HeadBuilder headBuilder) {
          this.headBuilder = headBuilder
          return this
      }

      public Builder setMultiItemBuilder(MultiItemBuilder multiItemBuilder) {
          this.multiItemBuilder = multiItemBuilder
          return this
      }

      public Builder build() {
          return this
      }
  }

  public static void init(Builder builder) {
      multiItemBuilder = builder.multiItemBuilder
      headBuilder = builder.headBuilder
  }*/


  companion object {
    private const val TAG: String = "BaseRecvAdapter"

    private const val TYPE_NORMAL: Int = 1000   //普通item类型type
    private const val TYPE_HEAD: Int = 1001   //头部类型type
    private const val TYPE_FOOT: Int = 1002   //尾部类型type
    private const val TYPE_EMPTY: Int = 1003   //空布局类型type
    private const val TYPE_SORT_HEAD: Int = 1004  //分类列表类型type

    private const val LOADING: Int = 2001   //分页加载数据：正在加载
    private const val LOADING_FAILURE: Int = 2002   //分页加载数据：加载失败
    private const val LOADING_NOMORE: Int = 2003    //分页加载数据：没有数据
  }
}