package com.kotlin.awankkaley.footballmatchscedhule.feature.favourite.favouriteteam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.adapter.TeamFavouriteAdapter
import com.kotlin.awankkaley.footballmatchscedhule.db.TeamFavourite
import com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam.ScrollingDetailsActivity
import com.kotlin.awankkaley.footballmatchscedhule.utils.invisible
import com.kotlin.awankkaley.footballmatchscedhule.utils.visible
import kotlinx.android.synthetic.main.fragment_team_favourite.*
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh


class TeamFavouriteFragment : Fragment(),
    TeamFavouriteView {

    private var team: MutableList<TeamFavourite?> = mutableListOf()
    private lateinit var adapters: TeamFavouriteAdapter
    private lateinit var presenterTeam: TeamFavouritePresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun showProgress() {
        progressBar.visible()
    }

    override fun hideProgress() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<TeamFavourite>) {
        swipeRefresh.isRefreshing = false
        team.clear()
        team.addAll(data)
        adapters.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenterTeam =
                TeamFavouritePresenter(
                    this,
                    context
                )
        progressBar = prog_fav_team
        swipeRefresh = swipe_refresh_fav_team
        rv_fav_team.layoutManager = GridLayoutManager(context,2)
        adapters = TeamFavouriteAdapter(context,team){
            startActivity(intentFor<ScrollingDetailsActivity>(Pair("idTeam", it?.idTeam)))
        }
        rv_fav_team.adapter = adapters
        presenterTeam.getFavoList()

        swipeRefresh.onRefresh {
            presenterTeam.getFavoList()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_favourite, container, false)
    }


}
