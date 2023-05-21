package exceptions;

public class NumberPhoneException extends InputException{
    public NumberPhoneException() {
        super("Number format is invalid!");
    }
}
