package com.example.chatting

import android.content.Context
import android.content.SharedPreferences
import android.text.Editable

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("profile", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str.toString()).apply()
    }
}
