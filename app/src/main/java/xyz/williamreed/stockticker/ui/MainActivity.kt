package xyz.williamreed.stockticker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xyz.williamreed.stockticker.R
import xyz.williamreed.stockticker.ui.watchlist.WatchListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_watch_list, WatchListFragment.newInstance())
            commit()
        }
    }
}
