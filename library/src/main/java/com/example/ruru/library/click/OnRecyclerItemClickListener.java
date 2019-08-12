package com.example.ruru.library.click;

import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private GestureDetectorCompat mGestureDetectorCompat;//手势探测器
    private RecyclerView mRecyclerView;

    public OnRecyclerItemClickListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childViewUnder = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childViewUnder != null) {
                    RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(childViewUnder);
                    onItemClick(childViewHolder);
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childViewUnder = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childViewUnder != null) {
                    RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(childViewUnder);
                    onLongClick(childViewHolder);
                }
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        mGestureDetectorCompat.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        mGestureDetectorCompat.onTouchEvent(motionEvent);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    public void onItemClick(RecyclerView.ViewHolder childViewHolder) {

    }

    public void onLongClick(RecyclerView.ViewHolder childViewHolder) {

    }

}
