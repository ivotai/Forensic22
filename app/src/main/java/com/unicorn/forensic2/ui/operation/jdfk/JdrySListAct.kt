package com.unicorn.forensic2.ui.operation.jdfk

import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.ui_title_recycler.*

class JdrySListAct : BaseAct() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("选择鉴定人员")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@JdrySListAct)
            jdrySAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
    }

    override fun bindIntent() {
        val mask = DialogHelper.showMask(this)
        v1Api.jdJdryList(jgid = case.jgid, jdlbId = case.jdlbId).observeOnMain(this)
            .subscribeBy(
                onSuccess = { list ->
                    mask.dismiss()
                    jdrySAdapter.setNewData(list.map { JdryS(jdry = it) })
                },
                onError = {
                    mask.dismiss()
                }
            )

        titleBar.setOperation("确定").safeClicks().subscribe {
            if (jdrySAdapter.jdryListSelected.isEmpty()) {
                ToastUtils.showShort("请选择鉴定人员")
                return@subscribe
            }
            RxBus.post(JdrySelectEvent(jdryList = jdrySAdapter.jdryListSelected))
            finish()
        }
    }

    private val jdrySAdapter = JdrySAdapter()

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

    override val layoutId = R.layout.ui_title_recycler

}
