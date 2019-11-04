package com.example.bkbiswas.shohojbibaho;


public class CateringInfo {
    private String cateringName, areaOfService, chargePerWaiter, itemName, price, contactNo,userName,owner, email,uri;

    public CateringInfo(String cateringName, String areaOfService, String chargePerWaiter, String itemName, String price, String contactNo, String userName, String owner, String email) {
        this.cateringName = cateringName;
        this.areaOfService = areaOfService;
        this.chargePerWaiter = chargePerWaiter;
        this.itemName = itemName;
        this.price = price;
        this.contactNo = contactNo;
        this.userName = userName;
        this.owner = owner;
        this.email = email;
        this.uri = "https://firebasestorage.googleapis.com/v0/b/shohojbibaho-c63e6.appspot.com/o/Catering%20Info%2Ffood.jpg?alt=media&token=f371afc8-32ab-4e8d-9c50-7001ba6c883d";
    }

    public CateringInfo() {
    }

    public String getCateringName() {
        return cateringName;
    }

    public void setCateringName(String cateringName) {
        this.cateringName = cateringName;
    }

    public String getAreaOfService() {
        return areaOfService;
    }

    public void setAreaOfService(String areaOfService) {
        this.areaOfService = areaOfService;
    }

    public String getChargePerWaiter() {
        return chargePerWaiter;
    }

    public void setChargePerWaiter(String chargePerWaiter) {
        this.chargePerWaiter = chargePerWaiter;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
}
