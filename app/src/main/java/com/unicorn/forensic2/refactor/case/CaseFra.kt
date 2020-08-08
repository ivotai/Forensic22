package com.unicorn.forensic2.refactor.case

import com.blankj.utilcode.util.ToastUtils
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.helper.PowerMenuHelper
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_case.*


class CaseFra : BaseFra() {

    override fun initViews() {
        powerMenu = PowerMenuHelper.get(context!!, SearchType.forCase.map { PowerMenuItem(it.cn) })
        powerMenu.setOnMenuItemClickListener { position, item ->
            ToastUtils.showShort(powerMenu.selectedPosition.toString())
            powerMenu.selectedPosition = position
//            powerMenu.dismiss();
        }
    }

    override fun bindIntent() {
        super.bindIntent()

        tvCaseSearchType.safeClicks().subscribe { powerMenu.showAsDropDown(tvCaseSearchType) }
    }

    lateinit var powerMenu: PowerMenu

    override val layoutId = R.layout.fra_case

}