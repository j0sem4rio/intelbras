package br.com.intelbras.api

import retrofit2.Retrofit
import br.com.intelbras.api.IntelbrasAPI
import br.com.intelbras.api.IntelbrasApiServer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import kotlin.Throws
import br.com.intelbras.api.ConfigAPI
import br.com.intelbras.api.util.PokemonListDeserializer
import br.com.intelbras.model.Pokemon
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.reflect.TypeToken
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.TimeUnit

object IntelbrasApiServer {
    lateinit var retrofit: Retrofit
    private var remotyAPI: IntelbrasAPI? = null
    val apiServer: IntelbrasAPI?
        get() {
            createServer()
            return remotyAPI
        }

    /**
     * Private Server API
     *
     */
    private fun createServer() {
        val listType: Type = object : TypeToken<MutableList<Pokemon>>() {}.type
        val gsonConverter = GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            .registerTypeAdapter(listType,  PokemonListDeserializer())
            .excludeFieldsWithoutExposeAnnotation()
            .serializeNulls()
            .create()
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("Accept", "application/json, text/plain, */*")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        httpClient.addNetworkInterceptor(StethoInterceptor())
        val okHttpClient = httpClient.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ConfigAPI.API_MOBILE)
            .addConverterFactory(GsonConverterFactory.create(gsonConverter))
            .build()
        remotyAPI = retrofit.create(IntelbrasAPI::class.java)
    }
}