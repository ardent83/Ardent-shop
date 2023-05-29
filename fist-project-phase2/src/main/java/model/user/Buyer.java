package model.user;

import model.item.Comment;
import model.item.Item;
import model.item.Score;
import model.user.request.CommentRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends User {
    public Buyer(String email, String number, String password) {
        super(email, number, password, UserType.BUYER);
        this.cart = new HashMap<>();
        this.purchaseHistoryArrayList = new ArrayList<>();
        this.discountCodes = new ArrayList<>();
        this.accountCredit = 0;
    }
    Admin admin = Admin.getAdmin();
    private final HashMap<Item,Integer> cart;
    private final ArrayList<PurchaseInvoice> purchaseHistoryArrayList;
    private double accountCredit;
    private final ArrayList<Discount> discountCodes;

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

    public ArrayList<PurchaseInvoice> getPurchaseHistoryArrayList() {
        return purchaseHistoryArrayList;
    }

    public void postAComment(String idItem, String commentText){
        for (Item item : admin.getItemArrayList()){
            if(item.getIdItem().equals(idItem)){
                for (PurchaseInvoice purchaseInvoice : this.purchaseHistoryArrayList){
                    for (Item item1 : purchaseInvoice.getItemIntegerHashMap().keySet()){
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

    public void postScore(String idItem, double score) {
        for (PurchaseInvoice purchaseInvoice : this.purchaseHistoryArrayList) {
            for (Item item : purchaseInvoice.getItemIntegerHashMap().keySet()) {
                if (item.getIdItem().equals(idItem)) {
                    new Score(this, score, item);
                    return;
                }
            }
        }
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
