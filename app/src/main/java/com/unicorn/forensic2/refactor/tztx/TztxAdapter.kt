package com.unicorn.forensic2.refactor.tztx

import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat2
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_tztx.*

class TztxAdapter : BaseQuickAdapter<Tztx, KVHolder>(R.layout.item_tztx) {

    override fun convert(helper: KVHolder, item: Tztx) {
        helper.apply {
            tvAddTime.background = GradientDrawable().apply {
                setColor(ContextCompat.getColor(context,R.color.md_grey_400))
                cornerRadius = ConvertUtils.dp2px(5f).toFloat()
            }
            tvAddTime.text = item.notification.addtime.toDisplayFormat2("yyyy年MM月dd日 HH:mm")
            tvNotifyType.text = item.notification.notifytypeStr
            tvMsg.text = item.notification.msg
        }
    }

}