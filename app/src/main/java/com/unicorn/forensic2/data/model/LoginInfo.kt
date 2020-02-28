package com.unicorn.forensic2.data.model

import com.chibatching.kotpref.KotprefModel

object LoginInfo : KotprefModel() {
    var username by stringPref()
    var password by stringPref()
}