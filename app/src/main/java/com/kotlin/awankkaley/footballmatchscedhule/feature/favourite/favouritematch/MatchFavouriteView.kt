package com.kotlin.awankkaley.footballmatchscedhule.feature.favourite.favouritematch

import com.kotlin.awankkaley.footballmatchscedhule.db.Favourite

interface MatchFavouriteView {
    fun showProgress()
    fun hideProgress()
    fun showMatchList(data: List<Favourite>)
}