package com.kotlin.awankkaley.footballmatchscedhule.feature.match

import com.kotlin.awankkaley.footballmatchscedhule.model.LeagueResponse
import com.kotlin.awankkaley.footballmatchscedhule.model.Match

interface LastNextFragmentView {
    fun showProgress()
    fun hideProgress()
    fun showMatchList(data: List<Match>)
    fun showLeagueList(data: LeagueResponse)
}