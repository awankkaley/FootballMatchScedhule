package com.kotlin.awankkaley.footballmatchscedhule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kotlin.awankkaley.footballmatchscedhule.feature.favourite.favouritematch.MatchFavouriteFragment
import com.kotlin.awankkaley.footballmatchscedhule.feature.favourite.favouriteteam.TeamFavouriteFragment

class FavouritePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabTitles = arrayOf("MATCH", "TEAM")


    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                MatchFavouriteFragment()
            }
            else -> {
                TeamFavouriteFragment()
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



