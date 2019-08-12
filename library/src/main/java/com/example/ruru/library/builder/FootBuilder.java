package com.example.ruru.library.builder;

import com.example.ruru.library.viewholder.BaseViewHolder;

/**
 * 单纯的底部布局支持类
 */
public interface FootBuilder {

    int getFootLayoutId();

    /**
     * 正常显示的foot：不用于分页加载数据
     *
     * @param holder
     */
    void onNormal(BaseViewHolder holder);

    /**
     * 正在加载时底部的布局
     *
     * @param holder
     */
    void onLoading(BaseViewHolder holder);

    /**
     * 加载失败时底部的布局
     *
     * @param holder
     */
    void onLoadingFailure(BaseViewHolder holder);

    /**
     * 没有数据时底部的布局
     *
     * @param holder
     */
    void onNoMoreData(BaseViewHolder holder);


}
