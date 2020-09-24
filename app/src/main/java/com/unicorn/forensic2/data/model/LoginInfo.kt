package com.unicorn.forensic2.data.model

import com.chibatching.kotpref.KotprefModel

object LoginInfo : KotprefModel() {
    var username by stringPref()
    var password by stringPref()
    var dm by stringPref()
    var dmms by stringPref()
    val isCourtLogin get() = dm.isNotEmpty()
}
