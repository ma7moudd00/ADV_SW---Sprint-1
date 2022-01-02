package com.company.Controller;

public class PlacesDiscounts extends Discounts {
    private final double placesDiscountAmount = 0.9;
    @Override
    public void setDiscountAmount(double discountAmount) {
        super.setDiscountAmount(placesDiscountAmount);
    }

    @Override
    public void reAdjustPrice(Ride ride) {
        ride.setDiscountedPrice(ride.getPrice()*getDiscountAmount());
    }
}
