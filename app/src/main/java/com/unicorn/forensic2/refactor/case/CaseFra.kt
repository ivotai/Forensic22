package com.unicorn.forensic2.refactor.case

import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.helper.PowerMenuHelper
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.setIIcon
import com.unicorn.forensic2.refactor.icon.Solid
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_case.*


class CaseFra : BaseFra() {

    override fun initViews() {

        constraintLayout.background = GradientDrawable().apply {
            ContextCompat.getColor(context!!, R.color.md_grey_100).let { this.setColor(it) }
            cornerRadius = ConvertUtils.dp2px(8f).toFloat()
        }

        powerMenu = PowerMenuHelper.get(context!!, SearchType.forCase.map { PowerMenuItem(it.cn) })
        powerMenu.setOnMenuItemClickListener { position, _ ->
            val searchType = when (position) {
                0 -> SearchType.Ah
                1 -> SearchType.Year
                else -> SearchType.Plaintiff
            }
            onSearchTypeChange(searchType)
        }
        tvSearchType.text = searchType.cn
        ivCaret.setIIcon(context!!, Solid.Icon.solid_caret_down, R.color.md_black)
    }

    private fun onSearchTypeChange(searchType: SearchType) {
        this.searchType = searchType
        tvSearchType.text = searchType.cn
    }

    private var searchType = SearchType.Ah

    override fun bindIntent() {
        super.bindIntent()

        tvSearchType.safeClicks().subscribe { powerMenu.showAsDropDown(tvSearchType) }
    }

    lateinit var powerMenu: PowerMenu

    override val layoutId = R.layout.fra_case

}