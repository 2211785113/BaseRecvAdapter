package com.example.ruru.library.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * ViewHolder负责存放每个子View并且使用泛型方法获取View中的数据。
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

  public View itemView;
  //SparseArray：缓存itemView
  private static SparseArray<View> views;

  public BaseViewHolder(View view) {
    super(view);
    this.itemView = view;
    views = new SparseArray<View>();
  }

  public static BaseViewHolder bindView(ViewGroup viewGroup, int layId) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(layId, viewGroup, false);
    return new BaseViewHolder(view);
  }

  public View getView() {
    return itemView;
  }

  public <T extends View> T getView(int viewId) {
    View view = views.get(viewId);
    if (view == null) {
      view = itemView.findViewById(viewId);
      views.put(viewId, view);
    }
    return (T) view;
  }

  public void setText(int viewId, String text) {
    TextView textView = getView(viewId);

    Log.d(getClass().getName(), "setText:textview== " + (textView == null) + " text=" + (text == null));
    //textview为空

    textView.setText(text);
  }


  public void setImageResource(int viewId, int resId) {
    ImageView imageView = getView(viewId);
    imageView.setImageResource(resId);
  }

}
