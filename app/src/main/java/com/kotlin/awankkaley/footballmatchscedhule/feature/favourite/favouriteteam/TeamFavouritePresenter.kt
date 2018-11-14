package com.kotlin.awankkaley.footballmatchscedhule.feature.favourite.favouriteteam

import android.content.Context
import com.kotlin.awankkaley.footballmatchscedhule.db.TeamFavourite
import com.kotlin.awankkaley.footballmatchscedhule.db.databaseTeam
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class TeamFavouritePresenter(private val viewTeamFavourite: TeamFavouriteView, private val context: Context?) {

    fun getFavoList() {
        viewTeamFavourite.showProgress()
        context?.databaseTeam?.use {

            val result = select(TeamFavourite.TABLE_FAVOURITE_TEAM)
            val favourite = result.parseList(classParser<TeamFavourite>())
            viewTeamFavourite.showMatchList(favourite)
            viewTeamFavourite.hideProgress()

        }
    }
}