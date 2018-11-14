package com.kotlin.awankkaley.footballmatchscedhule.feature.team

import com.kotlin.awankkaley.footballmatchscedhule.model.LeagueResponse
import com.kotlin.awankkaley.footballmatchscedhule.model.Teamses


interface TeamView {
    fun showProgress()
    fun hideProgress()
    fun showTeamList(data: List<Teamses>)
    fun showLeagueList(data: LeagueResponse)
    fun showTeamSearch(data: List<Teamses>)
}