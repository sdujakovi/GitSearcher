package com.example.gitsearcher.presenter

import android.content.Context
import android.util.Log
import com.example.gitsearcher.interfaces.IGitRepositoryView
import com.example.gitsearcher.model.data.GitRepositoryHeader
import com.example.gitsearcher.model.service.IService
import retrofit2.Call
import retrofit2.Response


class GitRepositoryPresenter(val view: IGitRepositoryView,
                             val context: Context,
                             val searchText: String ) {

    fun getData() {
        Log.d("ajde", searchText.toString())

        IService.createAPI().getRepositorys(searchText)
            .enqueue(object : javax.security.auth.callback.Callback, retrofit2.Callback<GitRepositoryHeader>{
                override fun onResponse(call: Call<GitRepositoryHeader>, response: Response<GitRepositoryHeader>) {
                    Log.d("ajde", "u responsu")
                    if (response.isSuccessful){
                        Log.d("ajde", "uspjeh")
                        view.updateView(result = response.body()!!.items)
                    }else{
                        Log.d("ajde", "neuspjeh")
                    }
                }
                override fun onFailure(call: Call<GitRepositoryHeader>, t: Throwable) {
                    Log.d("messs", t.message.toString())
                }
            })
    }
}