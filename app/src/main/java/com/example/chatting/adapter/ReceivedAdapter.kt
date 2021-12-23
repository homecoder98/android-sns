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
import com.example.chatting.db.AppDatabase
import com.example.chatting.db.Profile

class ReceivedAdapter(profiles : MutableList<Profile>) : RecyclerView.Adapter<ReceivedAdapter.ReceivedViewHolder>(){
    var items : MutableList<Profile> = profiles
    lateinit var context : Context
    private var db : AppDatabase? = null
//    lateinit var intent : Intent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceivedViewHolder {
        context = parent.context
        db = AppDatabase.getInstance(context)
//        intent = Intent(context, ChattingRoomActivity::class.java)
        return ReceivedAdapter.ReceivedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.received_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReceivedViewHolder, position: Int) {
        val item = items.get(position)
        holder.item_nickname.text = item.nickname
        holder.item_profile.setImageResource(R.drawable.profile)
        holder.item_introduce.text = item.introduce

//        holder.itemView.setOnClickListener {
//            context.startActivity(intent)
//        }
        holder.accept_btn.setOnClickListener {
            //친구 받기
            //db에 추가
            val profile = Profile(item.nickname,item.sex,item.age,item.local,item.introduce,"")
            db?.profileDao()?.inserProfile(profile)
            items.removeAt(position)
            this.notifyItemChanged(position)
        }
        holder.deny_btn.setOnClickListener {
            //친구 거절
            items.removeAt(position)
            this.notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ReceivedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val item_profile = itemView.findViewById<ImageView>(R.id.from_img)
        val item_nickname = itemView.findViewById<TextView>(R.id.from_nickname)
        val item_introduce = itemView.findViewById<TextView>(R.id.from_msg)
        val accept_btn = itemView.findViewById<TextView>(R.id.accept_btn)
        val deny_btn = itemView.findViewById<TextView>(R.id.deny_btn)
    }
}