package com.unicorn.forensic2.refactor.case.detail

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

    val list = CaseDetailType.all

    private fun initVp() {
        viewPaper.adapter = CaseDetailPagerAdapter(supportFragmentManager, case)
        magicIndicator.background = GradientDrawable().apply {
            setColor(Color.parseColor("#40000000"))
            cornerRadius = ConvertUtils.dp2px(8f).toFloat()
        }
//        magicIndicator.setBackgroundResource(R.drawable.round_indicator_bg)
        val commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return list.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val clipPagerTitleView = ClipPagerTitleView(context)
                clipPagerTitleView.text = list.get(index).cn
                clipPagerTitleView.textColor = Color.WHITE
                clipPagerTitleView.clipColor = colorPrimary
                clipPagerTitleView.textSize = 16f
                clipPagerTitleView.setOnClickListener { viewPaper.setCurrentItem(index) }
                return clipPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                val navigatorHeight = ConvertUtils.dp2px(48f)

//                val borderWidth = UIUtil.dip2px(context, 1.0).toFloat()
                val lineHeight = navigatorHeight
                indicator.lineHeight = lineHeight.toFloat()
                indicator.roundRadius = ConvertUtils.dp2px(8f).toFloat()
//                indicator.yOffset = borderWidth
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