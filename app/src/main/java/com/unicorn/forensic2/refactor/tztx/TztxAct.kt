package com.unicorn.forensic2.refactor.tztx

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_tztx.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView

class TztxAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("通知提醒")

        val pagerAdapter = TztxPagerAdapter(fm = supportFragmentManager)
        viewPaper.adapter = pagerAdapter

        initMagicIndicator1()
    }

    private lateinit var commonNavigator: CommonNavigator

    private fun initMagicIndicator1() {
        commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return 2
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val badgePagerTitleView = BadgePagerTitleView(context)
                val simplePagerTitleView: SimplePagerTitleView =
                    ColorTransitionPagerTitleView(context)
                simplePagerTitleView.setText(if (index == 0) "未读" else "已读")
                simplePagerTitleView.normalColor = Color.BLACK
                simplePagerTitleView.selectedColor = colorPrimary
                simplePagerTitleView.setOnClickListener {
                    viewPaper.setCurrentItem(index)
//                    badgePagerTitleView.badgeView = null // cancel badge when click tab
                }
                badgePagerTitleView.innerPagerTitleView = simplePagerTitleView

                // setup badge
//                if (index != 2) {
//                    val badgeTextView = LayoutInflater.from(context)
//                        .inflate(R.layout.simple_count_badge_layout, null) as TextView
//                    badgeTextView.text = "" + (index + 1)
//                    badgePagerTitleView.badgeView = badgeTextView
//                } else {
//                    val badgeImageView = LayoutInflater.from(context)
//                        .inflate(
//                            R.layout.simple_red_dot_badge_layout,
//                            null
//                        ) as ImageView
//                    badgePagerTitleView.badgeView = badgeImageView
//                }
//
//                // set badge position
//                if (index == 0) {
//                    badgePagerTitleView.xBadgeRule = BadgeRule(
//                        BadgeAnchor.CONTENT_LEFT,
//                        -UIUtil.dip2px(context, 6.0)
//                    )
//                    badgePagerTitleView.yBadgeRule = BadgeRule(BadgeAnchor.CONTENT_TOP, 0)
//                } else if (index == 1) {
//                    badgePagerTitleView.xBadgeRule = BadgeRule(
//                        BadgeAnchor.CONTENT_RIGHT,
//                        UIUtil.dip2px(context, 0.0)
//                    )
//                    badgePagerTitleView.yBadgeRule = BadgeRule(BadgeAnchor.CONTENT_TOP, 0)
//                } else if (index == 2) {
//                    badgePagerTitleView.xBadgeRule = BadgeRule(
//                        BadgeAnchor.CENTER_X,
//                        -UIUtil.dip2px(context, 3.0)
//                    )
//                    badgePagerTitleView.yBadgeRule = BadgeRule(
//                        BadgeAnchor.CONTENT_BOTTOM,
//                        UIUtil.dip2px(context, 2.0)
//                    )
//                }
//
//                // don't cancel badge when tab selected
//                badgePagerTitleView.isAutoCancelBadge = false
                return badgePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.setColors(colorPrimary)
                return indicator
            }
        }
        commonNavigator.isAdjustMode = true
        magicIndicator.navigator = commonNavigator
        val titleContainer =
            commonNavigator.titleContainer // must after setNavigator
        ViewPagerHelper.bind(magicIndicator, viewPaper)
    }

    private var badge: Badge? = null

    override fun registerEvent() {
        RxBus.registerEvent(this, TotalElementsEvent::class.java, Consumer {
            if (it.index == 1) return@Consumer
            // 如果已经有 badge ，隐藏
            badge?.hide(false)
            val badgePagerTitleView =
                commonNavigator.getPagerTitleView(it.index) as BadgePagerTitleView
            val simplePagerTitleView =
                badgePagerTitleView.innerPagerTitleView as SimplePagerTitleView
            badge =
                QBadgeView(this).bindTarget(simplePagerTitleView).setBadgeNumber(it.totalElements)
                    .setGravityOffset(120f, 20f, false)
        })
    }

    private val colorPrimary by lazy { ContextCompat.getColor(this, R.color.colorPrimary) }

    override val layoutId = R.layout.act_tztx

}