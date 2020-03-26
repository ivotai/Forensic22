package com.unicorn.forensic2.ui.act

import androidx.core.content.ContextCompat
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.data.event.LogoutEvent
import com.unicorn.forensic2.data.event.ShowCaseEvent
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
        RxBus.registerEvent(this, LogoutEvent::class.java, Consumer {
            isLogin = false
            RxBus.post(LoginStateChangeEvent())
        })
        RxBus.registerEvent(this, LoginStateChangeEvent::class.java, Consumer {
            mainPagerAdapter.notifyDataSetChanged()
            notifyTabs()
        })
        RxBus.registerEvent(this, ShowCaseEvent::class.java, Consumer {
            viewPaper.setCurrentItem(1, true)
        })
    }

    private fun notifyTabs() {
        fun newItem(drawableRes: Int, checkedDrawableRes: Int, text: String) =
            NormalItemView(this).apply {
                initialize(drawableRes, checkedDrawableRes, text)
                setTextDefaultColor(colorDefault)
                setTextCheckedColor(colorPrimary)
            }

        val customBuilder = navigation.custom()
        customBuilder.apply {
            addItem(
                newItem(
                    R.mipmap.home,
                    R.mipmap.home_select,
                    "首页"
                )
            )
            if (isLogin) {
                addItem(
                    newItem(
                        R.mipmap.casez,
                        R.mipmap.case_select,
                        "案件"
                    )
                )
                addItem(
                    newItem(
                        R.mipmap.my,
                        R.mipmap.my_select,
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