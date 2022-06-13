package com.example.socialauthl


object Utility {
    fun getBlockedPermissions(all:List<AuthPermissions>, granted:List<AuthPermissions>):List<AuthPermissions>{
        val blocked = arrayListOf<AuthPermissions>()
        all.forEach { per->
           if(!granted.contains(per))
               blocked.add(per)
        }
        return blocked
    }

    fun getFaceParamsFromPermissions(permissions:List<AuthPermissions>):String{
        val params = arrayListOf<String>();
        permissions.forEach { per->
            when(per){
                AuthPermissions.PUBLIC_PROFILE ->
                    params.addAll(listOf("id", "name", "first_name", "last_name", "picture.type(large)"))

                AuthPermissions.EMAIL ->
                    params.add("email")

                AuthPermissions.BIRTH_DAY ->
                    params.add("birthday")

                AuthPermissions.GENDER ->
                    params.add("gender")

            }
        }
        return  params.joinToString(separator = ",");
    }
}