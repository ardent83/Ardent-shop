package exceptions;

public abstract class BuyException extends Exception {
    public BuyException(String message) {
        super("invalid buy : " + message);
    }
}
