package com.example.ruru.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.ruru.R;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.BaseRecvAdapter;
import com.example.ruru.library.click.OnRecyclerItemClickListener;
import com.example.ruru.library.viewholder.BaseViewHolder;

/**
 * 点击事件：使用recyclerview#addOnItemTouchListener
 */
public class ClickTwo extends Activity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_two);

        initView();
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final BaseRecvAdapter<String> adapter = new BaseRecvAdapter<String>(R.layout.item) {
            @Override
            public void bindData(BaseViewHolder holder, String s) {
                holder.setText(R.id.tv, s);
            }
        };

        adapter.setData(DataModel.getData());

        rv.addOnItemTouchListener(new OnRecyclerItemClickListener(rv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder childViewHolder) {
                int adapterPosition = childViewHolder.getAdapterPosition();
                Toast.makeText(ClickTwo.this, "点击了第" + (adapterPosition + 1) + "个事件", Toast.LENGTH_SHORT).show();
            }
        });

        rv.setAdapter(adapter);
    }
}
