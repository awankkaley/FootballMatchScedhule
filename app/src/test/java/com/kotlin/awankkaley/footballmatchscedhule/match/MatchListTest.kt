package com.kotlin.awankkaley.footballmatchscedhule.match

import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.TestContextProvider
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.feature.match.LastNextFragmentPresenter
import com.kotlin.awankkaley.footballmatchscedhule.feature.match.LastNextFragmentView
import com.kotlin.awankkaley.footballmatchscedhule.model.Match
import com.kotlin.awankkaley.footballmatchscedhule.model.MatchResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchListTest {

    @Mock private lateinit var view: LastNextFragmentView

    @Mock private lateinit var gson: Gson

    @Mock private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: LastNextFragmentPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastNextFragmentPresenter(
            view,
            apiRepository,
            gson,
            TestContextProvider()
        )

    }

    @Test
    fun getMatchList() {
        val match:MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val pilihan = "eventspastleague.php"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatch(pilihan,"league")),
            MatchResponse::class.java)).thenReturn(response)

        presenter.getMatchList(pilihan,"league")

        Mockito.verify(view).showProgress()
        Mockito.verify(view).showMatchList(match)
        Mockito.verify(view).hideProgress()


    }
}