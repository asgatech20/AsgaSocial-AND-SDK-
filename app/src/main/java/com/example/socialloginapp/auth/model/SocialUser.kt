package com.example.socialloginapp.auth.model

import org.json.JSONObject

data class SocialUser(
    var id: String?,
    var name: String?,
    var email: String?,
    var fName: String?,
    var lName: String?,
    var imageUrl: String?,
) {
    companion object {
        fun fromJson(json: JSONObject): SocialUser {
            return SocialUser(
                id = if(json.has("id"))json.getString("id") else "",
                name = json.getString("name"),
                email = json.getString("email"),
                fName = json.getString("first_name"),
                lName = json.getString("last_name"),
                imageUrl = json.getJSONObject("picture").getJSONObject("data").getString("url")
            )
        }
    }

    override fun toString(): String {
        return "SocialUser(${id}, ${name}, ${email}, ${fName}, ${lName}, ${imageUrl})"
    }
}
