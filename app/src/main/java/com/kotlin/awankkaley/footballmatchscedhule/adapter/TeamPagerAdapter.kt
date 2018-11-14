package com.kotlin.awankkaley.footballmatchscedhule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam.OverviewFragment
import com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam.PlayersFragment
import com.kotlin.awankkaley.footballmatchscedhule.model.Team

open class TeamPagerAdapter(fm: FragmentManager,team:Team,idTeam:String) : FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf("OVERVIEW", "PLAYERS")
    private val teams:Team = team
    private val idTeams:String = idTeam

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                OverviewFragment.newInstance(teams)
            }
            else -> {
                PlayersFragment.newInstance(idTeams)
            }
        }
    }

    override fun getCount(): Int {
        return tabTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}