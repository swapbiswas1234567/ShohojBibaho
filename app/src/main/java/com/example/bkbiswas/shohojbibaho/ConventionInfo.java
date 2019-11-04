package com.example.bkbiswas.shohojbibaho;

/**
 * Created by bk biswas on 10/2/2018.
 */

public class ConventionInfo {
    private String convName, location, area, capPeople, capParking, decCharge, floorCost,contactNo,userName,owner,email,uri;

    public ConventionInfo(String convName, String location, String area, String capPeople, String capParking, String decCharge, String floorCost, String contactNo, String userName, String owner, String email) {
        this.convName = convName;
        this.location = location;
        this.area = area;
        this.capPeople = capPeople;
        this.capParking = capParking;
        this.decCharge = decCharge;
        this.floorCost = floorCost;
        this.contactNo = contactNo;
        this.userName = userName;
        this.owner = owner;
        this.email = email;
        this.uri="https://firebasestorage.googleapis.com/v0/b/shohojbibaho-c63e6.appspot.com/o/Convention%20Info%2Fconventionl2.png?alt=media&token=15252478-b431-4caf-8a9d-ffa1e3b480ce";
    }

    public ConventionInfo() {
    }

    public String getConvName() {
        return convName;
    }

    public void setConvName(String convName) {
        this.convName = convName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCapPeople() {
        return capPeople;
    }

    public void setCapPeople(String capPeople) {
        this.capPeople = capPeople;
    }

    public String getCapParking() {
        return capParking;
    }

    public void setCapParking(String capParking) {
        this.capParking = capParking;
    }

    public String getDecCharge() {
        return decCharge;
    }

    public void setDecCharge(String decCharge) {
        this.decCharge = decCharge;
    }

    public String getFloorCost() {
        return floorCost;
    }

    public void setFloorCost(String floorCost) {
        this.floorCost = floorCost;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
