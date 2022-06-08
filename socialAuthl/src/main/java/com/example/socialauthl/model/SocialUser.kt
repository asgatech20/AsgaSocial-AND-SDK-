package com.example.socialauthl.model

import org.json.JSONObject

data class SocialUser(
    var id: String?,
    var name: String?,
    var email: String?,
    var fName: String?,
    var lName: String?,
    var birthday: String?,
    var imageUrl: String?,
) {
    companion object {
        fun fromJson(json: JSONObject): SocialUser {
            return SocialUser(
                id = if(json.has("id"))json.getString("id") else "",
                name = if(json.has("name"))json.getString("name") else "",
                email = if(json.has("email"))json.getString("email") else "",
                fName = if(json.has("first_name"))json.getString("first_name") else "",
                lName = if(json.has("last_name"))json.getString("last_name") else "",
                birthday = if(json.has("birthday"))json.getString("birthday") else "",
                imageUrl = json.getJSONObject("picture").getJSONObject("data").getString("url")
            )
        }
    }

    override fun toString(): String {
        return "SocialUser(${id}, ${name}, ${email}, ${fName}, ${lName}, ${imageUrl})"
    }
}
