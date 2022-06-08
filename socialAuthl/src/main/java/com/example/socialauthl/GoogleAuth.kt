package com.example.socialauthl

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.socialauthl.model.SocialUser
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class GoogleAuth(
    private val callBack: AuthCallBack,
    private val activity: Activity,
):SocialAuthInterface {

    companion object{

    }

    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient

    override fun login() {
        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(activity, gso)
        val signInIntent = gsc.signInIntent
        activity.startActivityForResult(signInIntent, SocialAuthActivity.RC_GOOGLE_SIGN_IN)
    }

    fun handleSignInResult(data: Intent?, resultCode: Int) {
        if(resultCode != AppCompatActivity.RESULT_OK){
            callBack.onCancel()
            return
        }
        try {

            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            callBack.onSuccess(SocialUser.fromGoogleUser(account), listOf(), listOf())
        } catch (e: ApiException) {
            callBack.onError(e)
            Log.e("googleLog", e.message.toString())
            e.printStackTrace()
        }
    }
    override fun logout() {
        gsc.signOut()
    }
}