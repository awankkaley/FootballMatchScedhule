package com.kotlin.awankkaley.footballmatchscedhule.feature.favourite.favouriteteam

import com.kotlin.awankkaley.footballmatchscedhule.db.TeamFavourite

interface TeamFavouriteView {
    fun showProgress()
    fun hideProgress()
    fun showMatchList(data: List<TeamFavourite>)
}