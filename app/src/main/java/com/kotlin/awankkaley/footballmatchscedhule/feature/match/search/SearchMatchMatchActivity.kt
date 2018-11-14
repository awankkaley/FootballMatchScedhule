package com.kotlin.awankkaley.footballmatchscedhule.feature.match.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.ProgressBar
import android.widget.SearchView
import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.adapter.LastNextMatchAdapter
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.feature.details.DetailActivity
import com.kotlin.awankkaley.footballmatchscedhule.model.Match
import com.kotlin.awankkaley.footballmatchscedhule.utils.invisible
import com.kotlin.awankkaley.footballmatchscedhule.utils.visible
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.intentFor

class SearchMatchMatchActivity : AppCompatActivity(),
    SearchMatchView {
    companion object {
        var idlingResourceSearchMatch = 1
    }

    private var match: MutableList<Match?> = mutableListOf()
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapters: LastNextMatchAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val request = ApiRepository()
        val gson = Gson()
        rv_match_search.layoutManager = LinearLayoutManager(this)

        progressBar = prog_search_match
        presenter = SearchMatchPresenter(
            this,
            request,
            gson
        )
        presenter.getMatchSearch("")
        adapters = LastNextMatchAdapter(this, match) {
            startActivity(
                intentFor<DetailActivity>(
                    Pair("idEvent", it?.idEvent),
                    Pair("idHomeTeam", it?.idHomeTeam),
                    Pair("idAwayTeam", it?.idAwayTeam)
                )
            )
        }
        rv_match_search.adapter = adapters
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchMenu = menu?.findItem(R.id.search_menu_item)
        val searchView = searchMenu?.actionView as SearchView?
        searchView?.queryHint = "Search by Teams"
        searchView?.isIconified = false
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getMatchSearch(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getMatchSearch(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun showProgress() {
        progressBar.visible()
    }

    override fun hideProgress() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        match.clear()
        match.addAll(data.filter { it.strSport.equals("Soccer") })
        adapters.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
