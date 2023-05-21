package exceptions;

public class PasswordException extends InputException {
    public PasswordException() {
        super("Password format is invalid!");
    }
}
