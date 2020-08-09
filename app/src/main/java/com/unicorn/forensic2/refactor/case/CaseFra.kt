package com.unicorn.forensic2.refactor.case

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.jakewharton.rxbinding3.view.clicks
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.helper.PowerMenuHelper
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.setIIcon
import com.unicorn.forensic2.app.trimText
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.refactor.icon.Solid
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_case.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

class CaseFra : BaseFra() {

    override fun initViews() {

        constraintLayout.background = GradientDrawable().apply {
            ContextCompat.getColor(context!!, R.color.md_grey_100).let { this.setColor(it) }
            cornerRadius = ConvertUtils.dp2px(8f).toFloat()
        }

        powerMenu = PowerMenuHelper.get(context!!, SearchType.forCase.map { PowerMenuItem(it.cn) })
        powerMenu.setOnMenuItemClickListener { position, _ ->
            val searchType = SearchType.forCase[position]
            onSearchTypeChange(searchType)
        }
        onSearchTypeChange(SearchType.Ah)

        ivCaret.setIIcon(context!!, Solid.Icon.solid_caret_down, R.color.md_black)

        initVp()
    }

    private fun initVp() {
        viewPaper.offscreenPageLimit = CaseType.all.size - 1
        viewPaper.adapter = CasePagerAdapter(childFragmentManager)
        magicIndicator.setBackgroundColor(Color.WHITE)
        val commonNavigator = CommonNavigator(context!!)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return  CaseType.all.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView: SimplePagerTitleView =
                    ColorTransitionPagerTitleView(context)
                simplePagerTitleView.normalColor = Color.BLACK
                simplePagerTitleView.selectedColor = colorPrimary
                simplePagerTitleView.text = CaseType.all.map { it.cn }[index]
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
                simplePagerTitleView.setOnClickListener { viewPaper.currentItem = index }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.lineHeight = UIUtil.dip2px(context, 4.0).toFloat()
                indicator.lineWidth = UIUtil.dip2px(context, 12.0).toFloat()
                indicator.roundRadius = UIUtil.dip2px(context, 4.0).toFloat()
                indicator.yOffset = UIUtil.dip2px(context, 8.0).toFloat()
                indicator.setColors(colorPrimary)
                return indicator
            }

        }
        commonNavigator.isAdjustMode = true
        magicIndicator.navigator = commonNavigator

        ViewPagerHelper.bind(magicIndicator, viewPaper)
    }


    private fun onSearchTypeChange(searchType: SearchType) {
        this.searchType = searchType
        tvSearchType.text = searchType.cn
        etSearch.hint = searchType.hint
        etSearch.setText("")
    }

    private var searchType = SearchType.Ah

    override fun bindIntent() {
        tvSearchType.clicks().mergeWith(ivCaret.clicks())
            .subscribe { powerMenu.showAsDropDown(tvSearchType) }

        tvSearch.safeClicks().subscribe {
            CaseQueryEvent(queryMap = HashMap<String, Any>().apply {
                this[searchType.en] = etSearch.trimText()
            }).let { RxBus.post(it) }
        }
    }

    private lateinit var powerMenu: PowerMenu

    private val colorPrimary by lazy { ContextCompat.getColor(context!!, R.color.colorPrimary) }

    override val layoutId = R.layout.fra_case

}