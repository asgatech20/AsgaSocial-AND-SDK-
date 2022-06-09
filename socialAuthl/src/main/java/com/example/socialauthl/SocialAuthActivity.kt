package com.example.socialauthl


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager


open class SocialAuthActivity : AppCompatActivity() {
    var callbackManager = CallbackManager.Factory.create()

    companion object {
        const val RC_GOOGLE_SIGN_IN = 1000
        val instance = SocialAuthActivity()
    }


    var facebookAuth:FacebookAuth? = null
    var googleAuth:GoogleAuth? = null

    fun startLoginWithFaceBook(
        callBack: AuthCallBack,
        permissions: List<AuthPermissions>
    ) {
        facebookAuth = FacebookAuth(callBack, this@SocialAuthActivity, callbackManager, permissions)
        logoutFacebook()
        facebookAuth?.login()
    }

    fun logoutFacebook() {
        facebookAuth?.logout()
    }

    fun startLoginWithGoogle(callBack: AuthCallBack
    ) {
        googleAuth = GoogleAuth(callBack, this@SocialAuthActivity)
        googleAuth?.login()
    }

    fun logoutGoogle() {
        googleAuth?.logout()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // facebook code
        callbackManager.onActivityResult(requestCode, resultCode, data)

        // google code
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            googleAuth?.handleSignInResult(data,resultCode);
        }
    }

}