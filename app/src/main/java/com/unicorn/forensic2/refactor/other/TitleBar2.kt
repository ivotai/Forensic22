package com.unicorn.forensic2.refactor.other

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.blankj.utilcode.util.ActivityUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.setIIcon
import com.unicorn.forensic2.refactor.icon.Light
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.title_bar2.view.*

class TitleBar2(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs),
    LayoutContainer {

    override val containerView = this

    init {
        LayoutInflater.from(context).inflate(R.layout.title_bar2, this, true)
        ivBack.setIIcon(context, Light.Icon.light_chevron_left, R.color.md_black)
        flBack.safeClicks().subscribe {
            ActivityUtils.getTopActivity().finish()
        }
    }

    fun setTitle(title: String, allowBack: Boolean = true) {
        tvTitle.text = title
        flBack.visibility = if (allowBack) View.VISIBLE else View.INVISIBLE
    }

    fun setOperation(operation: String): TextView = tvOperation.apply {
        visibility = View.VISIBLE
        text = operation
    }

}