package com.example.projetomobile1.data.network

import android.content.Context

object Prefs {
    private const val PREFS_NAME = "config_prefs"
    private const val KEY_BASE_URL = "base_url"
    private const val DEFAULT_URL = "http://10.0.2.2:8080"

    fun salvaUrl(context: Context, url: String){
        val sharePref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharePref.edit().putString(KEY_BASE_URL,url).apply()
    }

    fun getUrl(context: Context) : String{
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(KEY_BASE_URL,DEFAULT_URL)?: DEFAULT_URL
    }

    fun validarEFormatarUrl(url: String): String?{
        if(url.isBlank()) return null
        var urlFormatada = url.trim()
        if(!urlFormatada.startsWith("http://") && !urlFormatada.startsWith("htpps://")){
            urlFormatada = "http//$urlFormatada"
        }

        if(!urlFormatada.endsWith("/")){
            urlFormatada = "$urlFormatada/"
        }
        return urlFormatada
    }
}