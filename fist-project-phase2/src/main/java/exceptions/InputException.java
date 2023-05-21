package exceptions;

public abstract class InputException extends Exception {
    public InputException(String message) {
        super("invalid input : " + message);
    }
}
