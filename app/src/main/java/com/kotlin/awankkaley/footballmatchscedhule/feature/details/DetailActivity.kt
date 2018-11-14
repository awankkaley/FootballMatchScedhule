package com.kotlin.awankkaley.footballmatchscedhule.feature.details

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.db.Favourite
import com.kotlin.awankkaley.footballmatchscedhule.db.databse
import com.kotlin.awankkaley.footballmatchscedhule.model.Event
import com.kotlin.awankkaley.footballmatchscedhule.model.Teamses
import com.kotlin.awankkaley.footballmatchscedhule.utils.dateNewFormat
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailView {
    companion object {
        var idlingResourceDetail = 1
    }

    private var menuItem: Menu? = null
    private lateinit var presenter: DetailPresenter
    private lateinit var idEvent: String
    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String
    private lateinit var event: Event
    private var isFavourite: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val request = ApiRepository()
        val gson = Gson()
        idEvent = intent.getStringExtra("idEvent")
        idHomeTeam = intent.getStringExtra("idHomeTeam")
        idAwayTeam = intent.getStringExtra("idAwayTeam")
        presenter = DetailPresenter(this, request, gson)
        checkFavo()
        favoIconSet()
        presenter.getTeamList(idAwayTeam, idHomeTeam)
        presenter.getEventDetail(idEvent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favo_menu, menu)
        menuItem = menu
        favoIconSet()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favo_menu_star -> {
                if (!isFavourite) {
                    presenter.insertFavo(event, idEvent, idHomeTeam, idAwayTeam, applicationContext)
                    isFavourite = !isFavourite


                } else {
                    presenter.deleteFavo(idEvent, applicationContext)
                    isFavourite = !isFavourite
                }

                favoIconSet()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoIconSet() {
        if (isFavourite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_added_to_favourite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_add_to_favourites)
        }
    }

    private fun checkFavo() {
        databse.use {
            val result = select(Favourite.TABLE_FAVOURITE).whereArgs(
                "(ID_EVENT = {id})",
                "id" to idEvent
            )
            val favorite = result.parseList(classParser<Favourite>())
            if (!favorite.isEmpty()) isFavourite = true
        }
    }


    override fun showDataEvent(match: List<Event>?) {
        tv_away_score_detail.text = match?.single()?.intAwayScore
        tv_home_score_detail.text = match?.single()?.intHomeScore
        tv_date_detail.text = dateNewFormat(match?.single()?.dateEvent, false)
        tv_name_home.text = match?.single()?.strHomeTeam
        tv_name_away.text = match?.single()?.strAwayTeam
        tv_home_goal_details.text = match?.single()?.strHomeGoalDetails
        tv_away_goal_details.text = match?.single()?.strAwayGoalDetails
        tv_home_shoot_detail.text = match?.single()?.intHomeShots
        tv_away_shoot_detail.text = match?.single()?.intAwayShots
        tv_home_gk_details.text = match?.single()?.strHomeLineupGoalkeeper
        tv_away_gk_details.text = match?.single()?.strAwayLineupGoalkeeper
        tv_home_def_details.text = match?.single()?.strHomeLineupDefense
        tv_away_def_details.text = match?.single()?.strAwayLineupDefense
        tv_home_mid_details.text = match?.single()?.strHomeLineupMidfield
        tv_away_mid_details.text = match?.single()?.strAwayLineupMidfield
        tv_home_for_details.text = match?.single()?.strHomeLineupForward
        tv_away_for_details.text = match?.single()?.strAwayLineupForward
        tv_home_sub_details.text = match?.single()?.strHomeLineupSubstitutes
        tv_away_sub_details.text = match?.single()?.strAwayLineupSubstitutes
        tv_time_details.text = dateNewFormat(match?.single()?.strTime, true)
        event = Event(
            strHomeTeam = match?.single()?.strHomeTeam,
            strAwayTeam = match?.single()?.strAwayTeam,
            intHomeScore = match?.single()?.intHomeScore,
            intAwayScore = match?.single()?.intAwayScore,
            dateEvent = match?.single()?.dateEvent,
            strTime = match?.single()?.strTime
        )
    }

    override fun showToastSuccess() {
        if (!isFavourite) {
            toast(R.string.added_to_fav)
        } else {
            toast(R.string.deleted_to_fav)
        }

    }

    override fun showToastError(e: SQLiteConstraintException) {
        toast(e.localizedMessage)
    }

    override fun showImageTeams(dataAway: List<Teamses>, dataHome: List<Teamses>) {
        Glide.with(applicationContext).load(dataAway.single().strTeamBadge).into(img_away_detail)
        Glide.with(applicationContext).load(dataHome.single().strTeamBadge).into(img_home_detail)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
