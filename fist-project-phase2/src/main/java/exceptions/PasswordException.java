package exceptions;

public class PasswordException extends InputException {
    public PasswordException() {
        super("""
                Password format is invalid!
                Password must contain at least one digit [0-9]!
                Password must contain at least one lowercase Latin character [a-z]!
                Password must contain at least one uppercase Latin character [A-Z]!
                Password must contain at least one special character like ! @ # & ( )!
                Password must contain a length of at least 8 characters and a maximum of 20 characters!""");
    }
}
