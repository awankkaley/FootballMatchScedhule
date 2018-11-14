package com.kotlin.awankkaley.footballmatchscedhule.feature.match.search

import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.model.SearchTeamResponse
import com.kotlin.awankkaley.footballmatchscedhule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(
    private val matchView: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchSearch(query: String) {
        matchView.showProgress()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getMatchSearch(query)),
                    SearchTeamResponse::class.java
                )
            }

            matchView.hideProgress()
            matchView.showMatchList(data.await().match)
            SearchMatchMatchActivity.idlingResourceSearchMatch = 0
        }

    }
}