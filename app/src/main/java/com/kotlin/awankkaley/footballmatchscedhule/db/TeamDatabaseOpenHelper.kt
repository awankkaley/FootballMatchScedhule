package com.kotlin.awankkaley.footballmatchscedhule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class TeamDatabaseOpenHelper(context:Context):ManagedSQLiteOpenHelper(context,"FootballTeam.db",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(TeamFavourite.TABLE_FAVOURITE_TEAM,true,
            TeamFavourite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamFavourite.ID_TEAM to TEXT,
            TeamFavourite.STR_TEAM to TEXT,
            TeamFavourite.STR_TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TeamFavourite.TABLE_FAVOURITE_TEAM,true)
    }

    companion object {
        private var  instance : TeamDatabaseOpenHelper? = null
        @Synchronized
        fun getInstance(context: Context):TeamDatabaseOpenHelper {
            if (instance == null) {
                instance = TeamDatabaseOpenHelper(context.applicationContext)
            }
            return instance as TeamDatabaseOpenHelper
        }
    }
}
val Context.databaseTeam: TeamDatabaseOpenHelper
    get() = TeamDatabaseOpenHelper.getInstance(applicationContext)