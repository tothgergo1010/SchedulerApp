package com.example.schedulerapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale

class AppLocaleManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("locale_prefs", Context.MODE_PRIVATE)

    fun setLocale(c: Context, language: String): Context {
        saveLanguage(language)
        return updateResources(c, language)
    }

    fun getLanguage(): String {
        return prefs.getString("lang", "hu") ?: "hu"
    }

    @SuppressLint("ApplySharedPref")
    private fun saveLanguage(lang: String) {
        prefs.edit().putString("lang", lang).commit()
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }
}
