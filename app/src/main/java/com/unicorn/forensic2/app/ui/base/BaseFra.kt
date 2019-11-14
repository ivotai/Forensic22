package com.unicorn.forensic2.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.unicorn.forensic2.app.di.ComponentHolder

abstract class BaseFra : Fragment(), UI {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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