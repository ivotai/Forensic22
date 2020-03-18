package com.unicorn.forensic2.data.model

import java.io.Serializable

data class Jdjg(
    var bankaccount: String = "",
    var bankname: String = "",
    var bgdz: String = "",
    var clsj: Long = 0,
    var fddb: String = "",
    var fddbSfzh: String = "",
    var fidbacl: String = "",
    var fidfrsfz: String = "",
    var fidxkzs: String = "",
    var fidyyzz: String = "",
    var fzr: String = "",
    var fzrDh: String = "",
    var fzrSj: String = "",
    val jdryList: List<Jdry>,
    var jgid: String = "",
    var jgmc: String = "",
    var jgmc2: String = "",
    var jgszd: String = "",
    var jgszdId: String = "",
    var jgxz: String = "",
    var jgxzId: String = "",
    var jgzzList: List<Jgzz>,
    var lxr: String = "",
    var lxrCz: String = "",
    var lxrDh: String = "",
    var lxrSj: String = "",
    var lxrYx: String = "",
    var xkzh: String = "",
    var yb: String = "",
    var yyzzh: String = "",
    var zczj: String = "",
    var zwpj: String = "",
    var zzlb: String = "",
    var zzlbId: String = ""
) : Serializable

data class Jdry(
    val fidzczs: String,
    val fidzczsmc: String,
    val fidzyzs: String,
    val fidzyzsmc: String,
    val grzc: String,
    val jdlb: String,
    val jdlbId: String,
    val jdryid: String,
    val jgid: String,
    val mphone: String,
    val xm: String,
    val zczyh: String,
    val zjhm: String,
    val zjlx: String,
    val zyzsyxq: Long
) : Serializable

data class Jgzz(
    val cyly: String,
    val cylyId: String,
    val fidzzzs: String,
    val fidzzzsmc: String,
    val jdlb: String,
    val jdlbId: String,
    val jgid: String,
    val spjg: String,
    val yxrq: Long,
    val zzdj: String,
    val zzdjId: String,
    val zzid: String,
    val zzsm: String,
    val zzzh: String
) : Serializable