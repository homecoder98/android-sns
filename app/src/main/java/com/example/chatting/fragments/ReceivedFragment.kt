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

        //send fragment로 이동
        mBinding!!.sendText.setOnClickListener {
            it.findNavController().navigate(R.id.action_receivedFragment_to_sendFragment)
        }

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}