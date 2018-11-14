package com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.db.TeamFavourite
import com.kotlin.awankkaley.footballmatchscedhule.db.databaseTeam
import com.kotlin.awankkaley.footballmatchscedhule.model.Team
import com.kotlin.awankkaley.footballmatchscedhule.model.TeamResponse
import com.kotlin.awankkaley.footballmatchscedhule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

class ScroliingDetailPresenter(
    val view: ScrollingDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamDetail(idTeam: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeam(idTeam)),
                    TeamResponse::class.java
                )
            }
            view.getTeam(data.await().teams)


        }
    }

    fun insertFavo(team:Team?,idTeam: String?, context: Context?) {
        try {
            context?.databaseTeam?.use {
                insert(
                    TeamFavourite.TABLE_FAVOURITE_TEAM,
                    TeamFavourite.ID_TEAM to idTeam,
                    TeamFavourite.STR_TEAM to team?.strTeam,
                    TeamFavourite.STR_TEAM_BADGE to team?.strTeamBadge
                )
            }
            view.showToastSuccess()
        } catch (e: SQLiteConstraintException) {
            view.showToastError(e)
        }
    }

    fun deleteFavo(id: String, context: Context?) {
        try {
            context?.databaseTeam?.use {
                delete(
                    TeamFavourite.TABLE_FAVOURITE_TEAM, "(ID_TEAM = {id})",
                    "id" to id
                )
            }
            view.showToastSuccess()
        } catch (e: SQLiteConstraintException) {
            view.showToastError(e)
        }

    }
}