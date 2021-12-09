package com.example.chatting.model

class FriendsModel{
    var item_src : Int
    var item_nickname : String
    var item_introduce : String

    constructor(_src:Int, _nickname:String, _introduce:String){
        this.item_src = _src
        this.item_nickname = _nickname
        this.item_introduce = _introduce
    }

}