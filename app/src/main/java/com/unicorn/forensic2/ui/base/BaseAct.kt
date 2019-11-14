package com.unicorn.forensic2.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.unicorn.forensic2.app.di.ComponentHolder

abstract class BaseAct : AppCompatActivity(), UI {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initViews()
        bindIntent()
        registerEvent()
    }

    override fun initViews() {
    }

    override fun bindIntent() {
    }

    override fun registerEvent() {
    }

    protected val v1Api by lazy { ComponentHolder.appComponent.v1Api() }

}