package com.example.gitsearcher.presenter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.gitsearcher.*
import com.example.gitsearcher.`interface`.IGitRepositoryView
import com.example.gitsearcher.model.data.GitRepository
import com.example.gitsearcher.model.data.GitRepositoryHeader
import com.example.gitsearcher.model.service.IService
import retrofit2.Call
import retrofit2.Callback
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
                    Log.d("ajde", "on fail")
                }
            })

        /*IService.createAPI().getRepositorys(search = searchText.toString())
            .enqueue(object : Callback<List<GitRepository>> {
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


        /*var response : Response<List<GitRepository>> = IService.createAPI().getRepositorys(searchText).execute()
        Log.d("ajde", response.body()?.size.toString())*/
    }
}