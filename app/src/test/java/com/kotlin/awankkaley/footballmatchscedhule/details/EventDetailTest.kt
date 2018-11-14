package com.kotlin.awankkaley.footballmatchscedhule.details

import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.TestContextProvider
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.api.TheSportDBApi
import com.kotlin.awankkaley.footballmatchscedhule.feature.details.DetailPresenter
import com.kotlin.awankkaley.footballmatchscedhule.feature.details.DetailView
import com.kotlin.awankkaley.footballmatchscedhule.model.Event
import com.kotlin.awankkaley.footballmatchscedhule.model.EventResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class EventDetailTest {

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
    fun getEventDetail() {
        val event: MutableList<Event> = mutableListOf()
        val response = EventResponse(event)
        val idEvent = "123"

        Mockito.`when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getMatchDetails(idEvent)),
                EventResponse::class.java
            )
        ).thenReturn(response)

        presenter.getEventDetail(idEvent)

        Mockito.verify(view).showDataEvent(event)

    }
}