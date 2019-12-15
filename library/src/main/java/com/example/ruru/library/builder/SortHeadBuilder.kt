package com.example.ruru.library.builder

import com.example.ruru.library.viewholder.BaseViewHolder

/**
 * Created by lyr on 2019/12/15
 */
interface SortHeadBuilder<T> {
  fun getSortHeadLayoutId(): Int

  fun bindSortHeadData(holder: BaseViewHolder, position: Int)
}