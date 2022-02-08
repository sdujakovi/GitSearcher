package com.example.gitsearcher.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepositoryHeader(

    @SerializedName("total_count"        ) var totalCount        : Int?             = null,
    @SerializedName("incomplete_results" ) var incompleteResults : Boolean?         = null,
    @SerializedName("items"              ) var items             : ArrayList<GitRepository> = arrayListOf()
): Parcelable {}
