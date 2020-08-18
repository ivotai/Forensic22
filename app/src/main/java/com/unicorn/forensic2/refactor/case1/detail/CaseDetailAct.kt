package com.unicorn.forensic2.refactor.case1.detail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Case
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseOperation
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.ui.base.BaseAct
import com.unicorn.forensic2.ui.operation.JdTaskDocHelper
import com.unicorn.forensic2.refactor.caseDemo.CaseDemoPListAct
import com.unicorn.forensic2.ui.operation.hf.HfAct
import com.unicorn.forensic2.ui.operation.hf.RefreshCaseEvent
import com.unicorn.forensic2.ui.operation.jdbg.JDBGAct
import com.unicorn.forensic2.ui.operation.jdfk.JDFKAct
import com.unicorn.forensic2.ui.operation.lotteryDelay.LotteryDelayListAct
import com.unicorn.forensic2.ui.operation.remind.RemindListAct
import io.reactivex.functions.Consumer
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

    override fun bindIntent() {
        if (CaseOperation.all(caseType).isNotEmpty())
            titleBar.setOperation("操作").safeClicks().subscribe { showOperationDialog() }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, RefreshCaseEvent::class.java, Consumer {
            finish()
        })
    }

    private fun showOperationDialog() {
        MaterialDialog(this).show {
            listItems(items = CaseOperation.all(caseType).map { it.cn }) { _, index, _ ->
                val result = CaseOperation.all(caseType)[index]
                when (result) {
                    CaseOperation.HF -> Intent(this@CaseDetailAct, HfAct::class.java).apply {
                        putExtra(Param, case.lid)
                    }.let { startActivity(it) }
                    CaseOperation.JDFK -> Intent(this@CaseDetailAct, JDFKAct::class.java).apply {
                        putExtra(Param, case)
                    }.let { startActivity(it) }
                    CaseOperation.JDBG -> Intent(this@CaseDetailAct, JDBGAct::class.java).apply {
                        putExtra(Param, case)
                    }.let { startActivity(it) }
                    CaseOperation.BATX -> Intent(
                        this@CaseDetailAct,
                        RemindListAct::class.java
                    ).apply {
                        putExtra(Param, case)
                    }.let { startActivity(it) }
                    CaseOperation.AJBW -> Intent(
                        this@CaseDetailAct,
                        CaseDemoPListAct::class.java
                    ).apply {
                        putExtra(Param, case)
                    }.let { startActivity(it) }
                    CaseOperation.BGPF_JDJGADMIN, CaseOperation.BGPF_SFJD -> Intent(
                        this@CaseDetailAct,
                        LotteryDelayListAct::class.java
                    ).apply {
                        putExtra(Param, case)
                    }.let { startActivity(it) }

                    CaseOperation.LASP -> JdTaskDocHelper.showJdTaskDocDialog(
                        this@CaseDetailAct,
                        case, result.cn, 23, 24
                    )
                    CaseOperation.CYHSP -> JdTaskDocHelper.showJdTaskDocDialog(
                        this@CaseDetailAct,
                        case, result.cn, 25, 26
                    )
                    CaseOperation.JASP -> JdTaskDocHelper.showJdTaskDocDialog(
                        this@CaseDetailAct,
                        case, result.cn, 27, 28
                    )
                    CaseOperation.XASP -> JdTaskDocHelper.showJdTaskDocDialog(
                        this@CaseDetailAct,
                        case, result.cn, 29, 30
                    )
                }
            }
        }
    }

    private val case by lazy { intent.getSerializableExtra(Case) as Case }

    private val caseType by lazy { intent.getSerializableExtra(com.unicorn.forensic2.app.CaseType) as CaseType }

    private val colorPrimary by lazy { ContextCompat.getColor(this, R.color.colorPrimary) }

    override val layoutId = R.layout.act_case_detail

}