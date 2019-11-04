package com.example.bkbiswas.shohojbibaho;

/**
 * Created by bk biswas on 10/16/2018.
 */

public class Notification {
    String sender, receiver, itemName, senderContactNo, senderEmail, receiverEmail;

    public Notification(String sender, String receiver, String itemName, String senderContactNo, String senderEmail, String receiverEmail) {
        this.sender = sender;
        this.receiver = receiver;
        this.itemName = itemName;
        this.senderContactNo = senderContactNo;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
    }

    public Notification() {
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSenderContactNo() {
        return senderContactNo;
    }

    public void setSenderContactNo(String senderContactNo) {
        this.senderContactNo = senderContactNo;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }
}