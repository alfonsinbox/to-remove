package com.flashbook.frontend.viewmodels;

public class ViewUser {
    private int id;
    private String name;
    private boolean isFriend;

    public ViewUser() {
    }

    public ViewUser(int id, String name, boolean isFriend) {
        this.id = id;
        this.name = name;
        this.isFriend = isFriend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }
}
