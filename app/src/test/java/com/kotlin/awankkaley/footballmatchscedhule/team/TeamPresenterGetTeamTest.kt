package com.kotlin.awankkaley.footballmatchscedhule.team

import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.TestContextProvider
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.feature.team.TeamPresenter
import com.kotlin.awankkaley.footballmatchscedhule.feature.team.TeamView
import com.kotlin.awankkaley.footballmatchscedhule.model.Teamses
import com.kotlin.awankkaley.footballmatchscedhule.model.TeamsesResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterGetTeamTest {

    @Mock
    private lateinit var view: TeamView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(
            view,
            apiRepository,
            gson,
            TestContextProvider()
        )

    }


    @Test
    fun getTeams() {
        val team:MutableList<Teamses> = mutableListOf()
        val response = TeamsesResponse(team)
        val pilihan = "1234"

        Mockito.`when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamAll(pilihan)),
                TeamsesResponse::class.java
            )
        ).thenReturn(response)

        presenter.getTeams(pilihan)

        Mockito.verify(view).showProgress()
        Mockito.verify(view).showTeamList(team)
        Mockito.verify(view).hideProgress()
    }

}