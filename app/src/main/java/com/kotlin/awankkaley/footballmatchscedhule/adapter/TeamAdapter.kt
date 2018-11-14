package com.kotlin.awankkaley.footballmatchscedhule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.model.Teamses
import kotlinx.android.synthetic.main.team_list.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamAdapter(
    private val context: Context?,
    private val teamses: MutableList<Teamses?>,
    private val listener: (Teamses?) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.team_list, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teamses[position])
        holder.itemView.onClick {
            listener(teamses[position])
        }
    }

    override fun getItemCount(): Int = teamses.size
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(teamses: Teamses?) {
        itemView.name_team_list.text = teamses?.strTeam
        Glide.with(itemView.context).load(teamses?.strTeamBadge).into(itemView.image_team_list)

    }

}