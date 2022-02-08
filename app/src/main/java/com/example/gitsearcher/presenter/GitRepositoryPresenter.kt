package com.example.gitsearcher.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.gitsearcher.`interface`.IGitRepositoryModel
import com.example.gitsearcher.`interface`.IGitRepositoryView
import com.example.gitsearcher.model.data.GitRepository
import com.example.gitsearcher.model.service.IService
import com.example.gitsearcher.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class GitRepositoryPresenter(val view: IGitRepositoryView,
                             val context: Context,
                             val searchText: String ) {

    fun getData() {
        val data: ArrayList<GitRepository> = arrayListOf()
        Log.d("ajde", searchText.toString())

        /*IService.createAPI().getRepositorys(search = searchText.toString())
            .execute(object : Callback<List<GitRepository>> {
                override fun onResponse(
                    call: Call<List<GitRepository>>,
                    response: Response<List<GitRepository>>
                ) {
                    Toast.makeText(context, "Dobro jee", Toast.LENGTH_LONG)
                    Log.d("ajde", "nesto je doslo")
                    if (response.isSuccessful) {
                        Log.d("ajde", "Dohvatili smo podatke")
                    } else {
                        Log.d("ajde", "Opet nismo dohvatili...")
                    }
                }

                override fun onFailure(call: Call<List<GitRepository>>, t: Throwable) {
                    Toast.makeText(context, "An error ocured", Toast.LENGTH_LONG)
                }
            })*/


        var response : Response<List<GitRepository>> = IService.createAPI().getRepositorys(searchText).execute()
        Log.d("ajde", response.body()?.size.toString())
    }
}