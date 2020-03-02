package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.ui.base.KVHolder

class UserMenuAdapter : BaseQuickAdapter<Any, KVHolder>(R.layout.item_user_menu) {

    override fun convert(helper: KVHolder, item: Any) {
        helper.apply {
//            tvName.text = item.namez
//            Glide.with(mContext).load(R.mipmap.menu0).into(ivIcon)
        }
    }

}