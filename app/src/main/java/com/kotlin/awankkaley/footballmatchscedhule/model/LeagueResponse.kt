package com.kotlin.awankkaley.footballmatchscedhule.model

import com.google.gson.annotations.SerializedName

class LeagueResponse (
    @SerializedName("leagues")
    val leagues: MutableList<League>)