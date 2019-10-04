package com.example.ruru.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ruru.R;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.BaseRecvAdapter;
import com.example.ruru.library.builder.FootBuilder;
import com.example.ruru.library.builder.HeadBuilder;
import com.example.ruru.library.builder.SimpleFootBuilder;
import com.example.ruru.library.viewholder.BaseViewHolder;

public class HeadAndFootItem extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_and_foot_item);

        initView();
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        HeadBuilder headBuilder = new HeadBuilder() {
            @Override
            public int getHeadLayoutId() {
                return R.layout.item_foot;
            }

            @Override
            public void bindHeadView(BaseViewHolder holder) {
                holder.setText(R.id.tv, "这是一个头部");
            }
        };

        FootBuilder footBuilder = new SimpleFootBuilder() {
            @Override
            public int getFootLayoutId() {
                return R.layout.item_foot;
            }

            @Override
            public void onNormal(BaseViewHolder holder) {
                holder.setText(R.id.tv, "这是一个底部");
            }
        };

        BaseRecvAdapter<String> adapter = new BaseRecvAdapter<String>(R.layout.item) {
            @Override
            public void bindData(BaseViewHolder holder, String s) {
                holder.setText(R.id.tv, s);
            }
        };

        //测试建造者模式
        /*BaseRecvAdapter.Builder builder = new BaseRecvAdapter.Builder(R.layout.item).setHeadBuilder(headBuilder);

        BaseRecvAdapter<String> adapter1 = new BaseRecvAdapter<String>(R.layout.item) {
            @Override
            public void bindData(BaseViewHolder viewHolder, String s) {

            }
        };*/



        adapter.addHead(headBuilder);
        adapter.addFoot(footBuilder);

        adapter.setData(DataModel.getData());

        rv.setAdapter(adapter);
    }
}
