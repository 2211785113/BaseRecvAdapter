package com.example.ruru.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ruru.R;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.viewholder.BaseViewHolder;

/**
 * Created by SophieRu on 2019/3/29
 */
public class SingleItem extends AppCompatActivity {

    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        initView();
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        BaseRecvAdapter<String> adapter = new BaseRecvAdapter<String>(R.layout.item) {
            @Override
            public void bindData(BaseViewHolder holder, String s) {
                holder.setText(R.id.tv, s);
            }
        };

        adapter.setData(DataModel.getData());

        rv.setAdapter(adapter);
    }
}
