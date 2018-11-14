package com.kotlin.awankkaley.footballmatchscedhule.feature.details

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.db.Favourite
import com.kotlin.awankkaley.footballmatchscedhule.db.databse
import com.kotlin.awankkaley.footballmatchscedhule.model.Event
import com.kotlin.awankkaley.footballmatchscedhule.model.EventResponse
import com.kotlin.awankkaley.footballmatchscedhule.model.TeamsesResponse
import com.kotlin.awankkaley.footballmatchscedhule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

class DetailPresenter(
    val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamList(idaway: String?, idHome: String?) {
        async(context.main) {
            val dataAway = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeam(idaway)),
                    TeamsesResponse::class.java
                )
            }
            val dataHome = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeam(idHome)),
                    TeamsesResponse::class.java
                )
            }


            view.showImageTeams(
                dataAway.await().teamses,
                dataHome.await().teamses
            )


        }
    }

    fun insertFavo(match: Event?, idEvent: String?, idHomeTeam: String?, idAwayTeam: String?, context: Context?) {
        try {
            context?.databse?.use {
                insert(
                    Favourite.TABLE_FAVOURITE,
                    Favourite.ID_EVENT to idEvent,
                    Favourite.STR_HOME_TEAM to match?.strHomeTeam,
                    Favourite.STR_AWAY_TEAM to match?.strAwayTeam,
                    Favourite.INT_HOME_SCORE to match?.intHomeScore,
                    Favourite.INT_AWAY_SCORE to match?.intAwayScore,
                    Favourite.DATE_EVENT to match?.dateEvent,
                    Favourite.ID_HOME_TEAM to idHomeTeam,
                    Favourite.ID_AWAY_TEAM to idAwayTeam,
                    Favourite.STR_TIME to match?.strTime
                )
            }
            view.showToastSuccess()
        } catch (e: SQLiteConstraintException) {
            view.showToastError(e)
        }


    }

    fun getEventDetail(idEvent: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getMatchDetails(idEvent)),
                    EventResponse::class.java
                )
            }
            view.showDataEvent(data.await().event)
            Log.d("HASIL", "DATANYA :" + data.await().event)
            DetailActivity.idlingResourceDetail = 0
        }
    }

    fun deleteFavo(id: String, context: Context?) {
        try {
            context?.databse?.use {
                delete(
                    Favourite.TABLE_FAVOURITE, "(ID_EVENT = {id})",
                    "id" to id
                )
            }
            view.showToastSuccess()
        } catch (e: SQLiteConstraintException) {
            view.showToastError(e)
        }

    }


}