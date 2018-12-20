package com.flashbook.frontend.viewmodels;

public class CreateMessage {
    private int receiverId;
    private String text;

    public CreateMessage() {
    }

    public CreateMessage(int receiverId, String text) {
        this.receiverId = receiverId;
        this.text = text;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
