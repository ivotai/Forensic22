package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.MyMenu
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_my_menu.*

class MyMenuAdapter : BaseQuickAdapter<MyMenu, KVHolder>(R.layout.item_my_menu) {

    override fun convert(helper: KVHolder, item: MyMenu) {
        helper.apply {
            tvText.text = item.text
            ivIcon.setBackgroundResource(item.imgRes)
            root.safeClicks().subscribe {
                when (item) {

                }
            }
        }
    }

}