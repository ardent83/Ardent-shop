package model.for_user;

import controller.FilterController;
import model.for_item.Comment;
import model.for_item.Item;
import model.for_item.Score;
import model.for_user.request.CommentRequest;

import java.util.ArrayList;

public class Buyer extends User {
    public Buyer(String email, String number, String password) {
        super(email, number, password, UserType.BUYER);
        this.cart = new ArrayList<>();
        this.purchaseHistoryArrayList = new ArrayList<>();
        this.filterController = new FilterController();
        this.accountCredit = 0;
    }
    Admin admin = Admin.getAdmin();
    private ArrayList<Item> cart;
    private ArrayList<PurchaseInvoice> purchaseHistoryArrayList;
    private double accountCredit;

    public void setEmail(String email) {
        super.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Item> getItemArrayList(){
        return admin.getItemArrayList();
    }
    private FilterController filterController;
    public FilterController getFilterController() {
        return filterController;
    }

    public ArrayList<Item> searchItem(String nameItem){
        ArrayList<Item> searchResults = new ArrayList<>();
        for (Item item : admin.getItemArrayList()){
            if ((item.getName()).equalsIgnoreCase((nameItem))){
                searchResults.add(item);
            }
        }
        return searchResults;
    }

    public int buyItem(String idItem){
        for (Item item : admin.getItemArrayList()){
            if(item.getIdItem().equals(idItem)) {
                if ((item.getPrice() < accountCredit) && (item.getAvailableNumber() > 0)){
                    item.setAvailableNumber(item.getAvailableNumber() - 1);
                    ArrayList<Item> itemArrayList = new ArrayList<>();
                    itemArrayList.add(item);
                    purchaseHistoryArrayList.add(new PurchaseInvoice(item.getPrice(),itemArrayList));
                    accountCredit = (accountCredit - item.getPrice());
                    return 0;
                } else if (!(item.getPrice() < accountCredit)){
                    return 1;
                } else {
                    return 2;
                }
            }
        }
        return 3;
    }

    public ArrayList<PurchaseInvoice> getPurchaseHistoryArrayList() {
        return purchaseHistoryArrayList;
    }

    public boolean postAComment(String idItem, String commentText){
        for (Item item : admin.getItemArrayList()){
            if(item.getIdItem().equals(idItem)){
                for (PurchaseInvoice purchaseInvoice : this.purchaseHistoryArrayList){
                    for (Item item1 : purchaseInvoice.getItemArrayList()){
                        if(item1.getIdItem().equals(idItem)){
                            admin.getRequestArrayList().add(new CommentRequest(new Comment(this, item.getIdItem(), commentText, true)));
                            return true;
                        }
                    }
                }
                admin.getRequestArrayList().add(new CommentRequest(new Comment(this, item.getIdItem(), commentText, false)));
                return true;
            }
        }
        return false;
    }

    public boolean postScore(String idItem, double score){
        for (Item item : admin.getItemArrayList()) {
            if (item.getIdItem().equals(idItem)){
                for (PurchaseInvoice purchaseInvoice : this.purchaseHistoryArrayList){
                    for (Item item1 : purchaseInvoice.getItemArrayList()){
                        if(item1.getIdItem().equals(item.getIdItem())){
                            new Score(this,score,item);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void setAccountCredit(double accountCredit) {
        this.accountCredit = accountCredit;
    }

    public ArrayList<Item> getCart() {
        return cart;
    }

    public double getAccountCredit() {
        return accountCredit;
    }
}
