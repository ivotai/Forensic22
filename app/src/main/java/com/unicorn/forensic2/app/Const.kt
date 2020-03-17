package com.unicorn.forensic2.app

import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.data.model.Dict
import okhttp3.MediaType.Companion.toMediaType

const val Cyly = "Cyly"
const val Jdlb = "Jdlb"
const val Xtgg = "Xtgg"
const val JgzzAddParam = "JgzzAddParam"
const val Cookie = "Cookie"
const val SESSION = "SESSION"
const val Jdjg = "Jdjg "
const val JdjgId = "JdjgId"
const val TsjyId = "TsjyId"
const val Param = "Param"
const val V1 = "V1"
val TextOrPlain = "text/plain".toMediaType()

const val receiveDateFormat = "yyyy-MM-dd'T'HH:mm:ss"

const val defaultPageSize = 5

val defaultPadding = ConvertUtils.dp2px(16f)

//const val originalBaseUrl = "http://renjiawen1988.vicp.cc:33333/JudicialExpertise" +
//        "V3Public/"
const val baseUrl = "http://192.168.7.207:3500/sfjdwww/"
const val pictureBaseUrl = "${baseUrl}sysFileinfo/download/"

//const val baseUrl2 = "http://renjiawen1988.vicp.cc:33333/JudicialExpertiseV3/api/"

const val displayDateFormat = "yyyy-MM-dd"

const val displayDateFormat2 = "yyyy-MM-dd HH:mm:ss"

const val verifyCodeCount = 60L

val zjlxList = listOf(
    Dict("1", "身份证"),
    Dict("2", "军官证"),
    Dict("3", "士兵证"),
    Dict("4", "武警证"),
    Dict("5", "港澳台居民身份证"),
    Dict("6", "有效护照"),
    Dict("7", "组织机构代码"),
    Dict("8", "其他")
)

