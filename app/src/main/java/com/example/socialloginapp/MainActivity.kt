package com.example.socialloginapp

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.bumptech.glide.Glide
import com.example.socialauthl.AuthCallBack
import com.example.socialauthl.AuthPermissions
import com.example.socialauthl.SocialAuthActivity
import com.example.socialauthl.model.SocialUser
import com.example.socialloginapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : SocialAuthActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val facePermissions = listOf(
        AuthPermissions.EMAIL,
        AuthPermissions.PUBLIC_PROFILE,
        AuthPermissions.BIRTH_DAY
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.loginFace.setOnClickListener { view ->
            loginFace()
        }

        binding.logoutFace.setOnClickListener { view ->
            logoutFacebook()
        }
        binding.loginGoogle.setOnClickListener { view ->
            loginGoogle()
        }
        binding.logoutGoogle.setOnClickListener { view ->
            logoutGoogle()
        }

    }

    private fun loginFace() {
        startLoginWithFaceBook(
            object :
                AuthCallBack {
                override fun onSuccess(
                    socialUser: SocialUser,
                    granted: List<AuthPermissions>,
                    block: List<AuthPermissions>
                ) {

                    Log.e("facebook onSuccess", socialUser.toString())
                    Log.e("facebook onSuccess", granted.toString())
                    binding.loginType.text = "Google"
                    updateUi(user = socialUser)
                }

                override fun onCancel() {
                    Snackbar.make(
                        binding.fab,
                        "Replace ReplaceReplaceReplace",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()
                }

                override fun onError(throwable: Throwable) {

                }
            },
            permissions = facePermissions
        )
    }

    private fun loginGoogle() {
        startLoginWithGoogle(
            object :
                AuthCallBack {
                override fun onSuccess(
                    socialUser: SocialUser,
                    granted: List<AuthPermissions>,
                    block: List<AuthPermissions>
                ) {

                    Log.e("facebook onSuccess", socialUser.toString())
                    Log.e("facebook onSuccess", granted.toString())
                    binding.loginType.text = "Google"
                    updateUi(user = socialUser)
                }

                override fun onCancel() {
                    Snackbar.make(
                        binding.fab,
                        "Replace ReplaceReplaceReplace",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()
                }

                override fun onError(throwable: Throwable) {
                    Snackbar.make(
                        binding.fab,
                        throwable.message.toString(),
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()

                }
            },
        )
    }

    fun updateUi(user:SocialUser){
        binding.userName.text = user.name
        binding.userObject.text = user.toString()

        Glide
            .with(this)
            .load(user.imageUrl)
            .circleCrop()
            .into(binding.userImage);
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}