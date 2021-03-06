package com.vemiranda.damkeep.retrofit

import com.vemiranda.damkeep.common.Constants
import com.vemiranda.damkeep.common.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DamKeepInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val original: Request = chain.request()
        val request: Request
        val token = SharedPreferencesManager().getSharedPreferences()
            .getString(Constants.TOKEN, "")
        val requestBuilder: Request.Builder =
            original.newBuilder().header("Authorization","Bearer " + token)
        request = requestBuilder.build()

        return chain.proceed(request)

    }
}