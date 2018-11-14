package com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam

import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.model.PlayerReponse
import com.kotlin.awankkaley.footballmatchscedhule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayersPresenter (
    val view: PlayersView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPlayers(idTeam: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getPlayers(idTeam)),
                    PlayerReponse::class.java
                )
            }
            view.getPlayers(data.await().players)


        }
    }
}