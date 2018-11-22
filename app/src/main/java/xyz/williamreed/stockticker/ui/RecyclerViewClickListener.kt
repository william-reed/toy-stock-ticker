package xyz.williamreed.stockticker.ui

import android.view.View

// https://stackoverflow.com/questions/28296708/get-clicked-item-and-its-position-in-recyclerview
interface RecyclerViewClickListener {
    fun recyclerViewListClicked(v: View, position: Int)
}