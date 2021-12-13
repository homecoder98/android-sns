package com.example.chatting.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatting.ChattingRoomActivity
import com.example.chatting.ProfileActivity
import com.example.chatting.R
import com.example.chatting.model.FriendsModel

class ChattingAdapter : RecyclerView.Adapter<ChattingAdapter.ChattingViewHolder>() {
    public var items : MutableList<String>? = null
    lateinit var context : Context
    lateinit var intent : Intent

    init {
        items = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingViewHolder {
        context = parent.context
        intent = Intent(context, ChattingRoomActivity::class.java)
        return ChattingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chatting_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChattingViewHolder, position: Int) {
        var item = items!!.get(position)
        holder.annonymous.text = item

        holder.itemView.setOnClickListener {
            intent.putExtra("anonymous",item)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    class ChattingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var annonymous : TextView = itemView.findViewById(R.id.anonymous)
    }
}