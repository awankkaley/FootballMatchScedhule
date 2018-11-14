package com.kotlin.awankkaley.footballmatchscedhule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kotlin.awankkaley.footballmatchscedhule.feature.match.LastFragment
import com.kotlin.awankkaley.footballmatchscedhule.feature.match.NextFragment

class MatchPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabTitles = arrayOf("LAST MATCH", "NEXT MATCH")

    override fun getItem(position: Int):
            Fragment {
        return when (position) {
            0 -> {
                LastFragment()
            }
            else -> {
                NextFragment()
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