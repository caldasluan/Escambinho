package com.unb.devapp.escambinho.Model;

public class ChatViewModel {
    private String chatId, image, name, message, newMessage;

    public ChatViewModel(String chatId, String image, String name, String message, String newMessage) {
        this.chatId = chatId;
        this.image = image;
        this.name = name;
        this.message = message;
        this.newMessage = newMessage;
    }

    public ChatViewModel() { }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
