package exceptions;

public class AvailableNumberException extends InputException {
    public AvailableNumberException() {
        super("number is currently available!");
    }
}
