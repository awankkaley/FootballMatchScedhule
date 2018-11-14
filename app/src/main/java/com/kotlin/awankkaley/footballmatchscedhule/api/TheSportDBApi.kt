package com.kotlin.awankkaley.footballmatchscedhule.api

import com.kotlin.awankkaley.footballmatchscedhule.BuildConfig

object TheSportDBApi {
    fun getMatch(pilihan:String?,league:String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/$pilihan?id=$league"
    }
    fun getTeam(team:String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id=$team"
    }
    fun getTeamAll(league:String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/search_all_teams.php?l=$league"
    }
    fun getMatchDetails(id:String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=$id"
    }
    fun getLeague(): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/all_leagues.php"
    }
    fun getPlayers(id:String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php?id=$id"
    }
    fun getMatchSearch(query:String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?e=$query"
    }
    fun getTeamSearch(query:String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php?t=$query"
    }


}