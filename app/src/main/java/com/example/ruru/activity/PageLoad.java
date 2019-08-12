package com.example.ruru.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.ruru.R;
import com.example.ruru.base.BaseRecyclerView;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.BaseRecvAdapter;
import com.example.ruru.library.listener.LoadDataListener;
import com.example.ruru.library.builder.SimpleFootBuilder;
import com.example.ruru.library.listener.LoadDataStatus;
import com.example.ruru.library.viewholder.BaseViewHolder;

import static com.example.ruru.constant.ServeConstant.PAGE_SIZE;
import static com.example.ruru.constant.ServeConstant.TYPE_A;

public class PageLoad extends AppCompatActivity {

    private BaseRecyclerView mRecyclerView;
    private BaseRecvAdapter<String> adapter;
    private int pageNum = 1;
    private int pages = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_load);

        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new BaseRecvAdapter<String>(R.layout.item) {
            @Override
            public void bindData(BaseViewHolder holder, String s) {
                holder.<TextView>getView(R.id.tv).setText(s);
            }
        };


        adapter.setPaginationData(DataModel.getPurePageData(pageNum, PAGE_SIZE));

        mRecyclerView.setAdapter(adapter);

        mRecyclerView.initPage(pageNum, pages, adapter);
        mRecyclerView.initListener(TYPE_A);
    }
}
