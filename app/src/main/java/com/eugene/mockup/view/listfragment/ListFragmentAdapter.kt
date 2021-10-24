package com.eugene.mockup.view.listfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eugene.mockup.R
import com.eugene.mockup.model.Valute

class ListFragmentAdapter(private var itemClickListener: MyItemClickListener,
                          private var list: List<Valute>?): RecyclerView.Adapter<ListFragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFragmentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListFragmentAdapter.ViewHolder, position: Int) {
        holder.bind(list?.get(position))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(valute: Valute?) {
            itemView.findViewById<TextView>(R.id.item_text_name).text = valute!!.name
            itemView.findViewById<TextView>(R.id.item_text_char_code).text = valute.charCode

            itemView.setOnClickListener {
                itemClickListener.onMyItemClick(list, adapterPosition)
            }
       }
    }

    interface MyItemClickListener {
        fun onMyItemClick(list: List<Valute>?, position: Int)
    }
}

