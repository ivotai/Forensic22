package com.unicorn.forensic2.ui

import androidx.core.content.ContextCompat
import com.unicorn.forensic2.R
import com.unicorn.forensic2.ui.adapter.MainPagerAdapter
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_main.*
import me.majiajie.pagerbottomtabstrip.item.NormalItemView

class MainAct : BaseAct() {

    override fun initViews() {
        viewPaper.adapter = MainPagerAdapter(supportFragmentManager)
        viewPaper.offscreenPageLimit = MainPagerAdapter.size - 1

        fun newItem(drawable: Int, checkedDrawable: Int, text: String) =
            NormalItemView(this).apply {
                initialize(drawable, checkedDrawable, text)
                setTextDefaultColor(colorDefault)
                setTextCheckedColor(colorPrimary)
            }

        val navigationController = navigation.custom()
            .addItem(
                newItem(
                    R.drawable.ic__ionicons_svg_ios_home,
                    R.drawable.ic__ionicons_svg_ios_home2,
                    "首页"
                )
            )
            .addItem(
                newItem(
                    R.drawable.ic__ionicons_svg_ios_albums,
                    R.drawable.ic__ionicons_svg_ios_albums2,
                    "案件"
                )
            )
            .addItem(
                newItem(
                    R.drawable.ic__ionicons_svg_ios_contact,
                    R.drawable.ic__ionicons_svg_ios_contact2,
                    "我的"
                )
            )
            .build()
        navigationController.setupWithViewPager(viewPaper)
    }

    override fun registerEvent() {
        // TODO 修改密码后是否需要重新登录
//        RxBus.registerEvent(this, NeedLoginEvent::class.java, Consumer {
//            ActivityUtils.finishAllActivities()
//            startAct(LoginAct::class.java)
//        })
    }

    private val colorPrimary by lazy { ContextCompat.getColor(this@MainAct, R.color.colorPrimary) }
    private val colorDefault by lazy { ContextCompat.getColor(this@MainAct, R.color.colorDefault) }

    override val layoutId = R.layout.act_main

}