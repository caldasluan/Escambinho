package com.unb.devapp.escambinho.Model;

public class MessageModel {
    String id, userId, message;

    public MessageModel() {
    }

    public MessageModel(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }
    public MessageModel(String id, String userId, String message) {
        this.userId = userId;
        this.id = id;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
