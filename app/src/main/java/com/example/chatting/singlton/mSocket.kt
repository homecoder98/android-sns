package com.example.chatting.singlton

import io.socket.client.IO
import io.socket.client.Socket

object mSocket {
    val socket : Socket? = IO.socket("http://10.0.2.2:3000")

//    fun connectSocket(){
//        if(socket==null) {
//            socket = IO.socket("http://10.0.2.2:3000")
//            if(!mSocket.socket!!.connected()){
//                mSocket.socket!!.connect()
//            }
//        }
//    }
}