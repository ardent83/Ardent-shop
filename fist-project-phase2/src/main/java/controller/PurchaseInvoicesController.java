package controller;

import model.user.Buyer;

public class PurchaseInvoicesController {
    public PurchaseInvoicesController(Buyer buyer) {
        this.buyer = buyer;
    }

    private final Buyer buyer;
    public void giveScore(String idItem,double score){
        buyer.postScore(idItem, score);
    }
}
