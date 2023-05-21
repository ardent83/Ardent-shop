package exceptions;

public class DiscountException extends Exception {
    public DiscountException() {
        super("Discount Code is invalid");
    }
}
