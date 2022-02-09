package com.example.gitsearcher.interfaces

interface IGitRepositoryModel {
    fun getGitRepositoryData(searchText: String, presenter: IGitRepositoryPresenter)
    fun getGitRepository() : String
}