package com.kotlin.awankkaley.footballmatchscedhule.feature.favourite.favouritematch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.adapter.FavouriteAdapter
import com.kotlin.awankkaley.footballmatchscedhule.db.Favourite
import com.kotlin.awankkaley.footballmatchscedhule.feature.details.DetailActivity
import com.kotlin.awankkaley.footballmatchscedhule.utils.invisible
import com.kotlin.awankkaley.footballmatchscedhule.utils.visible
import kotlinx.android.synthetic.main.fragment_favourite.*
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class MatchFavouriteFragment : Fragment(),
    MatchFavouriteView {

    companion object {
        var idlingResourceFavourite = 1
    }

    override fun showProgress() {
        progressBar.visible()
    }

    override fun hideProgress() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Favourite>) {
        swipeRefresh.isRefreshing = false
        match.clear()
        match.addAll(data)
        adapters.notifyDataSetChanged()
    }

    private var match: MutableList<Favourite?> = mutableListOf()
    private lateinit var adapters: FavouriteAdapter
    private lateinit var presenterMatch: MatchFavouritePresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh : SwipeRefreshLayout


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenterMatch =
                MatchFavouritePresenter(
                    this,
                    context
                )
        progressBar = prog_fav
        swipeRefresh = swipe_refresh
        rv_fav.layoutManager = LinearLayoutManager(context)
        adapters = FavouriteAdapter(context,match){
            startActivity(intentFor<DetailActivity>(
                Pair("idEvent", it?.idEvent),
                Pair("idHomeTeam", it?.idHomeTeam),
                Pair("idAwayTeam", it?.idAwayTeam)))
        }
        rv_fav.adapter = adapters
        presenterMatch.getFavoList()

        swipeRefresh.onRefresh {
            presenterMatch.getFavoList()
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_favourite, container, false)

    }


}
