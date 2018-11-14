package com.kotlin.awankkaley.footballmatchscedhule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.db.TeamFavourite
import kotlinx.android.synthetic.main.team_list.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamFavouriteAdapter (private val context: Context?
                            , private val team: List<TeamFavourite?>
                            ,private val listener: (TeamFavourite?) -> Unit):
    RecyclerView.Adapter<TeamFavouriteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.team_list, parent, false))

    override fun getItemCount(): Int = team.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(team[position])
        holder.itemView.onClick {
            listener(team[position])
        }}


    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        fun bindItem(team:TeamFavourite?){
            itemView.name_team_list.text = team?.strTeam
            Glide.with(itemView.context).load(team?.strTeamBadge).into(itemView.image_team_list)
        }
    }
}