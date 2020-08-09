package com.unicorn.forensic2.refactor.case.detail

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
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
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

class CaseDetailAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("案件详情")
        initVp()
    }

    val list = CaseDetailType.all

    private fun initVp(){
//        magicIndicator.setBackgroundColor(Color.parseColor("#455a64"))
        val commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return  list.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView: SimplePagerTitleView =
                    ColorTransitionPagerTitleView(context)
                simplePagerTitleView.text = list[index].cn
                simplePagerTitleView.normalColor = Color.BLACK
                simplePagerTitleView.selectedColor =colorPrimary
                simplePagerTitleView.setOnClickListener {  }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.setColors(colorPrimary)
                return indicator
            }
        }
        commonNavigator.isAdjustMode = true
        magicIndicator.navigator = commonNavigator

        viewPaper.adapter = CaseDetailPagerAdapter(supportFragmentManager,case)
        ViewPagerHelper.bind(magicIndicator, viewPaper)
    }

    override val layoutId = R.layout.act_case_detail

    private val case by lazy { intent.getSerializableExtra(Param) as Case }
    private val colorPrimary by lazy { ContextCompat.getColor(this,R.color.colorPrimary) }
}