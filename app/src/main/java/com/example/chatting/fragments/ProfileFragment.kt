package com.example.chatting.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.chatting.Myapplication
import com.example.chatting.databinding.FragmentProfileBinding
import com.example.chatting.db.Profile

class ProfileFragment : Fragment() {
    private var mBinding : FragmentProfileBinding? = null
    private var mProfile : Profile? = null
    private val OPEN_GALLERY = 1
    private val RESULT_OK = -1
    private val RESULT_CANCELED = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater,container,false)
        mBinding = binding
        mProfile = Profile("홍길동","남자","20","서울","안녕하세요","")
        mBinding!!.profile = mProfile

        //SharedPreferences에서 프로필 가져오기
        val nick = Myapplication.prefs.getString("nickname","홍길동")
        val sex = Myapplication.prefs.getString("sex","남자")
        val age = Myapplication.prefs.getString("age","20")
        val local = Myapplication.prefs.getString("local","서울")
        val introduce = Myapplication.prefs.getString("introduce","안녕하세요")
        val picture = Myapplication.prefs.getString("picture","")

        try{
            Glide.with(inflater.context)
                .load(Uri.parse(picture))
                .into(mBinding!!.profileImage)
        }catch (e : java.lang.Exception){

        }


        mProfile.let {
            it!!.nickname = nick
            it!!.sex = sex
            it!!.age = age
            it!!.local = local
            it!!.introduce = introduce
        }

        //이미지 변경
        mBinding!!.profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent,OPEN_GALLERY)
        }

        //닉네임 변경 다이얼로그
        mBinding!!.changeNicknameBtn.setOnClickListener {
            val nickname_view = EditText(inflater.context)
            nickname_view.maxEms = 10
            val builder = AlertDialog.Builder(inflater.context)
            builder.setTitle("닉네임 변경")
                .setView(nickname_view)
                .setPositiveButton("변경",
                    DialogInterface.OnClickListener { dialog, id ->
                        val n = nickname_view.text
                        if(n.length in 1..9){
                            mBinding!!.nickname.text = n
                        }else{
                            Toast.makeText(inflater.context,"10글자 이하의 닉네임을 정해주세요",Toast.LENGTH_SHORT).show()
                        }

                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->
                    })

            builder.create().show()
        }

        //지역 변경 다이얼로그
        mBinding!!.changeLocalBtn.setOnClickListener {
            var choiced = "서울"
            var items = arrayOf<String>("서울","부산","인천","대전","대구","타 지역")
            val builder = AlertDialog.Builder(inflater.context)
            builder.setTitle("지역 변경")
                .setSingleChoiceItems(items, 0,
                    DialogInterface.OnClickListener { dialogInterface, i -> choiced = items[i] })
                .setPositiveButton("변경",
                    DialogInterface.OnClickListener { dialog, id ->
                        mBinding!!.local.text = choiced
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.create().show()
        }
        //소개 변경 다이얼로그
        mBinding!!.changeIntroduceBtn.setOnClickListener {
            val introduce_view = EditText(inflater.context)
            introduce_view.maxEms = 100
            val builder = AlertDialog.Builder(inflater.context)
            builder.setTitle("자기소개 변경")
                .setView(introduce_view)
                .setPositiveButton("변경",
                    DialogInterface.OnClickListener { dialog, id ->
                        val intro = introduce_view.text
                        if(intro.length<100){
                            mBinding!!.introduce.text = introduce_view.text
                        }else{
                            Toast.makeText(inflater.context,"100글자 이내의 소개를 써주세요",Toast.LENGTH_SHORT).show()
                        }

                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->
                    })

            builder.create().show()
        }


        //저장 버튼 누르면 프로필 정보 서버에 보냄
        mBinding!!.saveProfileBtn.setOnClickListener {
            val nickname = mBinding!!.nickname.text.toString()
            val sex = mBinding!!.sex.text.toString()
            val age = mBinding!!.age.text.toString()
            val local = mBinding!!.local.text.toString()
            val introduce = mBinding!!.introduce.text.toString()
            Myapplication.prefs.setString("nickname",nickname)
            Myapplication.prefs.setString("sex",sex)
            Myapplication.prefs.setString("age",age)
            Myapplication.prefs.setString("local",local)
            Myapplication.prefs.setString("introduce",introduce)
            //이미지까지 저장 후 서버로 전송


        }
        return mBinding?.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == OPEN_GALLERY)
        {
            if(resultCode == RESULT_OK)
            {
                var currentImageUri = data?.data

                try{
                    currentImageUri?.let {
//                        mBinding!!.profileImage.setImageURI(it)

                        Glide.with(layoutInflater.context)
                            .load(it)
                            .into(mBinding!!.profileImage)

//                        layoutInflater.context.contentResolver.takePersistableUriPermission(it,Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
//                        picture_uri = it.toString()

//                        val path = getFullPathFromUri(layoutInflater.context,it)

                        Myapplication.prefs.setString("picture",it.toString())
                    }
                }catch(e : Exception)
                {
                    e.printStackTrace()
                }
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(layoutInflater.context, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}