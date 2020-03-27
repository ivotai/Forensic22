package com.unicorn.forensic2.ui.act.addOrEdit

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.widget.textChanges
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.helper.PictureHelper
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.data.model.Zjzz
import com.unicorn.forensic2.data.model.param.addOrEdit.ZjzzEditParam
import com.unicorn.forensic2.ui.act.tree.JdlbTreeAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_zjzz_add_or_edit.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ZjzzEditAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("编辑专家资质")

        fun display() = with(zjzz){
            tvJdlb.text = jdlb
            etGrzc.setText(grzc)
            etZczyh.setText(zczyh)
            tvZyzsyxq.text =zyzsyxq.toDisplayFormat()
            PictureHelper.load(this@ZjzzEditAct,"$pictureBaseUrl${fidzyzsid}").into(ivZyzs)
            PictureHelper.load(this@ZjzzEditAct,"$pictureBaseUrl${fidzczsid}").into(ivZczs)
        }
        display()
    }

    override fun bindIntent() {
        fun initZjzzEditParam() = with(zjzz){
            zjzzEditParam = ZjzzEditParam(
                jdlbId = jdlbId,
                grzc = grzc,
                zczyh = zczyh,
                zyzsyxq = zyzsyxq.toDisplayFormat()
            )
        }
        initZjzzEditParam()

        tvJdlb.safeClicks().subscribe { startAct(JdlbTreeAct::class.java) }
        etGrzc.textChanges().map { it.toString() }.subscribe { zjzzEditParam.grzc = it }
        etZczyh.textChanges().map { it.toString() }.subscribe { zjzzEditParam.zczyh = it }
        tvZyzsyxq.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    val dateStr = date.time.time.toDisplayFormat()
                    zjzzEditParam.zyzsyxq = dateStr
                    this@ZjzzEditAct.tvZyzsyxq.text = dateStr
                }
            }
        }
        ivZyzs.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@ZjzzEditAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        zjzzEditParam.fid_zyzs = realPath
                        Glide.with(this@ZjzzEditAct).load(realPath).into(ivZyzs)
                    }

                    override fun onCancel() {
                    }
                })
        }
        ivZczs.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@ZjzzEditAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        zjzzEditParam.fid_zczs = realPath
                        Glide.with(this@ZjzzEditAct).load(realPath).into(ivZczs)
                    }

                    override fun onCancel() {
                    }
                })
        }

        fun save() = with(zjzzEditParam) {
            val map = HashMap<String, RequestBody>()
            map["jdlbId"] = jdlbId.toRequestBody(TextOrPlain)
            map["zczyh"] = zczyh.toRequestBody(TextOrPlain)
            map["grzc"] = grzc.toRequestBody(TextOrPlain)
            map["zyzsyxq"] = zyzsyxq.toRequestBody(TextOrPlain)

            var part1: MultipartBody.Part? = null
            var part2: MultipartBody.Part? = null
            if (fid_zyzs.isNotBlank()) {
                part1 = MultipartBody.Part.createFormData(
                    "fid_zyzs",
                    File(fid_zyzs).name,    // 不能为空
                    File(fid_zyzs).asRequestBody("image/*".toMediaType())
                )
            }

            if (fid_zczs.isNotBlank()) {
                part2 = MultipartBody.Part.createFormData(
                    "fid_zczs",
                    File(fid_zczs).name,
                    File(fid_zczs).asRequestBody("image/*".toMediaType())
                )
            }
            val mask = DialogHelper.showMask(this@ZjzzEditAct)
            v1Api.addZjzz(map, part1, part2)
                .observeOnMain(this@ZjzzEditAct)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        if (!it.success) {
                            ToastUtils.showShort("保存失败")
                            return@subscribeBy
                        }
                        ToastUtils.showShort("保存成功")
                        finish()
                        RxBus.post(RefreshEvent())
                    },
                    onError = {
                        mask.dismiss()
                        ToastUtils.showShort("保存失败")
                    }
                )
        }

        titleBar.setOperation("保存").safeClicks().subscribe {
            with(zjzzEditParam) {
                if (jdlbId.isBlank()) {
                    ToastUtils.showShort("请选择鉴定类别")
                    return@subscribe
                }
                if (zczyh.isBlank()) {
                    ToastUtils.showShort("请选择注册执业号")
                    return@subscribe
                }
                if (grzc.isBlank()) {
                    ToastUtils.showShort("请选择个人职称")
                    return@subscribe
                }
                if (zyzsyxq.isBlank()) {
                    ToastUtils.showShort("请选择有效日期")
                    return@subscribe
                }
            }
            save()
        }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            zjzzEditParam.jdlbId = it.dict.id
            tvJdlb.text = it.dict.name
        })
    }

    private lateinit var zjzzEditParam:ZjzzEditParam

    private val zjzz by  lazy { intent.getSerializableExtra(Param) as Zjzz }

    override val layoutId = R.layout.act_zjzz_add_or_edit

}