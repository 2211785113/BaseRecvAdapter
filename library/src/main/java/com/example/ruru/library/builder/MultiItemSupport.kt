package com.example.ruru.library.builder

/**
 * Created by lyr on 2019/12/15
 */
abstract class MultiItemSupport {
  abstract fun getItemViewType(position: Int): Int
  abstract fun getLayoutId(viewType: Int): Int
}