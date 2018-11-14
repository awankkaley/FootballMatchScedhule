package com.kotlin.awankkaley.footballmatchscedhule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavouriteMatch.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favourite.TABLE_FAVOURITE,true,
            Favourite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favourite.ID_EVENT to TEXT,
            Favourite.STR_HOME_TEAM to TEXT,
            Favourite.STR_AWAY_TEAM to TEXT,
            Favourite.INT_HOME_SCORE to TEXT,
            Favourite.INT_AWAY_SCORE to TEXT,
            Favourite.DATE_EVENT to TEXT,
            Favourite.ID_HOME_TEAM to TEXT,
            Favourite.ID_AWAY_TEAM to TEXT,
            Favourite.STR_TIME to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favourite.TABLE_FAVOURITE,true)
    }

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context):MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(context.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }
}
val Context.databse: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
