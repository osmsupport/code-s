package com.example.cricketapp.di

import com.example.cricketapp.network.api.ApiServices
import com.example.cricketapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

@Provides
fun provideApiClient(baseURL:String,okHttpClient:OkHttpClient): ApiServices {

    val client : ApiServices by lazy {
        Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
    return client
}
    @Provides
    fun provideBaseURL():String{
        return BASE_URL
    }
    @Provides
    fun providemLoggingInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun providemOkHttpClient(mHttpLoggingInterceptor:HttpLoggingInterceptor):OkHttpClient{
        return  OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()
    }

}