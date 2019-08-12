# BaseRecvAdapter
## RecyclerView万能通用适配器

### 介绍：

如果项目中有n个地方用到RecyclerView，那我们就得写n个适配器，代码冗余且不易拓展，

所以我写了一个通用的万能适配器BaseRecvAdapter。

### 支持功能：

* 单item类型列表

* 多item类型列表

* 添加列表头部

* 添加列表底部

* 添加空item

* 添加分类头部item

* 分页加载数据

* 点击事件与长按事件

### 项目地址：

https://github.com/2211785113/BaseRecvAdapter

### 使用：

1.在gradle中添加依赖

build.gradle（project）：

```
allprojects {
                repositories {
                        ...
                        maven { url 'https://jitpack.io' }
                }
        }
```
build.gradle（app）：
```
dependencies {
    implementation 'com.github.2211785113:BaseRecvAdapter:v1.0’
}
```

</br>

2.单item类型列表

初始化适配器 new SuperAdapter，并设置数据源 adapter.setData。
```
BaseRecvAdapter<String> adapter = new BaseRecvAdapter<String>(R.layout.item) {
    @Override
    public void bindData(BaseViewHolder holder, String s) {
        holder.setText(R.id.tv, s);
    }
};

adapter.setData(DataModel.getData());

rv.setAdapter(adapter);
```

</br>

3.多item类型列表
初始化适配器 new SuperAdapter(multiItemBuilder)，并设置数据源 adapter.setData。

MultiItemBuilder 中：

getItemViewType：为根据 position 设置不同的 item type；

getLayoutId：为根据不同的 item type 设置不同的布局。
```
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
```

</br>

4.添加列表头部
```
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

adapter.addHead(headBuilder);
```

</br>

5.添加列表底部
```
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

adapter.addFoot(footBuilder);
```

</br>

6.添加空item
```
EmptyBuilder emptyBuilder = new EmptyBuilder() {
    @Override
    public int getEmptyLayoutId() {
        return R.layout.item_empty;
    }
    @Override
    public void bindEmptyData(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv, "没有更多内容");
    }
};

adapter.addEmpty(emptyBuilder);
```

</br>

7.添加分类头部item

SORT_SIZE：为分类列表个数，即在SORT_SIZE个普通item列表上加一个头部。
```
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

adapter.addSortHead(sortHeadBuilder, SORT_SIZE);
```

</br>

8.分页加载数据：参见代码PageLoad。

</br>

9.点击事件与长按事件
```
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
```

