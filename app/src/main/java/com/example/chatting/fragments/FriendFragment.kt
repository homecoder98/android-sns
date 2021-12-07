package com.example.chatting.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.chatting.R
import com.example.chatting.databinding.FragmentFriendBinding

class FriendFragment : Fragment() {
    private var mBinding : FragmentFriendBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFriendBinding.inflate(inflater,container,false)
        mBinding = binding

        //received fragment로 이동
        mBinding!!.receivedText.setOnClickListener {
            it.findNavController().navigate(R.id.action_friendFragment_to_receivedFragment)
        }

        //send fragment로 이동
        mBinding!!.sendText.setOnClickListener {
            it.findNavController().navigate(R.id.action_friendFragment_to_sendFragment)
        }

        return mBinding?.root
    }
    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}