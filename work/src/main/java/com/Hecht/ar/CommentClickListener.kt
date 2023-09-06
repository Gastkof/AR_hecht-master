package com.Hecht.ar

interface CommentClickListener {
    fun onSendComment(author: String, message: String, level: Int, parentId: Int? = null,pos:Int,flag:Boolean)
}