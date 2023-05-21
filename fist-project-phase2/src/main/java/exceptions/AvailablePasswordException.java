package exceptions;

public class AvailablePasswordException extends InputException {
    public AvailablePasswordException() {
        super("password cannot be repeated!");
    }
}
