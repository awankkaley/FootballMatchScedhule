package com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.model.Team
import kotlinx.android.synthetic.main.fragment_overview.view.*

class OverviewFragment : Fragment() {
    private var teams: Team? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            teams = arguments!!.getParcelable(EXTRA_DATA)
        }
        view.tv_overview_team_details.text = teams?.strDescriptionEN
        view.tv_country_team_details.text = teams?.strCountry
        view.tv_manager_team_details.text = teams?.strManager
        view.tv_stadiumcapacity_team_details.text = teams?.intStadiumCapacity
        view.tv_stadiumlocation_team_details.text = teams?.strStadiumLocation
        view.tv_stadiumname_team_details.text = teams?.strStadium
        Glide.with(view.context).load(teams?.strTeamJersey).into(view.img_jersey_team_detail)
    }

    companion object {
        private const val EXTRA_DATA = "extra_data"
        fun newInstance(team: Team): OverviewFragment {
            val fragment = OverviewFragment()
            val args = Bundle()
            args.putParcelable(EXTRA_DATA, team)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_overview, container, false)
    }



}
