package model.user;

import controller.ItemController;
import model.item.Comment;
import model.item.Item;
import model.item.Score;
import model.user.request.CommentRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends User {
    public Buyer(String email, String number, String password) {
        super(email, number, password, UserType.BUYER);
        this.cart = new HashMap<>();
        this.purchaseHistoryArrayList = new ArrayList<>();
        this.itemController = new ItemController();
        this.discountCodes = new ArrayList<>();
        this.accountCredit = 0;
    }
    Admin admin = Admin.getAdmin();
    private HashMap<Item,Integer> cart;
    private ArrayList<PurchaseInvoice> purchaseHistoryArrayList;
    private double accountCredit;
    private ArrayList<Discount> discountCodes;

    public ArrayList<Discount> getDiscountCodes() {
        return discountCodes;
    }
    public void addDiscountCode(Discount discount) {
        this.discountCodes.add(discount);
    }

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
    private ItemController itemController;
    public ItemController getFilterController() {
        return itemController;
    }

    public ArrayList<PurchaseInvoice> getPurchaseHistoryArrayList() {
        return purchaseHistoryArrayList;
    }

    public void postAComment(String idItem, String commentText){
        for (Item item : admin.getItemArrayList()){
            if(item.getIdItem().equals(idItem)){
                for (PurchaseInvoice purchaseInvoice : this.purchaseHistoryArrayList){
                    for (Item item1 : purchaseInvoice.getItemArrayList().keySet()){
                        if(item1.getIdItem().equals(idItem)){
                            Comment comment = new Comment(this, item.getIdItem(), commentText, true);
                            item.getCommentArrayList().add(comment);
                            admin.getRequestArrayList().add(new CommentRequest(comment));
                            return;
                        }
                    }
                }
                Comment comment = new Comment(this, item.getIdItem(), commentText, false);
                item.getCommentArrayList().add(comment);
                admin.getRequestArrayList().add(new CommentRequest(comment));
                return;
            }
        }
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
                "\nEmail : " + email +
                "\nPassword : " + password +
                "\nNumber : " + number +
                "\nAccount credit : " + accountCredit +
                "\nUser type : " + userType);
    }
}
