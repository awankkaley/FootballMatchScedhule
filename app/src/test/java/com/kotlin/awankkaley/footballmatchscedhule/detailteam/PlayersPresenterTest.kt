package com.kotlin.awankkaley.footballmatchscedhule.detailteam

import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.TestContextProvider
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam.PlayersPresenter
import com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam.PlayersView
import com.kotlin.awankkaley.footballmatchscedhule.model.PlayerReponse
import com.kotlin.awankkaley.footballmatchscedhule.model.Players
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayersPresenterTest {
    @Mock
    private lateinit var view: PlayersView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayersPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayersPresenter(
            view,
            apiRepository,
            gson,
            TestContextProvider()
        )

    }

    @Test
    fun getPlayers() {
        val players: MutableList<Players> = mutableListOf()
        val response = PlayerReponse(players)
        val idTeam = "123"

        Mockito.`when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPlayers(idTeam)),
                PlayerReponse::class.java
            )
        ).thenReturn(response)


        presenter.getPlayers(idTeam)

        Mockito.verify(view).getPlayers(players)

    }
}
