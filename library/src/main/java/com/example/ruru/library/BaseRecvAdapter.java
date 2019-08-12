package com.example.ruru.library;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruru.library.adapter.BaseAdapter;
import com.example.ruru.library.builder.EmptyBuilder;
import com.example.ruru.library.listener.LoadDataListener;
import com.example.ruru.library.builder.FootBuilder;
import com.example.ruru.library.builder.HeadBuilder;
import com.example.ruru.library.builder.MultiItemBuilder;
import com.example.ruru.library.builder.SortHeadBuilder;
import com.example.ruru.library.click.OnClickListener;
import com.example.ruru.library.click.OnLongClickListener;
import com.example.ruru.library.listener.LoadDataStatus;
import com.example.ruru.library.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecvAdapter<T> extends BaseAdapter {

    //list数据集合
    private List<T> list = new ArrayList<T>();

    //普通item布局id
    private int layoutId;

    //各种形式item布局id
    private int layId;

    //点击事件
    private OnClickListener onClickListener;

    //长按事件
    private OnLongClickListener onLongClickListener;

    /**
     * @param layoutId 普通item布局
     */
    public BaseRecvAdapter(int layoutId) {
        this.layoutId = layoutId;
    }

    /**
     * @param multiItemBuilder 多级item布局
     */
    public BaseRecvAdapter(MultiItemBuilder multiItemBuilder) {
        this.multiItemBuilder = multiItemBuilder;
    }

    /**
     * @return 所有item的数量
     */
    @Override
    public int getItemCount() {

        if (isSortHeadView()) {
            return getSortCount();
        }

        return getCount();
    }

    /**
     * @param position
     * @return position
     * 注意：此处必须返回position，否则会导致数据错乱
     */
    @Override
    public int getItemViewType(int position) {

        //多级item type
        if (isMultiView())
            return multiItemBuilder.getItemViewType(position);

        //头部view
        if (isHeadView() && position == 0)
            return TYPE_HEAD;

        //底部view
        if (isFootView() && isSortHeadView() && position == getSortCount() - 1)
            return TYPE_FOOT;

        if (isFootView() && !isSortHeadView() && position == getCount() - 1)
            return TYPE_FOOT;

        //空view
        if (isEmptyView() && isSortHeadView() && position == getSortCount() - 1)
            return TYPE_EMPTY;

        if (isEmptyView() && !isSortHeadView() && position == getCount() - 1)
            return TYPE_EMPTY;

        //分类头部view
        if (isHeadView() && position > 0)
            position = position - 1;

        if (isSortHeadView() && position % (sortCount + 1) == 0)
            return TYPE_SORT_HEAD;

        return position;
    }

    /**
     * 初始化布局
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            case TYPE_HEAD:
                layId = headBuilder.getHeadLayoutId();
                break;
            case TYPE_FOOT:
                layId = footBuilder.getFootLayoutId();
                break;
            case TYPE_EMPTY:
                layId = emptyBuilder.getEmptyLayoutId();
                break;
            case TYPE_SORT_HEAD:
                layId = sortHeadBuilder.getSortHeadLayoutId();
                break;
            default:
                layId = layoutId;
                break;
        }

        if (isMultiView()) {
            layId = multiItemBuilder.getLayoutId(viewType);
        }

        return BaseViewHolder.bindView(viewGroup, layId);
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param pos
     */
    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder holder, int pos) {

        //头部view
        if (isHeadView() && pos == 0) {
            headBuilder.bindHeadView(holder);
            //这里要加return：如果不加会继续执行bindData绑定数据，onCreateViewHolder负责存放View，这里负责绑定数据。
            return;
        }

        //底部view
        if (isFootView() && isSortHeadView() && pos == getSortCount() - 1) {
            footBuilder.onNormal(holder);
            return;
        }

        if (isFootView() && !isSortHeadView() && pos == getCount() - 1) {
            footBuilder.onNormal(holder);
            return;
        }

        //空view
        if (isEmptyView() && isSortHeadView() && pos == getSortCount() - 1) {
            emptyBuilder.bindEmptyData(holder, pos);
            return;
        }

        if (isEmptyView() && !isSortHeadView() && pos == getCount() - 1) {
            emptyBuilder.bindEmptyData(holder, pos);
            return;
        }

        //分类头部view
        if (isHeadView() && pos > 0)
            pos = pos - 1;

        if (isSortHeadView() && pos % (sortCount + 1) == 0) {
            sortHeadBuilder.bindSortHeadData(holder, pos);
            return;
        }

        if (isSortHeadView() && pos % (sortCount + 1) != 0) {
            pos = pos - (pos / (sortCount + 1) + 1);
        }

        //普通item绑定数据
        bindData(holder, list.get(pos));

        holder.getView().setTag(pos);

        //点击事件
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    int posTag = (int) holder.getView().getTag();
                    onClickListener.click(holder.getView(), posTag);
                }
            }
        });

        //长按事件
        holder.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongClickListener != null) {
                    int posTag = (int) holder.getView().getTag();
                    onLongClickListener.onLongClick(holder.getView(), posTag);
                }
                return true;
            }
        });
    }

    /**
     * @param viewHolder
     * @param t          绑定数据，由子类来实现
     */
    public abstract void bindData(BaseViewHolder viewHolder, T t);

    /**
     * @param list 适配数据
     */
    public void setData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clearData() {
        list.clear();
        notifyDataSetChanged();
    }

    /**
     * 分页加载数据
     */
    public void setPaginationData(List<T> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * @param headBuilder 添加头部
     */
    public void addHead(HeadBuilder headBuilder) {
        this.headBuilder = headBuilder;
        count++;
    }

    /**
     * @param footBuilder 添加尾部
     */
    public void addFoot(FootBuilder footBuilder) {
        this.footBuilder = footBuilder;
        count++;
    }

    /**
     * @param emptyBuilder 添加空布局
     */
    public void addEmpty(EmptyBuilder emptyBuilder) {
        this.emptyBuilder = emptyBuilder;
        count++;
    }

    /**
     * @param sortHeadBuilder 添加分类头部
     */
    public void addSortHead(SortHeadBuilder sortHeadBuilder, int sortSize) {
        this.sortHeadBuilder = sortHeadBuilder;
        this.sortCount = sortSize;
    }

    /**
     * @param onClickListener 设置点击事件
     */
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * @param onLongClickListener 设置长按事件
     */
    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    /**
     * @return 返回有分类头部view的总item数
     */
    private int getSortCount() {
        return list.size() + count + (list.size() % sortCount == 0 ? list.size() / sortCount : list.size() / sortCount + 1);
    }

    /**
     * @return 返回总item数
     */
    private int getCount() {
        return list.size() + count;
    }
}

