package com.example.gitsearcher.presenter

import android.content.Context
import android.util.Log
import com.example.gitsearcher.interfaces.IGitRepositoryPresenter
import com.example.gitsearcher.interfaces.IGitRepositoryView
import com.example.gitsearcher.model.data.GitRepository
import com.example.gitsearcher.model.data.GitRepositoryHeader
import com.example.gitsearcher.model.service.IService
import retrofit2.Call
import retrofit2.Response


class GitRepositoryPresenter(val view: IGitRepositoryView,
                             val context: Context,
                             val searchText: String ) : IGitRepositoryPresenter{

    override fun getData() {

        IService.createAPI().getRepositorys(searchText)
            .enqueue(object : javax.security.auth.callback.Callback,
                retrofit2.Callback<GitRepositoryHeader>{

                override fun onResponse(call: Call<GitRepositoryHeader>,
                                        response: Response<GitRepositoryHeader>) {
                    if (response.isSuccessful){
                        var retrievedData: ArrayList<GitRepository> = response.body()!!.items as ArrayList<GitRepository>
                        retrievedData.sortByDescending {
                            it.updatedAt
                        }
                        view.updateView(retrievedData.toList())
                    }
                }

                override fun onFailure(call: Call<GitRepositoryHeader>, t: Throwable) {
                    Log.d("tag", t.message.toString())
                }
            })
    }
}