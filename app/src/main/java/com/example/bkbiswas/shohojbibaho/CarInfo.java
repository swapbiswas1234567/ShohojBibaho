package com.example.bkbiswas.shohojbibaho;

/**
 * Created by bk biswas on 9/21/2018.
 */

public class CarInfo {
    private String carRegistrationNo, carType,noOfSeats,driversName,driversLicenseNo,driversPhoneNo,expectedAmountperHour,contactNo,userName,owner,email,uri;

    public CarInfo() {
    }

    public CarInfo(String carRegistrationNo, String carType, String noOfSeats, String driversName, String driversLicenseNo, String driversPhoneNo, String expectedAmountperHour,String contactNo, String userName,String owner, String email) {
        this.carRegistrationNo = carRegistrationNo;
        this.carType = carType;
        this.noOfSeats = noOfSeats;
        this.driversName = driversName;
        this.driversLicenseNo = driversLicenseNo;
        this.driversPhoneNo = driversPhoneNo;
        this.expectedAmountperHour = expectedAmountperHour;
        this.contactNo = contactNo;
        this.userName = userName;
        this.owner = owner;
        this.email=email;
        this.uri="https://firebasestorage.googleapis.com/v0/b/shohojbibaho-c63e6.appspot.com/o/Car%20Info%2Fcarl2.png?alt=media&token=1dcbdb87-f246-4b9c-8bde-9ba3a9013c22";
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCarRegistrationNo() {
        return carRegistrationNo;
    }

    public String getCarType() {
        return carType;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public String getDriversName() {
        return driversName;
    }

    public String getDriversLicenseNo() {
        return driversLicenseNo;
    }

    public String getDriversPhoneNo() {
        return driversPhoneNo;
    }

    public String getExpectedAmountperHour() {
        return expectedAmountperHour;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getOwner() {
        return owner;
    }

    public String getUserName() {
        return userName;
    }

    public void setCarRegistrationNo(String carRegistrationNo) {
        this.carRegistrationNo = carRegistrationNo;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public void setDriversName(String driversName) {
        this.driversName = driversName;
    }

    public void setDriversLicenseNo(String driversLicenseNo) {
        this.driversLicenseNo = driversLicenseNo;
    }

    public void setDriversPhoneNo(String driversPhoneNo) {
        this.driversPhoneNo = driversPhoneNo;
    }

    public void setExpectedAmountperHour(String expectedAmountperHour) {
        this.expectedAmountperHour = expectedAmountperHour;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}