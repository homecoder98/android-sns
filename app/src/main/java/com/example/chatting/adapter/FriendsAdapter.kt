package com.example.chatting.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatting.ProfileActivity
import com.example.chatting.R
import com.example.chatting.model.FriendsModel
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import kotlin.coroutines.coroutineContext

class FriendsAdapter() : RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>(){
    public var items : MutableList<FriendsModel>? = null
    lateinit var context : Context
    lateinit var intent : Intent

    init {
        items = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        context = parent.context
        intent = Intent(context,ProfileActivity::class.java)
        return FriendsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.friend_item,parent,false))
    }
    //여기서 리스너 등록?
    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        var item = items!!.get(position)
        Picasso.get().load(item.item_src).into(holder.src)
        holder.nickname.text = item.item_nickname
        holder.introduce.text = item.item_introduce

        holder.itemView.setOnClickListener {
            intent.putExtra("nickname",item.item_nickname)
            intent.putExtra("img",item.item_src)
            intent.putExtra("introduce",item.item_introduce)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var src : ImageView = itemView.findViewById(R.id.item_img)
        var nickname : TextView = itemView.findViewById(R.id.item_nickname)
        var introduce : TextView = itemView.findViewById(R.id.item_introduce)
    }
}