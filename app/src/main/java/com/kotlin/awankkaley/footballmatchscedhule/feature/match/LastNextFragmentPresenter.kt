package com.kotlin.awankkaley.footballmatchscedhule.feature.match


import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.model.LeagueResponse
import com.kotlin.awankkaley.footballmatchscedhule.model.MatchResponse
import com.kotlin.awankkaley.footballmatchscedhule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class LastNextFragmentPresenter(
    private val viewLastNext: LastNextFragmentView,
    private val apiRepository: ApiRepository,
    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagueList() {
        viewLastNext.showProgress()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getLeague()),
                    LeagueResponse::class.java
                )
            }
            viewLastNext.showLeagueList(data.await())
        }
    }



    fun getMatchList(pilihan: String,league:String?) {
        viewLastNext.showProgress()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getMatch(pilihan,league)),
                    MatchResponse::class.java
                )
            }

            viewLastNext.hideProgress()
            viewLastNext.showMatchList(data.await().match)

            LastFragment.idlingResourceLast = 0
            NextFragment.idlingResourceNext = 0
        }
    }

}