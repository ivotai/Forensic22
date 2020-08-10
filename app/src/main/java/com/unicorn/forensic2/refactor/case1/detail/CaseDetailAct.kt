package com.unicorn.forensic2.refactor.case1.detail

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_case_detail.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView

class CaseDetailAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("案件详情")
        initVp()
    }

    private fun initVp() {
        viewPaper.offscreenPageLimit = CaseDetailType.all.size - 1
        viewPaper.adapter = CaseDetailPagerAdapter(supportFragmentManager, case)
        magicIndicator.background = GradientDrawable().apply {
            setColor(Color.parseColor("#1A000000"))
            cornerRadius = ConvertUtils.dp2px(20f).toFloat()
        }
        val commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return CaseDetailType.all.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val clipPagerTitleView = ClipPagerTitleView(context)
                clipPagerTitleView.text = CaseDetailType.all.get(index).cn
                clipPagerTitleView.textColor = Color.WHITE
                clipPagerTitleView.clipColor = colorPrimary
                clipPagerTitleView.textSize = ConvertUtils.dp2px(14f).toFloat()
                clipPagerTitleView.setOnClickListener { viewPaper.currentItem = index }
                return clipPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                val navigatorHeight = ConvertUtils.dp2px(40f)
                indicator.lineHeight = navigatorHeight.toFloat()
                indicator.roundRadius = ConvertUtils.dp2px(20f).toFloat()
                indicator.setColors(Color.WHITE)
                return indicator
            }
        }
        commonNavigator.isAdjustMode = true
        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, viewPaper)
    }

    override val layoutId = R.layout.act_case_detail

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

    private val colorPrimary by lazy { ContextCompat.getColor(this, R.color.colorPrimary) }

}