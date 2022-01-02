package com.company.Controller;

public class firstRideDiscount extends Discounts {
    private final double firstRideDiscountAmount = 0.9;

    @Override
    public void setDiscountAmount(double discountAmount) {
        super.setDiscountAmount(firstRideDiscountAmount);
    }

    @Override
    public void reAdjustPrice(Ride ride) {
        ride.setDiscountedPrice(ride.getPrice()*getDiscountAmount());
    }
}
