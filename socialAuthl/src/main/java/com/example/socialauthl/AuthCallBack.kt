package com.example.socialauthl

import com.example.socialauthl.model.SocialUser

interface AuthCallBack {
    fun onSuccess(socialUser: SocialUser, grantedPermission:List<AuthPermissions>, blockedPermission:List<AuthPermissions>)
    fun onCancel()
    fun onError(throwable:Throwable)
}