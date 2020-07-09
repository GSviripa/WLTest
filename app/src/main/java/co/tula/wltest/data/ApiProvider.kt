package co.tula.wltest.data

import co.tula.wltest.domain.AuthStorage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiProvider(apiUrl: String) {
    private val httpClient: OkHttpClient
    val twitchApi: TwitchApi

    init {
        httpClient = buildClient()
        twitchApi = buildRetrofit(httpClient, apiUrl).create()
    }

    private fun buildRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        val builder = Retrofit.Builder()
        builder.client(client)
        builder.baseUrl(baseUrl)
        builder.addConverterFactory(GsonConverterFactory.create(buildGson()))
        return builder.build()
    }

    private fun buildGson(): Gson {
        return GsonBuilder().create()
    }

    private fun buildClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor {
                val request = it.request()
                val newRequest = request.newBuilder()
                    .addHeader("Authorization", "Bearer ${AuthStorage.token}")
                    .addHeader("Client-ID", AuthStorage.clientId)
                    .build()
                it.proceed(newRequest)
            }
            .build()

    }

}