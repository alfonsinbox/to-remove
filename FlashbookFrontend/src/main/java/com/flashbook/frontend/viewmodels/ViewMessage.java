package com.flashbook.frontend.viewmodels;

public class ViewMessage {
    private int id;
    private String text;
    private String senderName;

    public ViewMessage() {
    }

    public ViewMessage(int id, String text, String senderName) {
        this.id = id;
        this.text = text;
        this.senderName = senderName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
