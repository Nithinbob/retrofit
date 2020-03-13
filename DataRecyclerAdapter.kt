package com.example.movielibrary.ui.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielibrary.R
import kotlinx.android.synthetic.main.movie_list_item.view.*

class DataRecyclerAdapterAdapter(val postList: List<Data>, val context: Activity?) :
    RecyclerView.Adapter<DataRecyclerAdapterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.movie_list_item,
            parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.txtPostTitle.text = postList.get(position).title
        holder.itemView.txtPostBody.text = postList.get(position).body

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}