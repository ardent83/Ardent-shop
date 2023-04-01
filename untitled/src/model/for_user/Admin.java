package model.for_user;

import model.for_item.CommentStatus;
import model.for_item.Item;
import model.for_user.request.CommentRequest;
import model.for_user.request.IncreaseCreditRequest;
import model.for_user.request.Request;
import model.for_user.request.SignUpRequest;

import java.util.ArrayList;

public class Admin extends User {
    private Admin(String email, String number, String password) {
        super(email, number, password, UserType.ADMIN);
        this.itemArrayList = new ArrayList<>();
        this.buyerArrayList = new ArrayList<>();
        this.requestArrayList = new ArrayList<>();
    }
    private static Admin admin;
    public static Admin getAdmin(){
        if(admin == null){
            admin = new Admin("email", "number", "admin");
        }
        return admin;
    }
    ArrayList<Item> itemArrayList;
    ArrayList<Buyer> buyerArrayList;
    ArrayList<Request> requestArrayList;

    public ArrayList<Request> getRequestArrayList() {
        return requestArrayList;
    }

    public void acceptRequest(Request request){
        if(request instanceof SignUpRequest){
            Buyer buyer = new Buyer(((SignUpRequest) request).getEmail(),((SignUpRequest) request).getNumber(),((SignUpRequest) request).getPassword());
            buyerArrayList.add(buyer);
        } else if (request instanceof CommentRequest) {
            ((CommentRequest) request).getComment().setCommentStatus(CommentStatus.ACCEPTED);
        } else if (request instanceof IncreaseCreditRequest){
            ((IncreaseCreditRequest) request).getBuyer().setAccountCredit(((IncreaseCreditRequest) request).getBuyer().getAccountCredit() + ((IncreaseCreditRequest) request).getIncreaseAmount());
        }
    }
    public void addItem(Item item){
        itemArrayList.add(item);
    }
    public void removeItem(Item item){
        itemArrayList.remove(item);
    }
    public void editNameItem(Item item,String name){
        item.setName(name);
    }
    public void editPriceItem(Item item, double price){
        item.setPrice(price);
    }
    public void editAvailableNumber(Item item, int availableNumber){
        item.setAvailableNumber(availableNumber);
    }

    public ArrayList<Buyer> getBuyerArrayList() {
        return buyerArrayList;
    }
}
