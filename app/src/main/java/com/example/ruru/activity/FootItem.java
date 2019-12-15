package com.example.ruru.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ruru.R;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.viewholder.BaseViewHolder;

import java.util.List;


public class FootItem extends AppCompatActivity {

    private RecyclerView rv;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_item);

        initView();
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

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

        adapter.addFoot(footBuilder);
        adapter.setData(DataModel.getData());
        rv.setAdapter(adapter);
    }
}
