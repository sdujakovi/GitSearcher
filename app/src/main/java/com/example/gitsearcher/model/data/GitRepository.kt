package com.example.gitsearcher.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepository(

    @SerializedName("id"                ) var id               : Int?              = null,
    @SerializedName("name"              ) var name             : String?           = null,
    @SerializedName("owner"             ) var owner            : GitRepositoryOwner?            = GitRepositoryOwner(),
    @SerializedName("description"       ) var description      : String?           = null,
    @SerializedName("updated_at"        ) var updatedAt        : String?           = null,

):Parcelable {}
