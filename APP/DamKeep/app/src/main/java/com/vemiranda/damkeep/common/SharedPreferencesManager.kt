package com.vemiranda.damkeep.common

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {

    fun getSharedPreferences(): SharedPreferences {
        return MyApp.instace?.getSharedPreferences(
            Constants.SHARED_PREFS_FILE, Context.MODE_PRIVATE
        )
    }

    fun setStringValue(label: String, value: String) {
        val editor: SharedPreferences.Editor = getSharedPreferences().edit()
        editor.putString(label, value)
        editor.apply()
    }

    fun removeValue() {
        val editor: SharedPreferences.Editor = getSharedPreferences().edit()
        editor.remove(Constants.TOKEN)
        editor.apply()

    }

}