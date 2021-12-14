package com.example.chatting.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatting.ChattingRoomActivity
import com.example.chatting.R
import com.example.chatting.db.Profile

class ChattingRoomAdapter(items: MutableList<Profile>) : RecyclerView.Adapter<ChattingRoomAdapter.ChattingRoomViewHolder>() {
    var items : MutableList<Profile> = items
    lateinit var context : Context
    lateinit var intent : Intent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ChattingRoomViewHolder {
        context = parent.context
//        intent = Intent(context, ChattingRoomActivity::class.java)
        return ChattingRoomAdapter.ChattingRoomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chatting_right_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChattingRoomAdapter.ChattingRoomViewHolder, position: Int) {
        holder.item_nickname.text = items.get(position).nickname
        holder.item_introduce.text = items.get(position).introduce
        holder.item_img.setImageResource(R.drawable.profile)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ChattingRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val item_nickname = itemView.findViewById<TextView>(R.id.anonymous)
        val item_introduce = itemView.findViewById<TextView>(R.id.item_introduce)
        val item_img = itemView.findViewById<ImageView>(R.id.item_image)
    }
}