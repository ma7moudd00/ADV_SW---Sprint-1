package com.company.Controller;

public class People {
    private String username, password, name, mobileNumber, email="";
    private boolean verified = false;
    private boolean suspended = false;
    private double balance = 0.0;
    public People () {};
    public People(String username, String password, String name, String mobileNumber, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public double getBalance() {
        return balance;
    }

    public void addToBalance(double balance) {
        this.balance += balance;
    }

    public void deduceFromBalance (double balance) {
        this.balance -= balance;
    }

}
