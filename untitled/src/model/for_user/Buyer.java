package model.for_user;

import controller.FilterController;
import model.for_item.Comment;
import model.for_item.Item;
import model.for_item.Score;
import model.for_user.request.CommentRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends User {
    public Buyer(String email, String number, String password) {
        super(email, number, password, UserType.BUYER);
        this.cart = new HashMap<>();
        this.purchaseHistoryArrayList = new ArrayList<>();
        this.filterController = new FilterController();
        this.accountCredit = 0;
    }
    Admin admin = Admin.getAdmin();
    private HashMap<Item,Integer> cart;
    private ArrayList<PurchaseInvoice> purchaseHistoryArrayList;
    private double accountCredit;

    public void setEmail(String email) {
        this.email = email;
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
                    HashMap<Item,Integer> itemArrayList = new HashMap<>();
                    itemArrayList.put(item,1);
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
                    for (Item item1 : purchaseInvoice.getItemArrayList().keySet()){
                        if(item1.getIdItem().equals(idItem)){
                            Comment comment = new Comment(this, item.getIdItem(), commentText, true);
                            item.getCommentArrayList().add(comment);
                            admin.getRequestArrayList().add(new CommentRequest(comment));
                            return true;
                        }
                    }
                }
                Comment comment = new Comment(this, item.getIdItem(), commentText, true);
                item.getCommentArrayList().add(comment);
                admin.getRequestArrayList().add(new CommentRequest(comment));
                return true;
            }
        }
        return false;
    }

    public boolean postScore(String idItem, double score){
        for (Item item : admin.getItemArrayList()) {
            if (item.getIdItem().equals(idItem)){
                for (PurchaseInvoice purchaseInvoice : this.purchaseHistoryArrayList){
                    for (Item item1 : purchaseInvoice.getItemArrayList().keySet()){
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

    public HashMap<Item,Integer> getCart() {
        return cart;
    }

    public double getAccountCredit() {
        return accountCredit;
    }

    @Override
    public String toString() {
        return   ("Username : " + idUser +
                "\nPassword : " + password +
                "\nEmail : " + email +
                "\nNumber : " + number +
                "\nAccount credit : " + accountCredit +
                "\nUser type : " + userType);
    }
}
