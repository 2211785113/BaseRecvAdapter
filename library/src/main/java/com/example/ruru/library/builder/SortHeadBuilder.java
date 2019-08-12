package com.example.ruru.library.builder;

import com.example.ruru.library.viewholder.BaseViewHolder;

public interface SortHeadBuilder<T> {
    int getSortHeadLayoutId();

    void bindSortHeadData(BaseViewHolder holder, int position);
}
