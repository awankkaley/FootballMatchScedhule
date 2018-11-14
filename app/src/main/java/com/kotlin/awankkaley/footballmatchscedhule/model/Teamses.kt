package com.kotlin.awankkaley.footballmatchscedhule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teamses(
    @SerializedName("strTeamBadge")
    var strTeamBadge: String? = null,

    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeam")
    var strTeam: String? = null
    ) : Parcelable