package com.kotlin.awankkaley.footballmatchscedhule.model

import com.google.gson.annotations.SerializedName

class TeamResponse(
    @SerializedName("teams")
    val teams: List<Team>
)