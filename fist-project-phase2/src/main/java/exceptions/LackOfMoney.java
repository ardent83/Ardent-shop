package exceptions;

public class LackOfMoney extends BuyException{
    public LackOfMoney() {
        super("The account balance is insufficient!");
    }
}
