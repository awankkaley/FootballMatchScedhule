package com.kotlin.awankkaley.footballmatchscedhule.feature.detailteam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.model.Players
import kotlinx.android.synthetic.main.activity_scrolling_player_detail.*
import kotlinx.android.synthetic.main.content_scrolling_player_detail.*

class ScrollingPlayerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling_player_detail)
        setSupportActionBar(toolbar_detail_player)
        val player:Players? = intent.getParcelableExtra("player")
        tv_player_weight_detail.text = player?.strWeight
        tv_height_player_detail.text = player?.strHeight
        tv_description_player_detail.text = player?.strDescriptionEN
        tv_position_player_detail.text = player?.strPosition
        tv_player_name_details.text = player?.strPlayer
        Glide.with(applicationContext).load(player?.strThumb).into(image_player_detail)
    }
}
