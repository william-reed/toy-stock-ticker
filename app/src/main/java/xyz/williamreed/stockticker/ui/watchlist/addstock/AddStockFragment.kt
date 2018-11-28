package xyz.williamreed.stockticker.ui.watchlist.addstock

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import xyz.williamreed.stockticker.R

class AddStockFragment : DialogFragment() {
    private lateinit var listener: OnFragmentInteractionListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it).apply {
                setTitle(R.string.add_ticker)
                setView(R.layout.add_ticker_edit_text)
                setPositiveButton("Add") { dialog, b ->
                    val editText = (dialog as AlertDialog).findViewById<EditText>(R.id.edit_text_add_ticker)
                    // TODO: whats my best bet for !! here?
                    listener.onStockTickerEntered(editText!!.text.toString())
                    dialog.dismiss()
                }
                setNegativeButton("Cancel") { dialog, _ ->
                    // TODO : is this needed / right?
                    dialog.cancel()
                }
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    interface OnFragmentInteractionListener {
        fun onStockTickerEntered(ticker: String)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddStockFragment()
    }
}
