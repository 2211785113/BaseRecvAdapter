package com.example.ruru.library.builder;

import com.example.ruru.library.viewholder.BaseViewHolder;

public interface HeadBuilder {
    int getHeadLayoutId();

    void bindHeadView(BaseViewHolder holder);
}
