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
import androidx.recyclerview.widget.RecyclerView
import com.example.chatting.ChattingRoomActivity
import com.example.chatting.R
import com.example.chatting.db.AppDatabase
import com.example.chatting.db.Profile

class FriendAdapter(profiles : MutableList<Profile>) : RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {
    var items : MutableList<Profile> = profiles
    lateinit var context : Context
    lateinit var intent : Intent
    private var db : AppDatabase? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        context = parent.context
        db = AppDatabase.getInstance(context)
        intent = Intent(context, ChattingRoomActivity::class.java)
        return FriendViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.friend_item,parent,false))
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val item = items.get(position)
        holder.item_nickname.text = item.nickname
        holder.item_profile.setImageResource(R.drawable.profile)
        holder.item_introduce.text = item.introduce

        holder.itemView.setOnClickListener {
            context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            val profile = Profile(item.nickname,item.sex,item.age,item.local,item.introduce,"")
            db?.profileDao()?.delete(profile)
            items.removeAt(position)
            this.notifyItemChanged(position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val item_profile = itemView.findViewById<ImageView>(R.id.item_img)
        val item_nickname = itemView.findViewById<TextView>(R.id.item_nickname)
        val item_introduce = itemView.findViewById<TextView>(R.id.item_introduce)
    }
}