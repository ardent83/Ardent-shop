package exceptions;

public class CardException extends InputException {
    public CardException() {
        super("card format is invalid!");
    }
}
