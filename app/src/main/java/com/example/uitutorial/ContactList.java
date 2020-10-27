package com.example.uitutorial;

public class ContactList {
    private String name;
    private String imageUrl;
    private String newMessage;
    private String time;
    private int numNewMessage;

    public ContactList(String name, String imageUrl, String newMessage, String time, int numNewMessage) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.newMessage = newMessage;
        this.time = time;
        this.numNewMessage = numNewMessage;
    }


    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public String getTime() {
        return time;
    }

    public int getNumNewMessage() {
        return numNewMessage;
    }
}
