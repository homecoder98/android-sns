package com.example.chatting.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.ProfileActivity
import com.example.chatting.R
import com.example.chatting.adapter.FriendsAdapter
import com.example.chatting.databinding.FragmentFriendsBinding
import com.example.chatting.model.FriendsModel

class FriendsFragment : Fragment() {
    private var mBinding : FragmentFriendsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFriendsBinding.inflate(inflater,container,false)
        mBinding = binding

        var item1 = FriendsModel(R.drawable.profile,"a","hi")
        var item2 = FriendsModel(R.drawable.profile,"b","hi")
        var item3 = FriendsModel(R.mipmap.ic_launcher,"c","hi")
        var adapter = FriendsAdapter(mutableListOf(item1,item2,item3))

        var friendsRecyclerView = mBinding?.friendsRecyclerview
        friendsRecyclerView?.adapter = adapter
        friendsRecyclerView?.layoutManager = LinearLayoutManager(context)

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}