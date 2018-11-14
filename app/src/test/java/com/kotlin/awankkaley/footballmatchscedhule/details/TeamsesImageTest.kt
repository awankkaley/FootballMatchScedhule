package com.kotlin.awankkaley.footballmatchscedhule.details

import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.TestContextProvider
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.feature.details.DetailPresenter
import com.kotlin.awankkaley.footballmatchscedhule.feature.details.DetailView
import com.kotlin.awankkaley.footballmatchscedhule.model.Teamses
import com.kotlin.awankkaley.footballmatchscedhule.model.TeamsesResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TeamsesImageTest {

    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(
            view,
            apiRepository,
            gson,
            TestContextProvider()
        )

    }


    @Test
    fun getTeamList() {
        val teamses: MutableList<Teamses> = mutableListOf()
        val response = TeamsesResponse(teamses)
        val responsedua = TeamsesResponse(teamses)
        val idHome = "123"
        val idAway = "123"

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeam(idHome)),
                TeamsesResponse::class.java
            )
        ).thenReturn(response)

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeam(idAway)),
                TeamsesResponse::class.java
            )
        ).thenReturn(responsedua)

        presenter.getTeamList(idHome, idAway)

        Mockito.verify(view).showImageTeams(teamses, teamses)

    }




}