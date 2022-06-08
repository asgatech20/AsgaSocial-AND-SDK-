package com.example.socialauthl

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.socialauthl.model.PermissionsParser.fromFacePermissions
import com.example.socialauthl.model.PermissionsParser.toFacePermissions
import com.example.socialauthl.model.SocialUser
import com.example.socialauthl.model.Utility.getBlockedPermissions
import com.example.socialauthl.model.Utility.getFaceParamsFromPermissions
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import org.json.JSONException

class FacebookAuth(private val callBack: AuthCallBack,
                   private val activity: Activity,
                   private val callbackManager: CallbackManager,
                   private val permissions: List<AuthPermissions>):SocialAuthInterface {
    private lateinit var loginManager:LoginManager
    override fun login(
    ) {
        registerWithFaceBook()
    }

    private fun registerWithFaceBook(){
        val loginManager = LoginManager.getInstance()
        loginManager.logOut()
        loginManager.logInWithReadPermissions(activity, toFacePermissions(permissions))
        loginManager.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    Log.e("facebook", "onSuccess")
                    Log.e("facebook", result.toString())
                    requestDataFromGraphAPI(result)
                }

                override fun onCancel() {
                    // App code
                    Log.e("facebook auth2", "onCancel")
                    callBack.onCancel()
                }

                override fun onError(exception: FacebookException) {

                    // App code
                    Log.e("facebook auth2", exception.toString())
                    callBack.onError(exception)
                }
            })
    }
    private fun requestDataFromGraphAPI(loginResult: LoginResult){
        val request = GraphRequest.newMeRequest(
            loginResult.accessToken
        ) { json, result ->
            try {
                if(json!=null) {
                    Log.e("GraphAPI json", json.toString())
                    Log.e("GraphAPI result", result.toString())
                    AccessToken.getCurrentAccessToken()?.let {
                        val granted = fromFacePermissions(it.permissions);
                        callBack.onSuccess(
                            SocialUser.fromJson(json),
                            granted,
                            getBlockedPermissions(permissions,granted)
                        )
                    };
                }
                else
                    callBack.onError(Throwable(message = "cant get user data"))
            }catch (e:JSONException){
                callBack.onError(e)
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", getFaceParamsFromPermissions(permissions))
        request.parameters = parameters
        request.executeAsync()
    }

    override fun logout() {
        val loginManager = LoginManager.getInstance()
        loginManager.logOut()
    }

}