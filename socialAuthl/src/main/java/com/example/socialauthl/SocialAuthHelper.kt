package com.example.socialauthl

import android.app.Activity
import com.facebook.*


class SocialAuthHelper {

    companion object {
        val instance = SocialAuthHelper()
    }

    fun startLoginWithFaceBook(
        activity: Activity,
        callbackManager: CallbackManager,
        callBack: AuthCallBack,
        permissions: List<AuthPermissions>
    ) {
        FacebookAuth(callBack,activity, callbackManager, permissions).login()
    }

}