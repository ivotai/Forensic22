package com.unicorn.forensic2.refactor.case1

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.KeyboardUtils
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
import io.reactivex.functions.Consumer
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

        powerMenu = PowerMenuHelper.get(context!!, CaseSearchType.all.map { PowerMenuItem(it.cn) })
        powerMenu.setOnMenuItemClickListener { position, _ ->
            val searchType = CaseSearchType.all[position]
            onSearchTypeChange(searchType)
        }
        onSearchTypeChange(CaseSearchType.Ah)

        ivCaret.setIIcon(context!!, Solid.Icon.solid_caret_down, R.color.md_black)
        viewPaper.offscreenPageLimit = CaseType.all.size - 1
        casePagerAdapter = CasePagerAdapter(childFragmentManager)
        viewPaper.adapter = casePagerAdapter
        magicIndicator.setBackgroundColor(Color.WHITE)
        initVp()
    }

    private lateinit var casePagerAdapter: CasePagerAdapter
    private fun initVp() {
        val commonNavigator = CommonNavigator(context!!)

        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return CaseType.all.size
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


    private fun onSearchTypeChange(caseSearchType: CaseSearchType) {
        this.searchType = caseSearchType
        tvSearchType.text = caseSearchType.cn
        etSearch.hint = caseSearchType.hint
        etSearch.setText("")
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, CaseType::class.java, Consumer {
            val position = CaseType.all.indexOf(it)
            viewPaper.currentItem = position
        })

        RxBus.registerEvent(this, CasePagerChangeEvent::class.java, Consumer {
            casePagerAdapter.notifyDataSetChanged()
            initVp()
        })
    }

    private var searchType = CaseSearchType.Ah

    override fun bindIntent() {
        tvSearchType.clicks().mergeWith(ivCaret.clicks())
            .subscribe { powerMenu.showAsDropDown(tvSearchType) }

        tvSearch.safeClicks().subscribe {
            CaseQueryEvent(queryMap = HashMap<String, Any>().apply {
                this[searchType.en] = etSearch.trimText()
            }).let { RxBus.post(it) }
        }

        etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                KeyboardUtils.hideSoftInput(activity!!)
                //实现自己的搜索逻辑
                CaseQueryEvent(queryMap = HashMap<String, Any>().apply {
                    this[searchType.en] = etSearch.trimText()
                }).let { RxBus.post(it) }

              return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }


private lateinit var powerMenu: PowerMenu

private val colorPrimary by lazy { ContextCompat.getColor(context!!, R.color.colorPrimary) }

override val layoutId = R.layout.fra_case

}