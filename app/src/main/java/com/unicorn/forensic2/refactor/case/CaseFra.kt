package com.unicorn.forensic2.refactor.case

import android.graphics.Color
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_case.*


class CaseFra : BaseFra() {
    override fun bindIntent() {
        super.bindIntent()

        tvCaseSearchType.safeClicks().subscribe { showCaseSearchTypeDialog() }
    }

    private fun showCaseSearchTypeDialog() {
        val powerMenu = PowerMenu.Builder(context!!)
            .addItemList(CaseSearchType.all.map { PowerMenuItem(it.cn) }) // list has "Novel", "Poerty", "Art"
//            .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT) // Animation start point (TOP | LEFT).
            .setMenuRadius(10f) // sets the corner radius.
            .setMenuShadow(10f) // sets the shadow.
            .setTextColor(ContextCompat.getColor(context!!, R.color.md_grey_800))
            .setTextGravity(Gravity.CENTER)
            .setSelectedTextColor(Color.WHITE)
            .setMenuColor(Color.WHITE)
            .setSelectedMenuColor(ContextCompat.getColor(context!!, R.color.colorPrimary))

            .build()
        powerMenu.   setOnMenuItemClickListener { position, item ->
            powerMenu.setSelectedPosition(position); // change selected item
            powerMenu.dismiss();
        }
        powerMenu.showAsDropDown(tvCaseSearchType)
//        powerMenu.show(tvCaseSearchType)
    }

    override val layoutId = R.layout.fra_case

}