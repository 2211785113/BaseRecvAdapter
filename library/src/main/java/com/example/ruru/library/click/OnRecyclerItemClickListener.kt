package com.example.ruru.library.click

import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * Created by lyr on 2019/12/15
 */
class OnRecyclerItemClickListener: RecyclerView.OnItemTouchListener {

  private var mGestureDetectorCompat: GestureDetectorCompat? = null //手势探测器
  private var mRecyclerView: RecyclerView? = null

  override fun onTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent) {
    mGestureDetectorCompat?.onTouchEvent(motionEvent)
  }


  constructor(recyclerView: RecyclerView) {
    mRecyclerView = recyclerView
    mGestureDetectorCompat = GestureDetectorCompat(recyclerView.context, object: GestureDetector.SimpleOnGestureListener() {
      override fun onSingleTapUp(e: MotionEvent?): Boolean {
        val childViewUnder = mRecyclerView !!.findChildViewUnder(e !!.x, e !!.y)
        if (childViewUnder != null) {
          val childViewHolder = mRecyclerView !!.getChildViewHolder(childViewUnder)
          onItemClick(childViewHolder)
        }
        return true
      }

      override fun onLongPress(e: MotionEvent?) {
        val childViewUnder = mRecyclerView !!.findChildViewUnder(e !!.x, e !!.y)
        if (childViewUnder != null) {
          val childViewHolder = mRecyclerView !!.getChildViewHolder(childViewUnder)
          onLongClick(childViewHolder)
        }
      }
    })
  }

  override fun onInterceptTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent): Boolean {
    mGestureDetectorCompat?.onTouchEvent(motionEvent)
    return false
  }

  override fun onRequestDisallowInterceptTouchEvent(p0: Boolean) {
  }

  fun onItemClick(childViewHolder: RecyclerView.ViewHolder) {
  }

  fun onLongClick(childViewHolder: RecyclerView.ViewHolder) {
  }
}