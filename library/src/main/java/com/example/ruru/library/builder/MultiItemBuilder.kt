package com.example.ruru.library.builder

/**
 * Created by lyr on 2019/12/15
 */
interface MultiItemBuilder {
  fun getItemViewType(position: Int): Int
  fun getLayoutId(viewType: Int): Int
}