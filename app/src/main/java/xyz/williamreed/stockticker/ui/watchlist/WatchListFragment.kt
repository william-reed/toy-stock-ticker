package xyz.williamreed.stockticker.ui.watchlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import xyz.williamreed.stockticker.R
import xyz.williamreed.stockticker.StockTickerApplication
import xyz.williamreed.stockticker.data.models.Quote
import xyz.williamreed.stockticker.ui.ViewModelFactory
import javax.inject.Inject


class WatchListFragment : Fragment() {
    //    private var listener: OnFragmentInteractionListener? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: WatchListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (context!!.applicationContext as StockTickerApplication).component.inject(this)
        val view = inflater.inflate(R.layout.fragment_watch_list, container, false)
        setupLiveData(view, viewModelFactory)
        setupAdapter(view)
        Log.i("Watch List Fragment", "fragment created")
        return view
    }

    private fun setupAdapter(view: View) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = WatchListAdapter(mutableListOf())

        // TODO: convert to kotlin extensions
        recyclerView = view.findViewById<RecyclerView>(R.id.watchListRecyclerView).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun setupLiveData(view: View, viewModelFactory: ViewModelFactory) {
        val model = ViewModelProviders.of(this, viewModelFactory).get(WatchListViewModel::class.java)
        model.getErrors().observe(this, Observer<String> {
            Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
        })

        model.getQuotes().observe(this, Observer<List<Quote>> {
            viewAdapter.updateData(it)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    // TODO: long click on RV item should delete it from the list

    override fun onDetach() {
        super.onDetach()
//        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onStockDeleted(ticker: String)
    }

    companion object {
        @JvmStatic
        fun newInstance() = WatchListFragment()
    }
}
