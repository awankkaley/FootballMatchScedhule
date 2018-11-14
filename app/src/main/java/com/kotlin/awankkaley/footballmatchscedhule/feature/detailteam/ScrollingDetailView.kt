package com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam

import android.database.sqlite.SQLiteConstraintException
import com.kotlin.awankkaley.footballmatchscedhule.model.Team

interface ScrollingDetailView {
    fun getTeam(team:List<Team>)
    fun showToastSuccess()
    fun showToastError(e: SQLiteConstraintException)
}