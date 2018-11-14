package com.kotlin.awankkaley.footballmatchscedhule.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamFavourite(
    val id: Long?,
    val idTeam: String?,
    val strTeam: String?,
    val strTeamBadge: String?
) : Parcelable{
    companion object {
        const val TABLE_FAVOURITE_TEAM: String = "TABLE_FAVOURITE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM:String = "ID_TEAM"
        const val STR_TEAM:String = "STR_TEAM"
        const val STR_TEAM_BADGE:String = "STR_TEAM_BADGE"
    }
}