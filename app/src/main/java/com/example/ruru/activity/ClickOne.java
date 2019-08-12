package com.example.ruru.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.ruru.R;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.BaseRecvAdapter;
import com.example.ruru.library.click.OnClickListener;
import com.example.ruru.library.click.OnLongClickListener;
import com.example.ruru.library.viewholder.BaseViewHolder;

/**
 * 点击事件：在adapter里注册监听事件
 */
public class ClickOne extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        initView();
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        BaseRecvAdapter<String> adapter = new BaseRecvAdapter<String>(R.layout.item) {
            @Override
            public void bindData(BaseViewHolder holder, String s) {
                holder.setText(R.id.tv, s);
            }
        };

        adapter.setData(DataModel.getData());

        //相当于holder.setOnClickListener.
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void click(View view, int position) {
                Toast.makeText(ClickOne.this, "点击了第" + (position + 1) + "条数据", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(ClickOne.this, "长按了第" + (position + 1) + "条数据", Toast.LENGTH_SHORT).show();
            }
        });

        rv.setAdapter(adapter);
    }
}
