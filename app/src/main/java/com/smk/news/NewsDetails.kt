package com.smk.news

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class NewsDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        assert(
            supportActionBar != null
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("News details");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val title = intent.getStringExtra("Title")
        val description = intent.getStringExtra("Description")
        val thumbnail = intent.getStringExtra("Thumbnail")

        findViewById<TextView>(R.id.tv_title).setText(title)
        findViewById<TextView>(R.id.tv_description).setText(description)

        if (thumbnail !== null) {
            Glide.with(this)
                .load(thumbnail)
                .into(findViewById<ImageView>(R.id.iv_thumbnail))
        } else {
            findViewById<ImageView>(R.id.iv_thumbnail).setImageResource(R.drawable.ic_launcher_background)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}