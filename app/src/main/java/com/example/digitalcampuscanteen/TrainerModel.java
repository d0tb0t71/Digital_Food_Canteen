package com.example.digitalcampuscanteen;

public class TrainerModel {

    String tID;
    String tName;
    String tAge;
    String tMobile;
    String tEmail;
    String tDes;


    public TrainerModel() {
    }

    public TrainerModel(String tID, String tName, String tAge, String tMobile, String tEmail, String tDes) {
        this.tID = tID;
        this.tName = tName;
        this.tAge = tAge;
        this.tMobile = tMobile;
        this.tEmail = tEmail;
        this.tDes = tDes;
    }

    public String gettID() {
        return tID;
    }

    public void settID(String tID) {
        this.tID = tID;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettAge() {
        return tAge;
    }

    public void settAge(String tAge) {
        this.tAge = tAge;
    }

    public String gettMobile() {
        return tMobile;
    }

    public void settMobile(String tMobile) {
        this.tMobile = tMobile;
    }

    public String gettEmail() {
        return tEmail;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public String gettDes() {
        return tDes;
    }

    public void settDes(String tDes) {
        this.tDes = tDes;
    }
}
