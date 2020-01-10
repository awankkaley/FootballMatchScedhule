package com.kotlin.awankkaley.footballmatchscedhule.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.awankkaley.footballmatchscedhule.R
import com.kotlin.awankkaley.footballmatchscedhule.model.Match
import com.kotlin.awankkaley.footballmatchscedhule.utils.dateNewFormat
import com.kotlin.awankkaley.footballmatchscedhule.utils.invisible
import com.kotlin.awankkaley.footballmatchscedhule.utils.visible
import kotlinx.android.synthetic.main.match_list.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class LastNextMatchAdapter(
    private val context: Context?
    , private val match: List<Match?>
    , private val listener: (Match?) -> Unit
) :
    RecyclerView.Adapter<LastNextMatchAdapter.ViewHolder>() {
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
        var isAdded: Boolean = false

        fun bindItem(match: Match?) {

            itemView.tv_date_list.text = dateNewFormat(match?.dateEvent, false)
            itemView.tv_time_list.text = dateNewFormat(match?.strTime, true)
            itemView.tv_away_list.text = match?.strAwayTeam
            itemView.tv_home_list.text = match?.strHomeTeam
            itemView.tv_away_score_list.text = match?.intAwayScore
            itemView.tv_home_score_list.text = match?.intHomeScore
            notifIconSet()
            if (match?.intHomeScore == null) {
                itemView.checkbox_notif.visible()
            } else itemView.checkbox_notif.invisible()

            itemView.checkbox_notif.onClick {
                if (!isAdded) {
                    AddToCalender(match)
                    isAdded = !isAdded
                } else {
                    isAdded = !isAdded
                }
                notifIconSet()
            }

        }

        private fun notifIconSet() {
            if (isAdded) itemView.checkbox_notif.isChecked = true else itemView.checkbox_notif.isChecked = false
        }

        @SuppressLint("SimpleDateFormat")
        private fun AddToCalender(match: Match?) {
            val intent = Intent(Intent.ACTION_INSERT)
            val time = match?.strTime?.split("+")?.get(0)
            val date = match?.dateEvent
            val dateClock = "$date $time"
            val simpleDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            simpleDate.timeZone = TimeZone.getTimeZone("GMT")
            val dateFormat = simpleDate.parse(dateClock)
            val timeInMillis = dateFormat.time
            val end = timeInMillis + 5400000
            intent.type = "vnd.android.cursor.item/event"
            intent.data = CalendarContract.Events.CONTENT_URI
            intent.putExtra(CalendarContract.Events.TITLE, "${match?.strHomeTeam} VS ${match?.strAwayTeam}")
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timeInMillis)
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Streaming Om")
            itemView.context.startActivity(intent)
        }

    }
}



