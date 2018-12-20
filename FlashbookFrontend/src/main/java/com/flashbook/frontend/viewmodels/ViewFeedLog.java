package com.flashbook.frontend.viewmodels;

public class ViewFeedLog {
    private int id;
    private String text;
    private int ownerId;
    private String ownerName;
    private String chartId;

    public ViewFeedLog() {
    }

    public ViewFeedLog(int id, String text, int ownerId, String ownerName, String chartId) {
        this.id = id;
        this.text = text;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.chartId = chartId;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getChartId() {
        return chartId;
    }

    public void setChartId(String chartId) {
        this.chartId = chartId;
    }
}
