package com.kotlin.awankkaley.footballmatchscedhule.model

import com.google.gson.annotations.SerializedName

class SearchTeamResponse(
    @SerializedName("event")
    val match: List<Match>
)