package com.unicorn.forensic2.app

import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.data.model.Dict
import okhttp3.MediaType.Companion.toMediaType

const val Fy = "Fy"
const val Cyly = "Cyly"
const val Jdlb = "Jdlb"
const val Xtgg = "Xtgg"
const val JgzzAddParam = "JgzzAddParam"
const val Cookie = "Cookie"
const val SESSION = "SESSION"
const val Jdjg = "Jdjg"
const val JdjgId = "JdjgId"
const val TsjyId = "TsjyId"
const val Param = "Param"
const val Case = "Case"
const val CaseType = "CaseType"
const val V1 = "V1"
val TextOrPlain = "text/plain".toMediaType()

const val receiveDateFormat = "yyyy-MM-dd'T'HH:mm:ss"

const val defaultPageSize = 5

val defaultPadding = ConvertUtils.dp2px(16f)

const val originalBaseUrl = "http://113.142.60.13:8086/"
const val baseUrl = "${originalBaseUrl}sfjdwww_test/"
//const val baseUrl = "http://113.142.60.13:8080/sfjdwww/"

const val pictureBaseUrl = "${baseUrl}sysFileinfo/download/"

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

val shjgList = listOf(
    Dict("0", "待审核"),
    Dict("1", "审核通过"),
    Dict("2", "审核不通过"),
    Dict("3", "待公示"),
    Dict("4", "公示中"),
    Dict("5", "公示通过"),
    Dict("6", "公示不通过"),
    Dict("8", "暂停")
)

val ajlxList = listOf(
    Dict("1", "鉴定委托"),
    Dict("2", "保外就医审核"),
    Dict("3", "技术咨询"),
    Dict("4", "技术审核"),
    Dict("5", "诉前鉴定"),
    Dict("9", "其他")
)
