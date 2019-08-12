package com.example.ruru.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruru.R;
import com.example.ruru.base.BaseRecyclerView;
import com.example.ruru.data.DataModel;
import com.example.ruru.library.BaseRecvAdapter;
import com.example.ruru.library.viewholder.BaseViewHolder;

import static com.example.ruru.constant.ServeConstant.PAGE_SIZE;
import static com.example.ruru.constant.ServeConstant.TYPE_A;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment {

    private int pageNum = 1;
    private int pages = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_a, container, false);
        BaseRecyclerView rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(container.getContext()));

        BaseRecvAdapter<String> adapter = new BaseRecvAdapter<String>(R.layout.item) {
            @Override
            public void bindData(BaseViewHolder viewHolder, String s) {
                viewHolder.setText(R.id.tv, s);
            }
        };

        adapter.setPaginationData(DataModel.getPurePageData(pageNum, PAGE_SIZE));

        rv.setAdapter(adapter);

        rv.initPage(pageNum, pages, adapter);
        rv.initListener(TYPE_A);
        return view;
    }
}
