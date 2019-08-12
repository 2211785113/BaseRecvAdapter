package com.example.ruru.library.builder;

import com.example.ruru.library.viewholder.BaseViewHolder;

public abstract class SimpleFootBuilder implements FootBuilder {

    /**
     * 正常底部布局：不用于分页加载数据时的布局
     *
     * @param holder
     */
    public void onNormal(BaseViewHolder holder) {

    }

    /**
     * 正在加载时底部的布局
     *
     * @param holder
     */
    public void onLoading(BaseViewHolder holder) {

    }

    /**
     * 加载失败时底部的布局
     *
     * @param holder
     */
    public void onLoadingFailure(BaseViewHolder holder) {

    }

    /**
     * 没有数据时底部的布局
     *
     * @param holder
     */
    public void onNoMoreData(BaseViewHolder holder) {

    }


}
