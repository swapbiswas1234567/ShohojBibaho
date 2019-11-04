package com.example.bkbiswas.shohojbibaho;

/**
 * Created by bk biswas on 10/14/2018.
 */

public class ConventionNotification {
    String sender, receiver, conventionName, senderContactNo;

    public ConventionNotification(String sender, String receiver, String conventionName, String senderContactNo) {
        this.sender = sender;
        this.receiver = receiver;
        this.conventionName = conventionName;
        this.senderContactNo = senderContactNo;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getConventionName() {
        return conventionName;
    }

    public void setConventionName(String conventionName) {
        this.conventionName = conventionName;
    }

    public String getSenderContactNo() {
        return senderContactNo;
    }

    public void setSenderContactNo(String senderContactNo) {
        this.senderContactNo = senderContactNo;
    }
}
