package com.unb.devapp.escambinho.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserModel implements Serializable {
    private String id, name, birthday, course, imageUrl;
    Map<String, String> chats;

    public UserModel() {}

    public UserModel(String id, String name, String birthday, String curse, String imageUrl, Map<String, String> chats) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.course = curse;
        this.imageUrl = imageUrl;
        this.chats = chats;
    }

    public UserModel(String id, String name, String birthday, String curse, String imageUrl) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.course = curse;
        this.imageUrl = imageUrl;
        this.chats = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String curse) {
        this.course = curse;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Map<String, String> getChats() {
        return chats;
    }

    public void setChats(Map<String, String> chats) {
        this.chats = chats;
    }
}
