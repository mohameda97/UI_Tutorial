package com.example.uitutorial;

public class MessageInfoList {

    private String imageUrl;
    private String message;
    private int sender;

    public MessageInfoList(String imageUrl, String message, int sender) {
        this.imageUrl = imageUrl;
        this.message = message;
        this.sender = sender;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public String getMessage() {
        return message;
    }

    public int getSender() {
        return sender;
    }
}
