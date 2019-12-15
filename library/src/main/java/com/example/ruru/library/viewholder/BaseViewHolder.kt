package com.example.ruru.library.viewholder

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * ViewHolder负责存放每个子View并且使用泛型方法获取View中的数据。
 * Created by lyr on 2019/12/15
 */
class BaseViewHolder: RecyclerView.ViewHolder {

  var itemView: View? = null

  init {
    this.itemView = view
    views = SparseArray<View>()
  }

  constructor(itemView: View, )


  fun getView(): View {
    return itemView
  }

  fun <T extends View>getView(viewId:Int):T
  {
    View view = views . get (viewId);
    if (view == null) {
      view = itemView.findViewById(viewId);
      views.put(viewId, view);
    }
    return (T) view
  }

  fun setText(viewId: Int, text: String) {
    val textView = getView(viewId)

    textView !!.
      //textview为空

      textView.setText(text)
  }

  fun setImageResource(viewId: Int, resId: Int) {
    var imageView = getView(viewId)
    imageView.setImageResource(resId)
  }

  companion object {
    var views: SparseArray<View>? = null  //SparseArray：缓存itemView

    fun bindView(viewGroup: ViewGroup, layId: Int): BaseViewHolder {
      val view = LayoutInflater.from(viewGroup.context).inflate(layId, viewGroup, false)
      return BaseViewHolder(view)
    }
  }
}