package com.example.gitsearcher.model.service

import android.util.Log
import com.example.gitsearcher.model.data.GitRepository
import com.example.gitsearcher.model.data.GitRepositoryHeader
import com.example.gitsearcher.util.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


interface IService {
    @GET(Constants.SEARCH)
    fun getRepositorys(
        @Query("q") search: String

    ) : Call<GitRepositoryHeader>

    companion object{

        fun createAPI(): IService{
            val icp = HttpLoggingInterceptor()
            icp.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(icp)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            Log.d("ajde", "kreiran retrofit")

            return  retrofit.create(IService::class.java)
        }
    }
}