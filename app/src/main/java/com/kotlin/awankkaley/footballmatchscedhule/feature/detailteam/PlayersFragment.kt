package com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.adapter.PlayerAdapter
import com.kotlin.awankkaley.footballmatchscedhule.api.ApiRepository
import com.kotlin.awankkaley.footballmatchscedhule.model.Players
import kotlinx.android.synthetic.main.fragment_players.view.*
import org.jetbrains.anko.support.v4.intentFor


class PlayersFragment : Fragment(), PlayersView {

    private var player: MutableList<Players?> = mutableListOf()
    private var idTeam: String? = null
    private lateinit var presenter: PlayersPresenter
    private lateinit var adapters: PlayerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            idTeam = arguments!!.getString(EXTRA_DATA)
        }
        val request = ApiRepository()
        val gson = Gson()
        view.rv_player.layoutManager = GridLayoutManager(context,2)
        presenter = PlayersPresenter(this, request, gson)
        presenter.getPlayers(idTeam)
        adapters = PlayerAdapter(context, player) {
            startActivity(intentFor<ScrollingPlayerDetailActivity>("player" to it))
        }
        view.rv_player.adapter = adapters

    }

    companion object {
        private const val EXTRA_DATA = "extra_data"

        fun newInstance(idTeam: String): PlayersFragment {
            val fragment = PlayersFragment()
            val args = Bundle()
            args.putString(EXTRA_DATA, idTeam)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun getPlayers(players: List<Players>) {
        player.clear()
        player.addAll(players)
        adapters.notifyDataSetChanged()

    }

}
