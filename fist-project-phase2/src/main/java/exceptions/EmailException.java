package exceptions;

public class EmailException extends InputException{
    public EmailException() {
        super("Email format is invalid!");
    }
}
