package com.company.Controller;

public class birthdayDiscount extends Discounts {
    private final double birthdayDiscountAmount = 0.95;

    @Override
    public void setDiscountAmount(double discountAmount) {
        super.setDiscountAmount(birthdayDiscountAmount);
    }
    @Override
    public void reAdjustPrice(Ride ride) {
        ride.setDiscountedPrice(ride.getPrice()*getDiscountAmount());

    }
}
