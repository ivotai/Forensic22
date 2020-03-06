package com.unicorn.forensic2.data.model

import java.io.Serializable

data class Jdjg(
    val bankaccount: String,
    val bankname: String,
    val bgdz: String,
    val clsj: Long,
    val fddb: String,
    val fddbSfzh: String,
    val fidbacl: String,
    val fidfrsfz: String,
    val fidxkzs: String,
    val fidyyzz: String,
    val fzr: String,
    val fzrDh: String,
    val fzrSj: String,
    val jdryList: List<Jdry>,
    val jgid: String,
    val jgmc: String,
    val jgmc2: String,
    val jgszd: String,
    val jgszdId: String,
    val jgxz: String,
    val jgxzId: String,
    val jgzzList: List<Jgzz>,
    val lxr: String,
    val lxrCz: String,
    val lxrDh: String,
    val lxrSj: String,
    val lxrYx: String,
    val xkzh: String,
    val yb: String,
    val yyzzh: String,
    val zczj: String,
    val zwpj: String,
    val zzlb: String,
    val zzlbId: String
): Serializable

data class Jdry(
    val alert: Boolean,
    val dataflag: Int,
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
): Serializable

data class Jgzz(
    val alert: Boolean,
    val cyly: String,
    val cylyId: String,
    val dataflag: Int,
    val fidzzzs: String,
    val fidzzzsmc: String,
    val jdlb: String,
    val jdlbId: String,
    val jgid: String,
    val spjg: String,
    val yxrq: Long,
    val zzdjId: String,
    val zzid: String,
    val zzsm: String,
    val zzzh: String
): Serializable