package com.example.ruru.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.ruru.R;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.BaseRecvAdapter;
import com.example.ruru.library.builder.MultiItemBuilder;
import com.example.ruru.library.viewholder.BaseViewHolder;

import java.util.List;

public class MultiItem extends AppCompatActivity {

    private RecyclerView rv;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item);

        initView();
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MultiItemBuilder multiItemBuilder = new MultiItemBuilder() {
            @Override
            public int getItemViewType(int position) {
                return position % 2 == 0 ? ITEM_TYPE.TYPE_TEXT_RIGHT.ordinal() : ITEM_TYPE.TYPE_TEXT_LEFT.ordinal();
            }

            @Override
            public int getLayoutId(int viewType) {
                return viewType == ITEM_TYPE.TYPE_TEXT_LEFT.ordinal() ? R.layout.item_left : R.layout.item_right;
            }
        };

        //注意此处：匿名类实现抽象类和实现接口都可以。
        /*MultiItemSupport multiItemSupport = new MultiItemSupport() {
            @Override
            public int getItemViewType(int position) {
                return position % 2 == 0 ? ITEM_TYPE.TYPE_TEXT_RIGHT.ordinal() : ITEM_TYPE.TYPE_TEXT_LEFT.ordinal();
            }

            @Override
            public int getLayoutId(int viewType) {
                return viewType == ITEM_TYPE.TYPE_TEXT_LEFT.ordinal() ? R.layout.item_left : R.layout.item_right;
            }
        };*/

        BaseRecvAdapter<String> adapter = new BaseRecvAdapter<String>(multiItemBuilder) {
            @Override
            public void bindData(BaseViewHolder holder, String s) {
                TextView tvLeft = holder.getView(R.id.tv_left);
                if (tvLeft != null) {
                    tvLeft.setText(s);
                }

                TextView tvRight = holder.getView(R.id.tv_right);
                if (tvRight != null) {
                    tvRight.setText(s);
                }
            }
        };

        adapter.setData(DataModel.getData());

        rv.setAdapter(adapter);
    }

    public enum ITEM_TYPE {
        TYPE_TEXT_LEFT,
        TYPE_TEXT_RIGHT
    }
}

