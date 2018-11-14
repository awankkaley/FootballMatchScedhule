package com.kotlin.awankkaley.footballmatchscedhule.feature.match.search

import com.kotlin.awankkaley.footballmatchscedhule.model.Match

interface SearchMatchView {
    fun showProgress()
    fun hideProgress()
    fun showMatchList(data: List<Match>)
}