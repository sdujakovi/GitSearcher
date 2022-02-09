package com.example.gitsearcher.interfaces

import com.example.gitsearcher.model.data.GitRepositoryHeader

interface IGitRepositoryModel {
    //implementirano u model.service.IService
    fun getGitRepository() : GitRepositoryHeader
}