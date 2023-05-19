package view;

import model.for_item.Item;
import model.for_user.Buyer;

public class ItemPanelBuyer extends ItemPanel{
    public ItemPanelBuyer(Buyer buyer){
        super();
        this.buyer = buyer;
    }
    private final Buyer buyer;
    @Override
    protected void selectItem() {
        System.out.println("\nEnter item ID.");
        String idItem = input.next();
        for (Item item : admin.getItemArrayList()){
            if (item.getIdItem().equals(idItem)) {
                System.out.println(item);
                System.out.println("\nSelect Number :\n1.add item to cart \n2.post comment \n3.back ");
                int command = input.nextInt();
                switch (command){
                    case 1:
                        System.out.println("\nPlease enter the number of item.");
                        int number = input.nextInt();
                        addItemToCartPanel(item, number);
                        return;
                    case 2:
                        postAComment(idItem);
                        return;
                    case 3:
                        new BuyerPanel(buyer).buyerMenu();
                        return;
                    default:
                        System.out.println("command is wrong!");
                        new BuyerPanel(buyer).buyerMenu();
                        return;
                }
            }
        }
        System.out.println("\nThere is no item with this ID!");
        new BuyerPanel(buyer).buyerMenu();
    }
    private void addItemToCartPanel(Item item, int numberItem){
        if (buyerController.addItemToCart(buyer, item, numberItem)) {
            System.out.println("\nAdding the item to the cart was successful.");
            new BuyerPanel(buyer).buyerMenu();
            return;
        }
        System.out.println("\nThe number is more than the inventory.");
        new BuyerPanel(buyer).buyerMenu();
    }

    @Override
    protected void postAComment(String idItem) {
        System.out.println("\nPlease enter your comment ");
        input.nextLine();
        String commentText = input.nextLine();
        if (buyer.postAComment(idItem, commentText)) {
            System.out.println("\nThe comment was sent successfully");
            new BuyerPanel(buyer).buyerMenu();
            return;
        }
        System.out.println("\nitem ID is wrong!");
        new BuyerPanel(buyer).buyerMenu();
    }
}
