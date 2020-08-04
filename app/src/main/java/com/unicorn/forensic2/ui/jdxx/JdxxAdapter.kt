package com.unicorn.forensic2.ui.jdxx

import android.content.Intent
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.SimplePlayer
import com.unicorn.forensic2.ui.act.PdfAct
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_jdxx.*

class JdxxAdapter : BaseQuickAdapter<Jdxx, KVHolder>(R.layout.item_jdxx) {

    override fun convert(helper: KVHolder, item: Jdxx) {
        helper.apply {
            tvJdlb.text = item.jdlb
            tvDateLottery.text = item.dateLottery.toDisplayFormat()
            tvJgmc.text = item.jgmc
            tvZbtz.text = "查看中标通知"
            tvYhhf.text = "查看摇号回放"
            tvJdbg.text = "查看鉴定报告"

            //

            tvZbtz.safeClicks().subscribe {
                if (item.fidzbtz != null) {
                    val intent = Intent(context, PdfAct::class.java)
                    intent.putExtra(Param, item.fidzbtz.fileid)
                    context.startActivity(intent)
                } else {
                    ToastUtils.showShort("暂无文件")
                }
            }

            //

            tvYhhf.safeClicks().subscribe {
                if (item.fidyhhf != null) {
                    val intent = Intent(context, SimplePlayer::class.java)
                    intent.putExtra(Param, item.fidyhhf)
                    context.startActivity(intent)

                } else {
                    ToastUtils.showShort("暂无文件")
                }
            }

            //
            tvJdbg.safeClicks().subscribe {
                if (item.fidjdbg != null) {
                    val intent = Intent(context, PdfAct::class.java)
                    intent.putExtra(Param, item.fidjdbg)
                    context.startActivity(intent)
                } else {
                    ToastUtils.showShort("暂无文件")
                }
            }
        }

    }


}