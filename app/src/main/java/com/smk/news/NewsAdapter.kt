package com.smk.news

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.time.*


class NewsAdapter(
    val context: Context
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val allNews = ArrayList<News>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsTV = itemView.findViewById<TextView>(R.id.tv_title)
        val dateTV = itemView.findViewById<TextView>(R.id.tv_createdDate)
        val upsTV = itemView.findViewById<TextView>(R.id.tv_ups)
        val thumbnail = itemView.findViewById<ImageView>(R.id.iv_thumbnail)
        val newsLayout = itemView.findViewById<CardView>(R.id.cv_news)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.news_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.newsTV.setText(allNews.get(position).title)
        allNews.get(position).created?.let {
            Instant.ofEpochSecond(it.toLong())
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            ?.let { holder.dateTV.setText(it.toString()) }
        }
        val media = allNews.get(position).thumbnail
        if (media !== null) {
            Glide.with(context)
                .load(media)
                .into(holder.thumbnail)
        } else {
            holder.thumbnail.setImageResource(R.drawable.ic_launcher_background)
        }
        holder.newsLayout.setOnClickListener {
            val intent = Intent(context,NewsDetails::class.java)
            intent.putExtra("Title",allNews.get(position).title)
            intent.putExtra("Description",allNews.get(position).selftext)
            intent.putExtra("Thumbnail",allNews.get(position).thumbnail)
            context.startActivity(intent)
        }
        allNews.get(position).ups?.let { holder.upsTV.setText(it.toString()) }
    }

    override fun getItemCount(): Int {
        return allNews.size
    }

    fun updateList(newList: List<News>) {
        allNews.clear()
        allNews.addAll(newList)
        notifyDataSetChanged()
    }

}