package com.example.ruru.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.ruru.data.DataModel;

import static com.example.ruru.constant.ServeConstant.PAGE_SIZE;
import static com.example.ruru.constant.ServeConstant.TYPE_A;
import static com.example.ruru.constant.ServeConstant.TYPE_B;

public class BaseRecyclerView extends RecyclerView {

    private LinearLayoutManager layoutManager;
    private int pageNum;
    private int pages;
    private BaseRecvAdapter adapter;

    public BaseRecyclerView(@NonNull Context context) {
        super(context);
    }

    public BaseRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void initPage(int pageNum, int pages, BaseRecvAdapter adapter) {
        layoutManager = (LinearLayoutManager) getLayoutManager();
        this.pageNum = pageNum;
        this.pages = pages;
        this.adapter = adapter;
    }

    public void initListener(final int type) {
        addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int lastPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断条件里可以加这句：newState == SCROLL_STATE_IDLE

                //滑动到底部
                if (lastPosition == layoutManager.getItemCount() - 1 && pageNum <= pages) {
                    pageNum++;
                    switch (type) {
                        case TYPE_A:
                            adapter.setPaginationData(DataModel.getPurePageData(pageNum, PAGE_SIZE));
                            break;
                        case TYPE_B:
                            adapter.setPaginationData(DataModel.getPurePageData1(pageNum, PAGE_SIZE));
                            break;
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
            }
        });
    }
}
