package com.example.chatting.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.adapter.ChattingAdapter
import com.example.chatting.databinding.FragmentChattingBinding

class ChattingFragment : Fragment() {
    private var mBinding : FragmentChattingBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChattingBinding.inflate(inflater,container,false)
        mBinding = binding

        val adapter = ChattingAdapter()

        adapter.items!!.add("oh young taek")
        adapter.items!!.add("ju ru ru")
        adapter.items!!.add("oh young taek22")

        mBinding!!.ChattingRecyclerView.adapter = adapter
        mBinding!!.ChattingRecyclerView.layoutManager = LinearLayoutManager(context)

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}