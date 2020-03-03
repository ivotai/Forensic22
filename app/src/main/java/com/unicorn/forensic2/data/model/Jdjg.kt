package com.unicorn.forensic2.data.model

data class Jdjg(
    val jdryList: List<Any>,
    val jgid: String,
    val jgmc: String,
    val jgszd: String,
    val jgzzList: List<Jgzz>
)

data class Jgzz(
    val alert: Boolean,
    val cylyId: String,
    val dataflag: Int,
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
)