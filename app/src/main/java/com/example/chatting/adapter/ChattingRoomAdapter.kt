package com.example.chatting.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
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
        when (viewType){
            0->{
                return ChattingRoomAdapter.ChattingRoomViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.chatting_right_item, parent, false))
            }

            1->{
                return ChattingRoomAdapter.ChattingRoomViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.chatting_left_item, parent, false))
            }

            }
        return return ChattingRoomAdapter.ChattingRoomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chatting_right_item, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        val profile = items[position]
        if(profile.is_right)
            return 0
        else
            return 1
    }

    override fun onBindViewHolder(holder: ChattingRoomAdapter.ChattingRoomViewHolder, position: Int) {
        holder.item_nickname.text = items[position].nickname
        holder.item_introduce.text = items[position].introduce
        holder.item_img.setImageResource(R.drawable.profile)
        if(items[position].profile!="") {
            val s = items[position].profile.toString()
            val imageBytes = Base64.decode(s, Base64.NO_WRAP)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            holder.item_img2.setImageBitmap(decodedImage)
            holder.item_introduce.isVisible = false
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ChattingRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val item_nickname = itemView.findViewById<TextView>(R.id.peer_name)
        val item_introduce = itemView.findViewById<TextView>(R.id.item_text)
        val item_img = itemView.findViewById<ImageView>(R.id.item_image)
        val item_img2 = itemView.findViewById<ImageView>(R.id.item_img)
    }
}