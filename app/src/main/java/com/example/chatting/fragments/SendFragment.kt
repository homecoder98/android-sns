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
import com.example.chatting.databinding.FragmentReceivedBinding
import com.example.chatting.databinding.FragmentSendBinding

class SendFragment : Fragment() {
    private var mBinding : FragmentSendBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSendBinding.inflate(inflater,container,false)
        mBinding = binding

        //friend fragment로 이동
        mBinding!!.friendText.setOnClickListener {
            it.findNavController().navigate(R.id.action_sendFragment_to_friendFragment)
        }

        //received fragment로 이동
        mBinding!!.receivedText.setOnClickListener {
            it.findNavController().navigate(R.id.action_sendFragment_to_receivedFragment)
        }

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}