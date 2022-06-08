package com.example.socialloginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.socialauthl.AuthCallBack
import com.example.socialauthl.AuthPermissions
import com.example.socialauthl.SocialAuthHelper
import com.example.socialauthl.model.SocialUser
import com.example.socialloginapp.databinding.ActivityMainBinding
import com.facebook.CallbackManager
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var callbackManager = CallbackManager.Factory.create()
    private val facePermissions = listOf(
        AuthPermissions.EMAIL,
        AuthPermissions.PUBLIC_PROFILE,
        AuthPermissions.USER_BIRTH_DAY
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            SocialAuthHelper.instance.startLoginWithFaceBook(this, callbackManager, object :
                AuthCallBack {
                override fun onSuccess(
                    socialUser: SocialUser,
                    granted:List<AuthPermissions>,
                    block:List<AuthPermissions>
                ) {

                    Log.e("facebook onSuccess", socialUser.toString())
                    Log.e("facebook onSuccess", granted.toString())
                }

                override fun onCancel() {
                    Snackbar.make(binding.fab, "Replace ReplaceReplaceReplace", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                override fun onError(throwable: Throwable) {

                }
            },
            permissions = facePermissions)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}