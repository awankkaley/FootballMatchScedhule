package com.kotlin.awankkaley.footballmatchscedhule.feature.favourite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.adapter.FavouritePagerAdapter
import kotlinx.android.synthetic.main.fragment_favourite_home.view.*

class FavouriteHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_favourite_home, container, false)
        val fragmentAdapter = FavouritePagerAdapter(childFragmentManager)
        view.view_pager_fav.adapter = fragmentAdapter
        view.tab_layout_fav.setupWithViewPager(view.view_pager_fav)
        return view
    }


}
