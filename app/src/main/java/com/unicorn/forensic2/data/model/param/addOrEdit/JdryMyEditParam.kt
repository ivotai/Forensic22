package com.unicorn.forensic2.data.model.param.addOrEdit

import java.io.Serializable

data class JdryMyEditParam(
    val objectId: String,
    var xm: String = "",
    var zjlx: String = "",
    var zjhm: String = "",
    var zczyh: String = "",
    var grzc: String = "",
    var mphone: String = "",
    var jdlbId: String = "",
    var zyzsyxq: String = "",
    var fidzczs_new: String = "",
    var fidzyzs_new: String = ""
) : Serializable