package com.example.gitsearcher.model.service

import android.util.Log
import com.example.gitsearcher.model.data.GitRepository
import com.example.gitsearcher.model.data.GitRepositoryHeader
import com.example.gitsearcher.util.Constants
import com.google.gson.GsonBuilder
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
        @Query("q")     search: String,
        @Query("sort")  sort: String,
        @Query("page")  page: String,
        @Query("per_page") per_page: String

    ) : Call<GitRepositoryHeader>

    companion object{
        /**
         * Create API call method
         *
         * Method implements Retrofit2 call build, mplements Gson for date sort and
         * implements OkHttp for debug.
         *
         * @return created retrofit call
         */
        fun createAPI(): IService{
            val icp = HttpLoggingInterceptor()
            icp.setLevel(HttpLoggingInterceptor.Level.BODY)

            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create()

            val client = OkHttpClient.Builder()
                .addInterceptor(icp)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

            return  retrofit.create(IService::class.java)
        }
    }
}