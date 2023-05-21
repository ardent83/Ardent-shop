package exceptions;

public class ItemOutOfStockException extends BuyException {
    public ItemOutOfStockException() {
        super("item out of stock!");
    }
}
