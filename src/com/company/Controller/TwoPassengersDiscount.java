package com.company.Controller;

public class TwoPassengersDiscount extends Discounts {
    private final double TwoPassengersDiscountAmount = 0.95;
    @Override
    public void setDiscountAmount(double discountAmount) {
        super.setDiscountAmount(TwoPassengersDiscountAmount);
    }
    @Override
    public void reAdjustPrice(Ride ride) {
        ride.setDiscountedPrice(ride.getPrice()*getDiscountAmount());
    }
}
