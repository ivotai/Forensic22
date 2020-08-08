package com.unicorn.forensic2.refactor.profile

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.data.event.LogoutEvent
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.refactor.icon.Light
import com.unicorn.forensic2.ui.act.ExpertRegisterAct
import com.unicorn.forensic2.ui.act.JdjgMyGuideAct
import com.unicorn.forensic2.ui.act.JdjgRegisterAct
import com.unicorn.forensic2.ui.act.ModifyPwdAct
import com.unicorn.forensic2.ui.act.list.PersonalInfoUpdateAct
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_profile_operation.*

class ProfileOperationAdapter :
    BaseQuickAdapter<ProfileOperation, KVHolder>(R.layout.item_profile_operation) {

    override fun convert(helper: KVHolder, item: ProfileOperation) {
        helper.apply {
            tvText.text = item.text
            ivIcon.setIIcon(context, item.iIcon, R.color.colorPrimary)
            ivRight.setIIcon(context, Light.Icon.light_chevron_right, R.color.md_grey_300)


        }
        helper.root.safeClicks().subscribe {
            when (item) {
                ProfileOperation.ModifyPwd -> mContext.startAct(ModifyPwdAct::class.java)
                ProfileOperation.PersonInfo -> mContext.startAct(PersonalInfoUpdateAct::class.java)
                ProfileOperation.MyJdjg -> {
                    if (roleTag == Role.JdjgAdmin.en)
                        mContext.startAct(JdjgMyGuideAct::class.java)
                    else
                        ToastUtils.showShort("尚未注册机构")
                }
                ProfileOperation.RegisterJdjg -> mContext.startAct(JdjgRegisterAct::class.java)
                ProfileOperation.RegisterExpert -> mContext.startAct(ExpertRegisterAct::class.java)
                ProfileOperation.Logout -> RxBus.post(LogoutEvent())
                ProfileOperation.SwitchRole -> showRoleDialog()
            }
        }
    }

    private fun showRoleDialog(){
        val ens = user.roles
        val cns = ens.map { Role.en2Cn(it) }
        MaterialDialog(mContext).show {
            title(text = "选择角色")
            listItems(items = cns) { _, index, _ ->
                user.roleTag = ens[index]
                // 刷新登录状态
                RxBus.post(LoginStateChangeEvent())
                // 关闭登录界面
            }
        }
    }

}