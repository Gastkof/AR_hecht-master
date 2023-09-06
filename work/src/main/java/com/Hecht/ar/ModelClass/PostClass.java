package com.Hecht.ar.ModelClass;

import java.util.ArrayList;
import java.util.List;


public class PostClass {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<PostClass> getRepliess() {
        return repliess;
    }

    public void setRepliess(List<PostClass> repliess) {
        this.repliess = repliess;
    }

    public String id;
    public String post;
    public String author;
    public String image;
    public int level;
    public int parentId;
    public boolean flag;


    public List<PostClass> repliess = new ArrayList<>();

    public PostClass() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public PostClass(String id, String post, String author, int level, int parentId, boolean flag,List<PostClass> replyList,String image ) {
        this.id = id;
        this.post = post;
        this.author = author;
        this.level = level;
        this.parentId = parentId;
        this.flag = flag;
        this.image = image;
        this.repliess.addAll(replyList);
    }

}

