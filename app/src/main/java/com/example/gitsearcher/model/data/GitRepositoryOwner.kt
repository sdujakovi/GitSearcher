package com.example.gitsearcher.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepositoryOwner(

    @SerializedName("login"               ) var login             : String?  = null,
    @SerializedName("id"                  ) var id                : Int?     = null,
    @SerializedName("avatar_url"          ) var avatarUrl         : String?  = null,

): Parcelable {}
