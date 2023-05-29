package controller;

import model.item.Item;
import model.user.Buyer;

public class ItemBuyerController {
    public ItemBuyerController(Buyer buyer) {
        this.buyer = buyer;
    }

    private final Buyer buyer;

    public void postAComment(String idItem, String commentText) {
        buyer.postAComment(idItem,commentText);
    }
    public void addItemToCart(Buyer buyer, Item item, int number){
        buyer.getCart().put(item,number);
    }
}
