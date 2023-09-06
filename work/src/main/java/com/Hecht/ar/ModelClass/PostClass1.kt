package com.Hecht.ar.ModelClass

class PostClass1(
    id: String,
    post: String,
    author: String,
    level: Int,
    parentID: Int,
    flag: Boolean,
    repltList: MutableList<PostClass1>
) {
    var id: String? = null
    var post: String? = null
    var author: String? = null
    var level = 0
    var parentId = 0
    var flag = false
    var repliesList: List<PostClass1>? = null




}