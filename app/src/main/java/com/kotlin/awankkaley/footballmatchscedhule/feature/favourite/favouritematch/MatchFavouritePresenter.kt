package com.kotlin.awankkaley.footballmatchscedhule.feature.favourite.favouritematch

import android.content.Context
import com.kotlin.awankkaley.footballmatchscedhule.db.Favourite
import com.kotlin.awankkaley.footballmatchscedhule.db.databse
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class MatchFavouritePresenter(private val viewMatchFavourite: MatchFavouriteView, private val context:Context?) {

    fun getFavoList() {
        viewMatchFavourite.showProgress()
        context?.databse?.use {

            val result = select(Favourite.TABLE_FAVOURITE)
            val favourite = result.parseList(classParser<Favourite>())
            viewMatchFavourite.showMatchList(favourite)
            viewMatchFavourite.hideProgress()
            MatchFavouriteFragment.idlingResourceFavourite = 0

    }
}
}