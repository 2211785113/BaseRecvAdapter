package com.example.ruru.library.adapter;

import android.support.v7.widget.RecyclerView;

import com.example.ruru.library.builder.EmptyBuilder;
import com.example.ruru.library.listener.LoadDataListener;
import com.example.ruru.library.builder.FootBuilder;
import com.example.ruru.library.builder.HeadBuilder;
import com.example.ruru.library.builder.MultiItemBuilder;
import com.example.ruru.library.builder.SortHeadBuilder;
import com.example.ruru.library.viewholder.BaseViewHolder;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    /**
     * 计头部和尾部的数量
     */
    protected int count = 0;

    /**
     * 计所有item的数量
     */
    protected int itemCount = 0;

    /**
     * 分页加载开始的页数
     */
    protected int startPage;

    /**
     * 分类item的数量：即每sortCount个普通item上加一个sortHeadItem
     */
    protected int sortCount;

    /**
     * 多种类型type支持类
     */
    protected MultiItemBuilder multiItemBuilder;

    /**
     * 头部布局支持类：只能添加一个
     */
    protected HeadBuilder headBuilder;

    /**
     * 底部布局支持类：只能添加一个
     */
    protected FootBuilder footBuilder;

    /**
     * 上拉加载支持类
     */
    protected LoadDataListener loadDataListener;

    /**
     * 空布局支持类：空布局添加在尾部
     * 用途：分页加载数据没有数据显示一个空布局
     */
    protected EmptyBuilder emptyBuilder;

    /**
     * 分类列表支持类
     */
    protected SortHeadBuilder sortHeadBuilder;

    /**
     * 普通item类型type
     */
    protected static final int TYPE_NORMAL = 1000;

    /**
     * 头部类型type
     */
    protected static final int TYPE_HEAD = 1001;

    /**
     * 尾部类型type
     */
    protected static final int TYPE_FOOT = 1002;

    /**
     * 空布局类型type
     */
    protected static final int TYPE_EMPTY = 1003;

    /**
     * 分类列表类型type
     */
    protected static final int TYPE_SORT_HEAD = 1004;

    /**
     * 判断是否是多级列表
     *
     * @return
     */
    public boolean isMultiView() {
        return multiItemBuilder != null;
    }

    /**
     * 判断是否添加了头部view
     *
     * @return
     */
    public boolean isHeadView() {
        return headBuilder != null;
    }

    /**
     * 判断是否添加了底部view
     *
     * @return
     */
    public boolean isFootView() {
        return footBuilder != null;
    }

    /**
     * 判断是否分页加载view
     *
     * @return
     */
    public boolean isLoadingView() {
        return isFootView() && loadDataListener != null;
    }

    /**
     * 判断是否添加了空view
     *
     * @return
     */
    public boolean isEmptyView() {
        return emptyBuilder != null;
    }

    /**
     * 判断是否是分类列表view
     *
     * @return
     */
    public boolean isSortHeadView() {
        return sortHeadBuilder != null;
    }


}
