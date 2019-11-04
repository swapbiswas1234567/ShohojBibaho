package com.example.bkbiswas.shohojbibaho;

/**
 * Created by bk biswas on 10/12/2018.
 */

public class CarNotification {
    String sender, receiver, carNo, senderContactNo;

    public CarNotification() {
    }

    public CarNotification(String sender, String receiver, String carNo, String senderContactNo) {
        this.sender = sender;
        this.receiver = receiver;
        this.carNo = carNo;
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

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getSenderContactNo() {
        return senderContactNo;
    }

    public void setSenderContactNo(String senderContactNo) {
        this.senderContactNo = senderContactNo;
    }
}
