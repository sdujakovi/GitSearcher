package com.example.gitsearcher.presenter

import android.util.Log
import com.example.gitsearcher.interfaces.IGitRepositoryPresenter
import com.example.gitsearcher.interfaces.IGitRepositoryView
import com.example.gitsearcher.model.data.GitRepository
import com.example.gitsearcher.model.data.GitRepositoryHeader
import com.example.gitsearcher.model.service.IService
import com.example.gitsearcher.util.Constants.Companion.PER_PAGE
import com.example.gitsearcher.util.Constants.Companion.SORT_BY_UPDATE
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
class GitRepositoryPresenter(val view: IGitRepositoryView) : IGitRepositoryPresenter{
    /***
     * Use of created API call.
     *
     * Method implements use of Retrofit2 calls with enqueue(with out corutines).
     * OnResponse provides data to view component.
     */
    override fun getData(searchText: String, page: String) {
        IService.createAPI().getRepositorys(searchText, SORT_BY_UPDATE, page, PER_PAGE)
            .enqueue(object : javax.security.auth.callback.Callback,
                retrofit2.Callback<GitRepositoryHeader>{

                override fun onResponse(call: Call<GitRepositoryHeader>,
                                        response: Response<GitRepositoryHeader>) {
                    if (response.isSuccessful){
                        var retrievedData: ArrayList<GitRepository> = response.body()!!.items as ArrayList<GitRepository>
                        retrievedData.sortByDescending {
                            it.updatedAt
                        }
                        view.updateView(retrievedData.toList(), response.body()!!.totalCount!!.toInt())
                    }
                }

                override fun onFailure(call: Call<GitRepositoryHeader>, t: Throwable) {
                    Log.d("tag", t.message.toString())
                }
            })
    }
}