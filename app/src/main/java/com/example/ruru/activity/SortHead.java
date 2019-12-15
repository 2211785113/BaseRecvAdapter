package com.example.ruru.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ruru.R;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.builder.FootBuilder;
import com.example.ruru.library.builder.HeadBuilder;
import com.example.ruru.library.builder.SimpleFootBuilder;
import com.example.ruru.library.builder.SortHeadBuilder;
import com.example.ruru.library.viewholder.BaseViewHolder;

import static com.example.ruru.constant.ServeConstant.SORT_SIZE;


public class SortHead extends AppCompatActivity {

  private RecyclerView rv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sort_head);

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

    SortHeadBuilder sortHeadBuilder = new SortHeadBuilder<String>() {
      @Override
      public int getSortHeadLayoutId() {
        return R.layout.item_sort_head;
      }

      @Override
      public void bindSortHeadData(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_title, "这是分类列表头部");
      }
    };

    FootBuilder footBuilder = new SimpleFootBuilder() {
      @Override
      public int getFootLayoutId() {
        return R.layout.item_foot;
      }

      @Override
      public void onNormal(BaseViewHolder viewHolder) {
        viewHolder.setText(R.id.tv, "这是一个尾部");
      }
    };

    BaseRecvAdapter<String> adapter = new BaseRecvAdapter<String>(R.layout.item) {
      @Override
      public void bindData(BaseViewHolder holder, String s) {
        holder.setText(R.id.tv, s);
      }
    };

    adapter.addHead(headBuilder);
    adapter.addFoot(footBuilder);
    adapter.addSortHead(sortHeadBuilder, SORT_SIZE);

    adapter.setData(DataModel.getData());

    rv.setAdapter(adapter);
  }
}
