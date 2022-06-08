package com.example.socialauthl.model

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import org.json.JSONObject

data class SocialUser(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var fName: String = "",
    var lName: String = "",
    var birthday: String = "",
    var imageUrl: String = "",
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

        fun fromGoogleUser(account:GoogleSignInAccount):SocialUser{
            return SocialUser(
                id = if(account.id!=null)account.id!! else "",
                name =  if(account.displayName!=null)account.displayName!! else "",
                email =  if(account.email!=null)account.email!! else "",
                fName =  if(account.givenName!=null)account.givenName!! else "",
                lName =  if(account.familyName!=null)account.familyName!! else "",
                birthday = "",
                imageUrl = if(account.photoUrl!=null)account.photoUrl?.toString()!! else ""
            )
        }
    }

    override fun toString(): String {
        return "SocialUser(${id}, ${name}, ${email}, ${fName}, ${lName}, ${imageUrl})"
    }
}
