package com.unicorn.forensic2.refactor.profile

import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.data.event.LogoutEvent
import com.unicorn.forensic2.data.model.MyMenu
import com.unicorn.forensic2.ui.act.ExpertRegisterAct
import com.unicorn.forensic2.ui.act.JdjgMyGuideAct
import com.unicorn.forensic2.ui.act.JdjgRegisterAct
import com.unicorn.forensic2.ui.act.ModifyPwdAct
import com.unicorn.forensic2.ui.act.list.PersonalInfoUpdateAct
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_profile_operation.*

class ProfileOperationAdapter : BaseQuickAdapter<ProfileOperation, KVHolder>(R.layout.item_profile_operation) {

    override fun convert(helper: KVHolder, item: ProfileOperation) {
        helper.apply {
            tvText.text = item.text

            root.safeClicks().subscribe {
                when (item) {
                    MyMenu.ModifyPwd -> mContext.startAct(ModifyPwdAct::class.java)
                    MyMenu.PersonInfo -> mContext.startAct(PersonalInfoUpdateAct::class.java)
                    MyMenu.RegisterJdjg -> mContext.startAct(JdjgRegisterAct::class.java)
                    MyMenu.MyJdjg -> {
                        if (user.JdjgAdmin)
                            mContext.startAct(JdjgMyGuideAct::class.java)
                        else
                            ToastUtils.showShort("尚未注册机构")
                    }
                    MyMenu.RegisterExpert -> mContext.startAct(ExpertRegisterAct::class.java)
                    MyMenu.Logout -> RxBus.post(LogoutEvent())
                }
            }
        }
    }

}