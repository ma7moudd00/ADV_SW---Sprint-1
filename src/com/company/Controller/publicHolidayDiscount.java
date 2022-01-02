package com.company.Controller;

public class publicHolidayDiscount extends Discounts {
    private final double publicHoliday = 0.9;
    @Override
    public void setDiscountAmount(double discountAmount) {
        super.setDiscountAmount(publicHoliday);
    }
    @Override
    public void reAdjustPrice(Ride ride) {
        ride.setDiscountedPrice(ride.getPrice()*getDiscountAmount());

    }
}
