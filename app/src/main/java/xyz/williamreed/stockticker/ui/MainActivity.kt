package xyz.williamreed.stockticker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xyz.williamreed.stockticker.R
import xyz.williamreed.stockticker.ui.watchlist.WatchListFragment
import xyz.williamreed.stockticker.ui.watchlist.addstock.AddStockFragment

class MainActivity : AppCompatActivity(), WatchListFragment.OnFragmentInteractionListener, AddStockFragment.OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_watch_list, WatchListFragment.newInstance())
            addToBackStack("watch list")
            commit()
        }
    }

    override fun onStockDeleted(ticker: String) {
        TODO("not implemented")
    }

    override fun onStockAddButtonClicked() {
        supportFragmentManager.beginTransaction().apply {
            AddStockFragment.newInstance().show(this, "add stock")
        }
    }

    override fun onStockTickerEntered(ticker: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // TODO: handle back / up

}
