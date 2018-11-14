package com.kotlin.awankkaley.footballmatchscedhule.feature.details

import android.database.sqlite.SQLiteConstraintException
import com.kotlin.awankkaley.footballmatchscedhule.model.Event
import com.kotlin.awankkaley.footballmatchscedhule.model.Teamses


interface DetailView {
    fun showImageTeams(dataAway: List<Teamses>, dataHome: List<Teamses>)
    fun showToastSuccess()
    fun showToastError(e:SQLiteConstraintException)
    fun showDataEvent(match:List<Event>?)
}