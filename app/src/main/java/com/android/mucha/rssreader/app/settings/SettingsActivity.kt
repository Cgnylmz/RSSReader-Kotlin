package com.android.mucha.rssreader.app.settings

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.mucha.rssreader.R
import kotlinx.android.synthetic.main.activity_settings.*

/**
 * Screen with App settings.
 *
 * @author Patrik Mucha
 */
class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settings_feeds.setOnClickListener { view ->
            startActivity(Intent(this, SettingsFeedsActivity::class.java))
        }
    }
}
