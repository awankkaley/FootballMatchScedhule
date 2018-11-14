package com.kotlin.awankkaley.footballmatchscedhule.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.feature.favourite.FavouriteHomeFragment
import com.kotlin.awankkaley.footballmatchscedhule.feature.match.HomeMatchFragment
import com.kotlin.awankkaley.footballmatchscedhule.feature.team.HomeTeamFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(HomeMatchFragment())
        nb_main.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_match -> {
                    loadFragment(HomeMatchFragment())
                }
                R.id.menu_team -> {
                    loadFragment(HomeTeamFragment())
                }
                R.id.menu_favo -> {
                    loadFragment(FavouriteHomeFragment())
                }
            }
            true
        }
        nb_main.selectedItemId = R.id.menu_match
    }


    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

    }

}
