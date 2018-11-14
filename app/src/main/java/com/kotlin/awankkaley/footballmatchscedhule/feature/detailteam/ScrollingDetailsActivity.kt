package com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.adapter.TeamPagerAdapter
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.db.TeamFavourite
import com.kotlin.awankkaley.footballmatchscedhule.db.databaseTeam
import com.kotlin.awankkaley.footballmatchscedhule.model.Team
import kotlinx.android.synthetic.main.activity_scrolling_details.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class ScrollingDetailsActivity : AppCompatActivity(),
    ScrollingDetailView {
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
                    presenter.insertFavo(teams, idTeam, applicationContext)
                    isFavourite = !isFavourite


                } else {
                    presenter.deleteFavo(idTeam, applicationContext)
                    isFavourite = !isFavourite
                }

                favoIconSet()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private var menuItem: Menu? = null
    private lateinit var presenter: ScroliingDetailPresenter
    private lateinit var idTeam: String
    private lateinit var teams: Team
    private var isFavourite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val request = ApiRepository()
        val gson = Gson()
        idTeam = intent.getStringExtra("idTeam")
        presenter = ScroliingDetailPresenter(
            this,
            request,
            gson
        )
        checkFavo()
        favoIconSet()
        presenter.getTeamDetail(idTeam)

    }

    private fun favoIconSet() {
        if (isFavourite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_added_to_favourite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_add_to_favourites)
        }
    }

    private fun checkFavo() {
        databaseTeam.use {
            val result = select(TeamFavourite.TABLE_FAVOURITE_TEAM).whereArgs(
                "(ID_TEAM = {id})",
                "id" to idTeam
            )
            val favorite = result.parseList(classParser<TeamFavourite>())
            if (!favorite.isEmpty()) isFavourite = true
        }
    }

    override fun getTeam(team: List<Team>) {

        tv_nama_team_detail.text = team.single().strTeam
        tv_years_team_detail.text = team.single().intFormedYear
        Glide.with(applicationContext).load(team.single().strTeamBadge).into(image_team_details)
        Glide.with(applicationContext).load(team.single().strStadiumThumb).into(img_background_details)

        val fragmentAdapter = TeamPagerAdapter(supportFragmentManager, team.single(), idTeam)
        view_pager_team.adapter = fragmentAdapter
        tab_layout_team.setupWithViewPager(view_pager_team)
        teams = Team(
            strTeam = team.single().strTeam,
            strTeamBadge = team.single().strTeamBadge
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
