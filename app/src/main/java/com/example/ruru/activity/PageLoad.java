package com.example.ruru.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ruru.R;
import com.example.ruru.base.BaseRecyclerView;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.BaseRecvAdapter;
import com.example.ruru.library.builder.FootBuilder;
import com.example.ruru.library.builder.HeadBuilder;
import com.example.ruru.library.listener.LoadDataListener;
import com.example.ruru.library.builder.SimpleFootBuilder;
import com.example.ruru.library.listener.LoadDataStatus;
import com.example.ruru.library.viewholder.BaseViewHolder;

import java.util.List;

import static com.example.ruru.constant.ServeConstant.PAGE_SIZE;
import static com.example.ruru.constant.ServeConstant.TYPE_A;

/**
 * 分页自动加载数据
 */
public class PageLoad extends AppCompatActivity implements View.OnClickListener {

    //    private BaseRecyclerView mRecyclerView;
    private RecyclerView mRecyclerView;
    private BaseRecvAdapter<String> adapter;

    private int pageNum = 1;
    private int pages = 2;
    private Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_load);

        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        btnRefresh = findViewById(R.id.btn_refresh);
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new BaseRecvAdapter<String>(R.layout.item) {
            @Override
            public void bindData(BaseViewHolder holder, String s) {
                Log.d("PageLoad", "bindData:s= " + s);
                holder.<TextView>getView(R.id.tv).setText(s);
            }
        };

        adapter.addHead(new HeadBuilder() {
            @Override
            public int getHeadLayoutId() {
                return R.layout.item_foot;
            }

            @Override
            public void bindHeadView(BaseViewHolder holder) {
                holder.setText(R.id.tv, "这是个头部");
            }
        });

        adapter.addFoot(new FootBuilder() {
            @Override
            public int getFootLayoutId() {
                return R.layout.item_foot_loading;
            }

            @Override
            public void onNormal(BaseViewHolder holder) {
                holder.getView(R.id.progressbar).setVisibility(View.GONE);
                holder.getView(R.id.tv).setVisibility(View.VISIBLE);
                holder.setText(R.id.tv, "这是一个底部");
            }

            @Override
            public void onLoading(BaseViewHolder holder) {
                holder.getView(R.id.progressbar).setVisibility(View.VISIBLE);
                holder.getView(R.id.tv).setVisibility(View.GONE);
            }

            @Override
            public void onLoadingFailure(BaseViewHolder holder) {
                holder.getView(R.id.progressbar).setVisibility(View.GONE);
                holder.getView(R.id.tv).setVisibility(View.VISIBLE);
                holder.setText(R.id.tv, "加载失败");
            }

            @Override
            public void onNoMoreData(BaseViewHolder holder) {
                holder.getView(R.id.progressbar).setVisibility(View.GONE);
                holder.getView(R.id.tv).setVisibility(View.VISIBLE);
                holder.setText(R.id.tv, "没有更多内容");
            }
        });

        mRecyclerView.setAdapter(adapter);

        btnRefresh.setOnClickListener(this);

       /* mRecyclerView.initPage(pageNum, pages, adapter);
        mRecyclerView.initListener(TYPE_A);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_refresh:
                setData();
                break;
        }
    }

    /**
     * //接口回调正在加载数据
     */
    private void setData() {
        adapter.clearData();
        adapter.setPaginationData(0, new LoadDataListener<String>() {
            @Override
            public void loadingData(final int loadPage, final LoadDataStatus<String> loadDataStatus) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> list = DataModel.getPurePageData(loadPage, PAGE_SIZE);
                        if (list == null) {
                            loadDataStatus.onFailure("数据加载失败");
                        }
                        if (list.size() == 0) {
                            loadDataStatus.onNoMoreData();
                        }
                        if (list.size() > 0) {
                            loadDataStatus.onSuccess(list);
                        }
                    }
                }, 3000);
            }
        });
    }
}
