package com.unicorn.forensic2.ui.other

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.jdxx.JdxxAdapter
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_case_detail.view.*


class CaseDetailHeader(context: Context, case: Case) : FrameLayout(context),
    LayoutContainer {

    override val containerView = this

    private val jdxxAdapter = JdxxAdapter()

    init {
        LayoutInflater.from(context).inflate(R.layout.header_case_detail, this, true)
        with(case) {
            tvJdNo.text = jdNo
            tvCaseNo.text = caseNo
            tvCaseStatus.text = caseStatus
            tvDateAccept.text = dateAccept.toDisplayFormat()
            tvDateClose.text = dateClose.toDisplayFormat()
            tvCloseTypeDisplay.text = closeTypeDisplay
        }

        // 鉴定信息
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            jdxxAdapter.bindToRecyclerView(this)
        }
        ComponentHolder.appComponent.v1Api()
            .getJdxx(case.caseId).observeOnMain(context as LifecycleOwner).subscribeBy(
                onSuccess = {
                    jdxxAdapter.setNewData(it)
                }
            )

    }

}