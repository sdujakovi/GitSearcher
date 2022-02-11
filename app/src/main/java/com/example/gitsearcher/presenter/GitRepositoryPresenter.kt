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

/**
 * Presenter application component.
 *
 * This class implements the method getData() from the presenter interface.
 *
 * @property view view component connected with this presenter
 * @property searchText string which provides the user for search
 */
class GitRepositoryPresenter(val view: IGitRepositoryView,
                             val searchText: String ) : IGitRepositoryPresenter{
    /***
     * Use of created API call.
     *
     * Method implements use of Retrofit2 calls with enqueue(with out corutines).
     * OnResponse provides data to view component.
     */
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