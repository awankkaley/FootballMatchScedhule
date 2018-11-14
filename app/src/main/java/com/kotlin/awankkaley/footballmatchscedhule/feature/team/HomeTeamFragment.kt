package com.kotlin.awankkaley.footballmatchscedhule.feature.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.adapter.TeamAdapter
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam.ScrollingDetailsActivity
import com.kotlin.awankkaley.footballmatchscedhule.model.League
import com.kotlin.awankkaley.footballmatchscedhule.model.LeagueResponse
import com.kotlin.awankkaley.footballmatchscedhule.model.Teamses
import com.kotlin.awankkaley.footballmatchscedhule.utils.invisible
import com.kotlin.awankkaley.footballmatchscedhule.utils.visible
import kotlinx.android.synthetic.main.fragment_home_team.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class HomeTeamFragment : Fragment(), TeamView {
    companion object {
        var idlingResourceTeam = 1
    }

    private var teamses: MutableList<Teamses?> = mutableListOf()
    private lateinit var teamPresenter: TeamPresenter
    private lateinit var adapters: TeamAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recycleView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var item: League
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        setHasOptionsMenu(true)
        progressBar = prog_team
        swipeRefresh = swipe_refresh_team
        recycleView = rv_team
        recycleView.layoutManager = LinearLayoutManager(context)
        teamPresenter = TeamPresenter(this, request, gson)
        teamPresenter.getLeaguaList()
        spinner = spinner_team
        adapters = TeamAdapter(context, teamses) {
            startActivity<ScrollingDetailsActivity>("idTeam" to it?.idTeam)
        }
        recycleView.adapter = adapters
        recycleView.layoutManager = GridLayoutManager(context, 2)
        swipeRefresh.onRefresh {
            val url = item.strLeague.toString().replace(" ", "%20")
            teamPresenter.getTeams(url)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home_team, container, false)

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

    override fun showTeamList(data: List<Teamses>) {
        teamses.clear()
        teamses.addAll(data)
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
                val url = item.strLeague.toString().replace(" ", "%20")
                teamPresenter.getTeams(url)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)
        val searchMenu = menu?.findItem(R.id.search_menu_item)
        val searchView = searchMenu?.actionView as SearchView?
        searchView?.queryHint = "Search by Teams"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                teamPresenter.getTeamsSearch(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.toString().isEmpty()) {
                    spinner.visible()
                } else {
                    spinner.invisible()
                }
                return true
            }

        })
    }

    override fun showTeamSearch(data: List<Teamses>) {
        teamses.clear()
        teamses.addAll(data)
        adapters.notifyDataSetChanged()
    }
}
