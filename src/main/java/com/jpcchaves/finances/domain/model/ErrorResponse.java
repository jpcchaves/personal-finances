package com.jpcchaves.finances.domain.model;

public class ErrorResponse {
    private String timestamp;
    private Integer status;
    private String title;
    private String message;

    public ErrorResponse(String timestamp, Integer status, String title, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.title = title;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
