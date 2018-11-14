package com.kotlin.awankkaley.footballmatchscedhule.model

import com.google.gson.annotations.SerializedName

class MatchResponse (
    @SerializedName("events")
    val match: List<Match>)