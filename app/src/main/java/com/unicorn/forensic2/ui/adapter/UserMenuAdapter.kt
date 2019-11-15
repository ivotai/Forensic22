package com.unicorn.forensic2.ui.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.data.model.UserMenu
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_user_menu.*

class UserMenuAdapter : BaseQuickAdapter<UserMenu, KVHolder>(R.layout.item_user_menu) {

    override fun convert(helper: KVHolder, item: UserMenu) {
        helper.apply {
            tvName.text = item.name
            Glide.with(mContext).load(R.mipmap.menu0).into(ivIcon)
        }
    }

}