package com.kotlin.awankkaley.footballmatchscedhule.feature.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.adapter.MatchPagerAdapter
import com.kotlin.awankkaley.footballmatchscedhule.feature.match.search.SearchMatchMatchActivity
import kotlinx.android.synthetic.main.fragment_home_match.view.*
import org.jetbrains.anko.startActivity

class HomeMatchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home_match, container, false)
        setHasOptionsMenu(true)
        val fragmentAdapter = MatchPagerAdapter(childFragmentManager)
        view.view_pager.adapter = fragmentAdapter
        view.tab_layout.setupWithViewPager(view.view_pager)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search_no_viewclass,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.search_menu_item_no->{
                context?.startActivity<SearchMatchMatchActivity>()
                true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }


}
