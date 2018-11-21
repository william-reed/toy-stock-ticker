package xyz.williamreed.stockticker.ui.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import xyz.williamreed.stockticker.R
import xyz.williamreed.stockticker.data.models.Quote

class WatchListAdapter(private val data: List<Quote>) :
    RecyclerView.Adapter<WatchListAdapter.WatchListViewHolder>() {
    class WatchListViewHolder(private val watchListCardView: CardView) : RecyclerView.ViewHolder(watchListCardView) {
        val symbol: TextView = watchListCardView.findViewById(R.id.symbol)
        val companyName: TextView = watchListCardView.findViewById(R.id.companyName)
        val primaryExchange: TextView = watchListCardView.findViewById(R.id.primaryExchange)
        val latestPrice: TextView = watchListCardView.findViewById(R.id.latestPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_watch, parent, false) as CardView
        return WatchListViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.symbol.text = data[position].symbol
        holder.companyName.text = data[position].companyName
        holder.primaryExchange.text = data[position].primaryExchange
        holder.latestPrice.text = data[position].latestPrice.toString()
    }

}