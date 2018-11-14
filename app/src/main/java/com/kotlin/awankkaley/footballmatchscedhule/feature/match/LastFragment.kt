package com.kotlin.awankkaley.footballmatchscedhule.feature.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.adapter.LastNextMatchAdapter
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.feature.details.DetailActivity
import com.kotlin.awankkaley.footballmatchscedhule.model.League
import com.kotlin.awankkaley.footballmatchscedhule.model.LeagueResponse
import com.kotlin.awankkaley.footballmatchscedhule.model.Match
import com.kotlin.awankkaley.footballmatchscedhule.utils.invisible
import com.kotlin.awankkaley.footballmatchscedhule.utils.visible
import kotlinx.android.synthetic.main.fragment_last.view.*
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh


class LastFragment : Fragment(), LastNextFragmentView {

    companion object {
        var idlingResourceLast = 1
    }


    private var match: MutableList<Match?> = mutableListOf()
    private lateinit var presenterLastNext: LastNextFragmentPresenter
    private lateinit var adapters: LastNextMatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recycleView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var item: League
    private lateinit var swipeRefresh: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_last, container, false)
        val request = ApiRepository()
        val gson = Gson()
        progressBar = view.prog_last
        swipeRefresh = view.swipe_refresh_last
        recycleView = view.rv_last
        recycleView.layoutManager = LinearLayoutManager(context)
        presenterLastNext =
                LastNextFragmentPresenter(this, request, gson)
        presenterLastNext.getLeagueList()
        spinner = view.spinner_last
        adapters = LastNextMatchAdapter(context, match) {
            startActivity(intentFor<DetailActivity>(
                    Pair("idEvent", it?.idEvent),
                    Pair("idHomeTeam", it?.idHomeTeam),
                    Pair("idAwayTeam", it?.idAwayTeam)))
        }
        recycleView.adapter = adapters
        swipeRefresh.onRefresh {
            presenterLastNext.getMatchList(resources.getString(R.string.last_match), item.idLeague.toString())
        }
        return view
    }

    override fun showProgress() {
        progressBar.visible()
        recycleView.invisible()
        swipeRefresh.invisible()
    }

    override fun hideProgress() {
        progressBar.invisible()
        recycleView.visible()
        swipeRefresh.visible()
    }

    override fun showMatchList(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapters.notifyDataSetChanged()
    }

    override fun showLeagueList(data: LeagueResponse) {
        val spinnerAdapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            data.leagues.filter { it.strSport.equals("Soccer") })
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                item = spinner.selectedItem as League
                presenterLastNext.getMatchList(resources.getString(R.string.last_match), item.idLeague.toString())
            }

        }

    }


}
