package com.example.socialauthl

enum class AuthPermissions(val value:String) {
    EMAIL("email"),
    PUBLIC_PROFILE("public_profile"),
    USER_BIRTH_DAY("user_birthday ");

    companion object {
        private val map = values().associateBy(AuthPermissions::value)
        fun fromString(type: String) = map[type]
    }
}