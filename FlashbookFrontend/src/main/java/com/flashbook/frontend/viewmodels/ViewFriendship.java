package com.flashbook.frontend.viewmodels;

import java.io.Serializable;

public class ViewFriendship implements Serializable {
    private int id;
    private String name;
    private boolean reciprocated;
    private boolean canAccept;

    public ViewFriendship() {
    }

    public ViewFriendship(int id, String name, boolean reciprocated, boolean canAccept) {
        this.id = id;
        this.name = name;
        this.reciprocated = reciprocated;
        this.canAccept = canAccept;
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

    public boolean isReciprocated() {
        return reciprocated;
    }

    public void setReciprocated(boolean reciprocated) {
        this.reciprocated = reciprocated;
    }

    public boolean isCanAccept() {
        return canAccept;
    }

    public void setCanAccept(boolean canAccept) {
        this.canAccept = canAccept;
    }
}
