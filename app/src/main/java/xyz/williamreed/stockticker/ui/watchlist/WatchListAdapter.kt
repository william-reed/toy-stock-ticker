package xyz.williamreed.stockticker.ui.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.williamreed.stockticker.R
import xyz.williamreed.stockticker.data.models.Quote

// TODO: better to take in LiveData, Observable, or List here?
class WatchListAdapter(private var data: MutableList<Quote>) :
    RecyclerView.Adapter<WatchListAdapter.WatchListViewHolder>() {

    class WatchListViewHolder(private val watchListCardView: CardView) : RecyclerView.ViewHolder(watchListCardView) {
        // TODO: use android kotlin extensions for getting views directly
        val symbol: TextView = watchListCardView.findViewById(R.id.symbol)
        val companyName: TextView = watchListCardView.findViewById(R.id.companyName)
        val primaryExchange: TextView = watchListCardView.findViewById(R.id.primaryExchange)
        val latestPrice: TextView = watchListCardView.findViewById(R.id.latestPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_cardview_watch, parent, false) as CardView
        return WatchListViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.symbol.text = data[position].symbol
        holder.companyName.text = data[position].companyName
        holder.primaryExchange.text = data[position].primaryExchange
        holder.latestPrice.text = data[position].latestPrice.toString()
    }

    fun updateData(newData: List<Quote>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                data[oldItemPosition].symbol == newData[newItemPosition].symbol

            override fun getOldListSize() = data.size
            override fun getNewListSize() = newData.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                data[oldItemPosition] == newData[newItemPosition]

            // TODO: might want to override getChangePayload for some animation changes
        })

        data.clear()
        data.addAll(newData)

        diff.dispatchUpdatesTo(this)
    }

}