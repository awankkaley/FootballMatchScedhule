package com.kotlin.awankkaley.footballmatchscedhule.feature.team

import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.model.LeagueResponse
import com.kotlin.awankkaley.footballmatchscedhule.model.TeamsesResponse
import com.kotlin.awankkaley.footballmatchscedhule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLeaguaList() {
        async(context.main) {
            view.showProgress()
            val data = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getLeague()),
                    LeagueResponse::class.java
                )
            }
            view.showLeagueList(data.await())
        }
    }

    fun getTeams(league: String) {
        view.showProgress()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamAll(league)),
                    TeamsesResponse::class.java
                )
            }

            view.hideProgress()
            view.showTeamList(data.await().teamses)
            HomeTeamFragment.idlingResourceTeam = 0
        }
    }

    fun getTeamsSearch(query: String) {
        view.showProgress()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamSearch(query)),
                    TeamsesResponse::class.java
                )
            }

            view.hideProgress()
            view.showTeamList(data.await().teamses)
        }
    }
}