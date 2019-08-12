package com.example.ruru.library.builder;

import com.example.ruru.library.viewholder.BaseViewHolder;

public interface EmptyBuilder {

    int getEmptyLayoutId();

    void bindEmptyData(BaseViewHolder holder, int position);

}
