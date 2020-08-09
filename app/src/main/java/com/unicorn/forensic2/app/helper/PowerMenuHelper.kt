package com.unicorn.forensic2.app.helper

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.skydoves.powermenu.MenuAnimation
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import com.unicorn.forensic2.R

object PowerMenuHelper {

    fun get(context: Context, itemList: List<PowerMenuItem>): PowerMenu {
        return PowerMenu.Builder(context)
            .addItemList(itemList)
            .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
            .setMenuRadius(10f)
            .setMenuShadow(10f)
            .setTextSize(14)
            .setTextColor(ContextCompat.getColor(context, R.color.md_grey_800))
            .setTextGravity(Gravity.CENTER)
            .setSelectedTextColor(Color.WHITE)
            .setMenuColor(Color.WHITE)
            .setSelectedMenuColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .setAutoDismiss(true)
            .build()
    }

}