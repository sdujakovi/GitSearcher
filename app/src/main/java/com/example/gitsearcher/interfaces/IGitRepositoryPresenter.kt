package com.example.gitsearcher.interfaces



interface IGitRepositoryPresenter {
    fun getData(searchText: String, page: String)
}