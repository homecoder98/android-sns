package com.example.chatting.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.R
import com.example.chatting.adapter.FriendAdapter
import com.example.chatting.databinding.FragmentFriendBinding
import com.example.chatting.db.AppDatabase
import com.example.chatting.db.Profile
import kotlinx.coroutines.CoroutineScope
import java.io.ByteArrayOutputStream

class FriendFragment : Fragment() {
    private var mBinding : FragmentFriendBinding? = null
    private var db : AppDatabase? = null

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

        val profile = Profile("운영자","남자","25","경기","하이루", "")
        val friend = mutableListOf<Profile>()
        friend.add(profile)

        db = AppDatabase.getInstance(inflater.context)
        db?.profileDao()?.getAll().let {
            if(it!=null){
                Log.d("db",it.toString())
                for(i in it)
                    friend.add(i)
            }
        }


        val friendAdapter = FriendAdapter(friend)

        mBinding!!.friendRecyclerview.adapter = friendAdapter
        mBinding!!.friendRecyclerview.layoutManager = LinearLayoutManager(context)

        return mBinding?.root
    }
    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}