package com.example.ruru.library.builder

import com.example.ruru.library.viewholder.BaseViewHolder

/**
 * Created by lyr on 2019/12/15
 */
interface EmptyBuilder {

  fun getEmptyLayoutId():Int
  fun bindEmptyData(holder:BaseViewHolder,position:Int)
}