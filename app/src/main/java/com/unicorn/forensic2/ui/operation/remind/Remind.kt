package com.unicorn.forensic2.ui.operation.remind

import com.unicorn.forensic2.app.roleTag
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.data.model.UploadFile
import java.io.Serializable

data class Remind(
    val caseId: String,
    val fileInfo: UploadFile?,
    val fileid: String,
    val fromFlag: Int,
    val jgid: String,
    val lastUpdateDate: Long,
    val remark: String,
    val remindDttm: Long,
    val acceptDttm: Long,
    val rid: String,
    val toFlag: Int
) : Serializable {
    companion object {
        private val map = HashMap<Int, String>().apply {
            this[1] = "办案法官"
            this[2] = "司技室"
            this[3] = "鉴定机构"
        }


        val currentFromFlag
            get() = when (roleTag) {
                Role.Spry.en -> 1
                Role.Sfjd.en -> 2
                Role.JdjgAdmin.en -> 3
                else -> 0
            }
        //to也看身份，法官的，只能发给司技室，机构只能发给司技室
//        司技室得选了
//       选另外两个

        val currentToFlag
            get() = when (roleTag) {
                Role.Spry.en -> 2
                Role.JdjgAdmin.en -> 2
                Role.Sfjd.en -> 1 // to 1,3
                else -> 0
            }

        val currentFromFlagStr get() = map[currentFromFlag]
    }

    val fromFlagStr get() = map[fromFlag]
    val toFlagStr get() = map[toFlag]

}

