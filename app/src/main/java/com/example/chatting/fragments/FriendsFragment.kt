package com.example.chatting.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.ProfileActivity
import com.example.chatting.R
import com.example.chatting.adapter.FriendsAdapter
import com.example.chatting.databinding.FragmentFriendsBinding
import com.example.chatting.model.FriendsModel
import com.example.chatting.retrofit.Friends
import com.example.chatting.retrofit.IRetrofit
import com.example.chatting.retrofit.RetrofitClient
import com.google.gson.JsonElement
import retrofit2.*

class FriendsFragment : Fragment() {
    private var mBinding : FragmentFriendsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFriendsBinding.inflate(inflater,container,false)
        mBinding = binding

        val retrofit = RetrofitClient.getClient()
        val service = retrofit?.create(IRetrofit::class.java)

        service!!.getFriends().enqueue(object : Callback<ArrayList<Friends>> {
            override fun onResponse(call: Call<ArrayList<Friends>>, response: Response<ArrayList<Friends>>) {
                var adapter = FriendsAdapter()
                for(i in response.body()!!){
                    val nickname = i.nickname
                    val sex = i.sex
                    val age = i.age
                    val local = i.local
                    val introduce = i.introduce
                    val profile = i.profile
                    var item = FriendsModel(profile,nickname,introduce)
                    adapter.items!!.add(item)
                }
                var friendsRecyclerView = mBinding?.friendsRecyclerview
                friendsRecyclerView?.adapter = adapter
                friendsRecyclerView?.layoutManager = LinearLayoutManager(context)

            }

            override fun onFailure(call: Call<ArrayList<Friends>>, t: Throwable) {
                Log.d("data","fail")
            }
        })

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

}