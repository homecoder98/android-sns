package com.example.chatting.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.R
import com.example.chatting.adapter.FriendAdapter
import com.example.chatting.adapter.ReceivedAdapter
import com.example.chatting.databinding.FragmentFriendBinding
import com.example.chatting.databinding.FragmentReceivedBinding
import com.example.chatting.db.AppDatabase
import com.example.chatting.db.Profile

class ReceivedFragment : Fragment() {
    private var mBinding : FragmentReceivedBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentReceivedBinding.inflate(inflater,container,false)
        mBinding = binding

        //friend fragment로 이동
        mBinding!!.friendText.setOnClickListener {
            it.findNavController().navigate(R.id.action_receivedFragment_to_friendFragment)
        }

        val profile = Profile("운영자2","남자","25","경기","하이루2", "")
        val friend = mutableListOf<Profile>()
        friend.add(profile)
        val friendAdapter = ReceivedAdapter(friend)

        mBinding!!.receivedRecyclerview.adapter = friendAdapter
        mBinding!!.receivedRecyclerview.layoutManager = LinearLayoutManager(context)

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}