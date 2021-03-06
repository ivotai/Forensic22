package com.unicorn.forensic2.ui.other

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.blankj.utilcode.util.ActivityUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.title_bar.view.*

class TitleBar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs),
    LayoutContainer {

    override val containerView = this

    init {
        LayoutInflater.from(context).inflate(R.layout.title_bar, this, true)
        flBack.safeClicks().subscribe {
            ActivityUtils.getTopActivity().finish()
        }
    }

    fun setTitle(title: String, allowBack: Boolean = true) {
        tvTitle.text = title
        flBack.visibility = if (allowBack) View.VISIBLE else View.INVISIBLE
    }

    fun setOperation(operation: String) = tvOperation.apply {
        visibility = View.VISIBLE
        text = operation
    }

}