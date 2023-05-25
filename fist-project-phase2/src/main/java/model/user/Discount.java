package model.user;

import java.time.LocalDate;

public class Discount {
    public Discount(double discountPercent, LocalDate discountValidity, int capacity) {
        this.discountPercent = discountPercent;
        this.discountValidity = discountValidity;
        this.capacity = capacity;
        this.discountCode = Integer.toHexString((int) (50000000+(Math.random()*50000000)));
    }

    private double discountPercent;
    private LocalDate discountValidity;
    private int capacity;
    private final String discountCode;

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public LocalDate getDiscountValidity() {
        return discountValidity;
    }

    public void setDiscountValidity(LocalDate discountValidity) {
        this.discountValidity = discountValidity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDiscountCode() {
        return discountCode;
    }
}
