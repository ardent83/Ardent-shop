package exceptions;

public class AvailableEmailException extends InputException {
    public AvailableEmailException() {
        super("email is currently available!");
    }
}
