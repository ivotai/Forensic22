package com.unicorn.forensic2.refactor.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class LoginFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = titles.size

    override fun createFragment(position: Int): Fragment =
        if (position == 0) LoginFra() else CourtLoginFra()

    companion object {
        val titles = listOf("用户登录", "法院登录")
    }

}