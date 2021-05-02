package com.example.expensetracker.data

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private var sharedPref: SharedPreferences? = null

    fun getSharedPref(context: Context): SharedPreferences?{

        if (sharedPref != null){
            return sharedPref
        }

        sharedPref = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

        return sharedPref
    }

}