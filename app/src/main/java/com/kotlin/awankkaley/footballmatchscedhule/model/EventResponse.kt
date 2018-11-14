package com.kotlin.awankkaley.footballmatchscedhule.model

import com.google.gson.annotations.SerializedName

class EventResponse(
    @SerializedName("events")
    val event: List<Event>
)