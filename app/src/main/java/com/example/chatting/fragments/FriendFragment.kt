package com.example.chatting.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chatting.databinding.FragmentFriendBinding

class FriendFragment : Fragment() {
    private var mBinding : FragmentFriendBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("frag","friend oncreate")
        val binding = FragmentFriendBinding.inflate(inflater,container,false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}