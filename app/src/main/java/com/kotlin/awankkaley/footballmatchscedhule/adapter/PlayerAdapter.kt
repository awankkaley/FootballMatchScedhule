package com.kotlin.awankkaley.footballmatchscedhule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.model.Players
import kotlinx.android.synthetic.main.player_list.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayerAdapter(
    private val context: Context?,
    private val players: MutableList<Players?>,
    private val listener: (Players?) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.player_list, parent, false))


    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(players[position])
        holder.itemView.onClick {
            listener(players[position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(players: Players?) {
            itemView.name_player_list.text = players?.strPlayer
            itemView.pos_player_list.text = players?.strPosition
            Log.d("testing",players.toString())
            Glide.with(itemView.context).load(players?.strCutout).into(itemView.image_player_list)
        }

    }
}