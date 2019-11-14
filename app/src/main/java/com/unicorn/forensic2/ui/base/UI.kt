package com.unicorn.forensic2.ui.base

interface UI {

    val layoutId: Int

    fun initViews()

    fun bindIntent()

    fun registerEvent()

}