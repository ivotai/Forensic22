package com.unicorn.forensic2.ui

import androidx.core.content.ContextCompat
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.ui.adapter.MainPagerAdapter
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_main.*
import me.majiajie.pagerbottomtabstrip.item.NormalItemView

class MainAct : BaseAct() {

    lateinit var mainPagerAdapter: MainPagerAdapter

    override fun initViews() {
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        viewPaper.adapter = mainPagerAdapter
        viewPaper.offscreenPageLimit = mainPagerAdapter.count - 1
        notifyTabs()
    }

    override fun registerEvent() {
        // TODO 修改密码后是否需要重新登录
//        RxBus.registerEvent(this, NeedLoginEvent::class.java, Consumer {
//            ActivityUtils.finishAllActivities()
//            startAct(LoginAct::class.java)
//        })

        RxBus.registerEvent(this, LoginStateChangeEvent::class.java, Consumer {
            mainPagerAdapter.notifyDataSetChanged()
            notifyTabs()
        })
    }

    private fun notifyTabs() {

        fun newItem(drawable: Int, checkedDrawable: Int, text: String) =
            NormalItemView(this).apply {
                initialize(drawable, checkedDrawable, text)
                setTextDefaultColor(colorDefault)
                setTextCheckedColor(colorPrimary)
            }

        val customBuilder = navigation.custom()
        customBuilder.apply {
            addItem(
                newItem(
                    R.drawable.ic__ionicons_svg_ios_home,
                    R.drawable.ic__ionicons_svg_ios_home2,
                    "首页"
                )
            )
            if (isLogin) {
                addItem(
                    newItem(
                        R.drawable.ic__ionicons_svg_ios_albums,
                        R.drawable.ic__ionicons_svg_ios_albums2,
                        "案件"
                    )
                )
                addItem(
                    newItem(
                        R.drawable.ic__ionicons_svg_ios_contact,
                        R.drawable.ic__ionicons_svg_ios_contact2,
                        "我的"
                    )
                )
            }
        }
        customBuilder.build().setupWithViewPager(viewPaper)
    }

    private val colorPrimary by lazy { ContextCompat.getColor(this@MainAct, R.color.colorPrimary) }
    private val colorDefault by lazy { ContextCompat.getColor(this@MainAct, R.color.colorDefault) }

    override val layoutId = R.layout.act_main

}