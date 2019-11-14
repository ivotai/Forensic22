package com.unicorn.forensic2.app.ui.base

interface UI {

    val layoutId: Int

    fun initViews()

    fun bindIntent()

    fun registerEvent()

}