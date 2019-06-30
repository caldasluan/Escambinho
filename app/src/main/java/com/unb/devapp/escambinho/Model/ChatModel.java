package com.unb.devapp.escambinho.Model;

import java.io.Serializable;
import java.util.HashMap;

public class ChatModel implements Serializable {
    String id, user1, user2;
    HashMap<String, MessageModel> messages = new HashMap<>();

    public ChatModel() {
    }

    public ChatModel(String id, String user1, String user2, HashMap<String, MessageModel> messages) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.messages = messages;
    }

    public ChatModel(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public HashMap<String, MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, MessageModel> messages) {
        this.messages = messages;
    }
}
