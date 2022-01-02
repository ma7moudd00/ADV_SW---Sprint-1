package com.company.Controller;

public abstract class Discounts {
    private double discountAmount=0;

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public abstract void reAdjustPrice(Ride ride);
}
