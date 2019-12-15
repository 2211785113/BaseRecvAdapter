package com.example.ruru.library.builder

import com.example.ruru.library.viewholder.BaseViewHolder

/**
 * Created by lyr on 2019/12/15
 */
interface HeadBuilder {
  fun getHeadLayoutId(): Int
  fun bindHeadView(holder: BaseViewHolder)
}