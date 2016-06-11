package com.example.chatapplication;

import java.util.Date;

/**
 * Created by sudhanshu on 10/5/16.
 */
public class Message {
    public Message(String fromName, String message, String toName, Date dateTime) {
        this.fromName = fromName;
        this.message = message;
        this.toName = toName;
        this.dateTime = dateTime;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    private String fromName, message,toName;
    private Date dateTime;
}
