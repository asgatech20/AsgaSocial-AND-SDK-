package com.example.socialloginapp.auth

import com.example.socialloginapp.auth.model.SocialUser

interface AuthCallBack {
    fun onSuccess(socialUser: SocialUser, grantedPermission:List<AuthPermissions>, blockedPermission:List<AuthPermissions>)
    fun onCancel()
    fun onError(throwable:Throwable)
}