package com.kotlin.awankkaley.footballmatchscedhule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.db.Favourite
import com.kotlin.awankkaley.footballmatchscedhule.utils.dateNewFormat
import kotlinx.android.synthetic.main.match_list.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavouriteAdapter(
    private val context: Context?
    , private val match: List<Favourite?>
    , private val listener: (Favourite?) -> Unit
) :
    RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.match_list, parent, false))

    override fun getItemCount() = match.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(match[position])
        holder.itemView.onClick {
            listener(match[position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(match: Favourite?) {
            itemView.tv_date_list.text = dateNewFormat(match?.dateEvent,false)
            itemView.tv_time_list.text = dateNewFormat(match?.strTime,true)
            itemView.tv_away_list.text = match?.strAwayTeam
            itemView.tv_home_list.text = match?.strHomeTeam
            itemView.tv_away_score_list.text = match?.intAwayScore
            itemView.tv_home_score_list.text = match?.intHomeScore
        }
    }
}