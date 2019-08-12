package com.example.ruru.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ruru.R;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.BaseRecvAdapter;
import com.example.ruru.library.builder.EmptyBuilder;
import com.example.ruru.library.viewholder.BaseViewHolder;

public class EmptyItem extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_item);

        initView();
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        EmptyBuilder emptyBuilder = new EmptyBuilder() {
            @Override
            public int getEmptyLayoutId() {
                return R.layout.item_empty;
            }

            @Override
            public void bindEmptyData(BaseViewHolder holder, int position) {
                holder.setText(R.id.tv, "没有更多内容");
            }
        };

        BaseRecvAdapter<String> adapter = new BaseRecvAdapter<String>(R.layout.item) {
            @Override
            public void bindData(BaseViewHolder viewHolder, String s) {
                viewHolder.setText(R.id.tv, s);
            }
        };

        adapter.setData(DataModel.getData());
        adapter.addEmpty(emptyBuilder);

        rv.setAdapter(adapter);
    }
}
