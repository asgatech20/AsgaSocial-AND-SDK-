package com.example.socialauthl.model

import com.example.socialauthl.AuthPermissions

object PermissionsParser {

    fun toFacePermissions(list:List<AuthPermissions>):List<String>{
        return  list.map { per-> per.value }
    }

    fun fromFacePermissions(list: Set<String?>): List<AuthPermissions> {
        val array = arrayListOf<AuthPermissions>()
        list.forEach { per ->
            per?.let { AuthPermissions.fromString(it) }?.let {
                array.add(it)
            }
        }
        return array;
    }
}