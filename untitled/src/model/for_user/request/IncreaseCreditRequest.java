package model.for_user.request;

import model.for_user.Buyer;

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
}
