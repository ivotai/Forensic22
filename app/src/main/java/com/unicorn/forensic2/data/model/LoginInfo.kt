package com.unicorn.forensic2.data.model

import com.chibatching.kotpref.KotprefModel

object LoginInfo : KotprefModel() {
    var loginStr by stringPref()
    var pwd by stringPref()
}