package com.unb.devapp.escambinho.Model;

public class ItemModel {
    String userId, title, imageUrl;

    public ItemModel() {
    }

    public ItemModel(String userId, String title, String imageUrl) {
        this.userId = userId;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
