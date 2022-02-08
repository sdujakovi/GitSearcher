package com.example.gitsearcher.`interface`

interface IGitRepositoryModel {
    fun getGitRepositoryData(searchText: String, presenter: IGitRepositoryPresenter)
    fun getGitRepository() : String
}