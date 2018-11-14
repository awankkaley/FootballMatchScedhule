package com.kotlin.awankkaley.footballmatchscedhule.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favourite(
    val id: Long?,
    val idEvent:String?,
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intHomeScore: String?,
    val intAwayScore: String?,
    val dateEvent: String?,
    val idHomeTeam: String?,
    val idAwayTeam: String?,
    val strTime:String?
) : Parcelable {
    companion object {

        const val TABLE_FAVOURITE: String = "TABLE_FAVOURITE"
        const val ID: String = "ID_"
        const val ID_EVENT:String = "ID_EVENT"
        const val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        const val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
        const val INT_HOME_SCORE: String = "INT_HOME_SCORE"
        const val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        const val STR_TIME:String = "STR_TIME"
    }


}