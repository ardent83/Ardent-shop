package model.user.request;

import model.user.Buyer;

public class IncreaseCreditRequest extends Request{
    public IncreaseCreditRequest(Buyer buyer, double increaseAmount) {
        this.buyer = buyer;
        this.increaseAmount = increaseAmount;
    }

    private final Buyer buyer;
    private final double increaseAmount;

    public Buyer getBuyer() {
        return buyer;
    }

    public double getIncreaseAmount() {
        return increaseAmount;
    }
    @Override
    public String toString() {
        return ("\nRequest ID : " + super.idRequest + "\n" +
                buyer.toString() +
                "\nIncrease Amount : " + increaseAmount);
    }
}
