package view;

import controller.BuyerController;
import model.for_item.Item;
import model.for_user.Buyer;
import model.for_user.PurchaseInvoice;

import java.util.*;

public class BuyerPanel {
    public BuyerPanel(Buyer buyer) {
        input = new Scanner(System.in);
        this.buyer = buyer;
        buyerController = new BuyerController();
    }
    Scanner input;
    Buyer buyer;
    BuyerController buyerController;
    public void buyerMenu() {
        System.out.println("\nSelect Number :\n1.View user information and edit it \n2.View item \n3.view cart(buy) \n4.buy \n5.view purchase history \n6.back");
        int command = input.nextInt();
        switch (command){
            case 1:
                viewInfoUser();
                break;
            case 2:
                new ItemPanelBuyer(buyer).itemMenu();
                break;
            case 3:
                viewCart();
                break;
            case 4:
                buy();
                break;
            case 5:
                viewPurchaseHistory();
                break;
            case 6:
                new MainPanel().mainPanel();
                break;

        }
    }
    private void viewInfoUser(){
        System.out.println(buyer.toString());
        System.out.println("\nSelect Number :\n1.Edit email \n2.edit number \n3.Edit password \n4.Back to the buyer menu");
        int command = input.nextInt();
        while (true) {
            switch (command) {
                case 1:
                    System.out.println("Please enter your new email.\n");
                    String email = input.next();
                    if (buyerController.editEmail(buyer, email) == 0) {
                        System.out.println("\nEdit was done successfully.\n");
                        buyerMenu();
                        return;
                    } else if (buyerController.editEmail(buyer, email) == 1){
                        System.out.println("\nThe email is invalid!\n");
                    } else {
                        System.out.println("\nA user has registered with this email!\n");
                    }
                    break;
                case 2:
                    System.out.println("Please enter your new number.\n");
                    String number = input.next();
                    if(buyerController.editNumber(buyer, number) == 0){
                        System.out.println("\nEdit was done successfully.\n");
                        buyerMenu();
                        return;
                    } else if (buyerController.editNumber(buyer, number) == 1){
                        System.out.println("\nThe number is invalid!\n");
                    } else {
                        System.out.println("\nA user has registered with this number!\n");
                    }
                    break;
                case 3:
                    System.out.println("Please enter your current password.\n");
                    String currentPassword = input.next();
                    if(currentPassword.equals(buyer.getPassword())){
                        System.out.println("\nplease enter your new Password.\n" +
                                "Password must contain at least one digit [0-9]!\n" +
                                "Password must contain at least one lowercase Latin character [a-z]!\n" +
                                "Password must contain at least one uppercase Latin character [A-Z]!\n" +
                                "Password must contain at least one special character like ! @ # & ( )!\n" +
                                "Password must contain a length of at least 8 characters and a maximum of 20 characters!\n");
                        String newPassword = input.next();
                        if (buyerController.editPassword(buyer, newPassword)){
                            System.out.println("\nPassword changed successfully.");
                            buyerMenu();
                            return;
                        } else {
                            System.out.println("The password is invalid!\n");
                        }
                        break;
                    }
                case 4:
                    buyerMenu();
                    return;
                default:
                    viewInfoUser();
                    return;
            }
        }
    }
    private void viewCart() {
        for (Item item : buyer.getCart().keySet()){
            System.out.println("\nname : " + item.getName() +
                    "\nitem ID : " + item.getIdItem() +
                    "\nprice : " + item.getIdItem() +
                    "\nquantity in cart : " + buyer.getCart().get(item) +
                    "\n_____________________________________________________");
        }
        System.out.println("\nSelect Number :\n1.remove item \n2.back");
        int command = input.nextInt();
        switch (command) {
            case 1:
                removeItem();
                return;
            case 2:
                buyerMenu();
                break;
            default:
                System.out.println("\ncommand is wrong!");
                buyerMenu();
                break;
        }
    }
    private void buy() {
        if (buyerController.finalizeCart(buyer)){
            System.out.println("\nThe purchase was made successfully.");
            buyerMenu();
        } else {
            System.out.println("\nAccount credit is insufficient");
            System.out.println("\nSelect Number \n1.Increase account credit \n2.back");
            int command = input.nextInt();
            switch (command) {
                case 1:
                    increaseAccountCredit();
                    return;
                case 2:
                    buyerMenu();
                    return;
                default:
                    System.out.println("\ncommand is wrong!");
                    buyerMenu();
                    break;
            }
        }
    }
    private void increaseAccountCredit(){
        System.out.println("\nPlease enter the payment amount, card number(16 digit), card password(4 digit) and card CVV2 (4 digit).");
        double amount = input.nextInt();
        String cardNumber = input.next();
        String passwordCard = input.next();
        String CVV2 = input.next();
        if (buyerController.increaseCredit(buyer, amount, cardNumber, passwordCard, CVV2)){
            System.out.println("\nAccount credit request has been successfully sent.");
            buyerMenu();
        } else {
            System.out.println("\nThe card information is invalid!");
            System.out.println("\nSelect Number :\n1.Increase account credit \n2.go to buyer menu");
            int command = input.nextInt();
            switch (command) {
                case 1:
                    increaseAccountCredit();
                    return;
                case 2:
                    buyerMenu();
                    return;
                default:
                    System.out.println("\ncommand is wrong!");
                    buyerMenu();
                    break;
            }
        }
    }
    private void viewPurchaseHistory() {
        for (PurchaseInvoice purchaseInvoice : buyer.getPurchaseHistoryArrayList()){
            for (Item item : purchaseInvoice.getItemArrayList().keySet()){
                System.out.println("\nname : " + item.getName() +
                        "\nitem ID : " + item.getIdItem() +
                        "\nprice : " + item.getPrice() +
                        "\nquantity purchased : " + buyer.getCart().get(item) +
                        "\n_____________________________________________________");
            }
        }
        System.out.println("\nSelect Number \n1.select a item and giving a score \n2.back");
        int command = input.nextInt();
        switch (command) {
            case 1:
                giveScore();
                return;
            case 2:
                buyerMenu();
                break;
        }
    }
    private void giveScore(){
        System.out.println("\nPlease enter item ID and the score.");
        String idItem = input.next();
        double score = input.nextDouble();
        if (buyer.postScore(idItem, score)){
            System.out.println("\nScore submitted successfully.");
            buyerMenu();
            return;
        }
        System.out.println("\nInvalid ID");
        buyerMenu();
    }

    private void removeItem() {
        System.out.println("\nEnter item ID and number.");
        String itemId = input.next();
        double number = input.nextDouble();
        Iterator<Map.Entry<Item, Integer>> iterator = buyer.getCart().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Item, Integer> entry = iterator.next();
            if (entry.getKey().getIdItem().equals(itemId)) {
                buyer.getCart().replace(entry.getKey(),(int) (entry.getValue()-number));
                break;
            }
        }
        System.out.println("\nRemoved successfully.");
        viewCart();
    }
}
