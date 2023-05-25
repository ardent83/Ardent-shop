package controller;

import exceptions.*;
import model.item.Item;
import model.user.Admin;
import model.user.Buyer;
import model.user.Discount;
import model.user.PurchaseInvoice;
import model.user.request.IncreaseCreditRequest;
import model.user.request.SignUpRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyerController {
    Admin admin = Admin.getAdmin();
    public void register(String email, String number, String password) throws Exception {
        for (Buyer buyer : admin.getBuyerArrayList()){
            if (buyer.getEmail().equals(email)){
                throw new AvailableEmailException();
            }
            if (buyer.getNumber().equals(number)){
                throw new AvailableNumberException();
            }
        }

        Pattern patternEmail = Pattern.compile("^\\w+@(gmail|yahoo)\\.com$");
        Matcher matcherEmail = patternEmail.matcher(email);

        Pattern patternNumber = Pattern.compile("\\d{11}");
        Matcher matcherNumber = patternNumber.matcher(number);

        Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        Matcher matcherPassword = patternPassword.matcher(password);

        if (!(matcherEmail.find())) {
            throw new EmailException();
        } else if (!(matcherNumber.find())) {
            throw new NumberPhoneException();
        } else if (!(matcherPassword.find())) {
             throw new PasswordException();
        } else {
            admin.getRequestArrayList().add(new SignUpRequest(email, number, password));
        }
    }

    public void logIn (String idBuyer, String password) throws Exception{
        for (Buyer buyer : admin.getBuyerArrayList()){
            if (buyer.getId().equals(idBuyer) && buyer.getPassword().equals(password)){
                return;
            }
        }
        throw new InputException("username or password is invalid!");
    }
    public Buyer getBuyer(String idBuyer){
        for (Buyer buyer : admin.getBuyerArrayList()){
            if(buyer.getId().equals(idBuyer)){
                return buyer;
            }
        }
        return new Buyer("null","null","null");
    }
    public void editEmail(Buyer buyer, String email) throws Exception {
        for (Buyer buyer1 : admin.getBuyerArrayList()){
            if (buyer1.getEmail().equals(email)){
                throw new AvailableEmailException();
            }
        }

        Pattern patternEmail = Pattern.compile("^\\w+@(gmail|yahoo)\\.com$");
        Matcher matcherEmail = patternEmail.matcher(email);

        if (matcherEmail.find()){
            buyer.setEmail(email);
            return;
        }
        throw new EmailException();
    }
    public void editNumber(Buyer buyer, String number) throws Exception {
        for (Buyer buyer1 : admin.getBuyerArrayList()){
            if (buyer1.getNumber().equals(number)){
                throw new AvailableNumberException();
            }
        }

        Pattern patternNumber = Pattern.compile("\\d{11}");
        Matcher matcherNumber = patternNumber.matcher(number);

        if (matcherNumber.find()){
            buyer.setNumber(number);
            return;
        }
        throw new NumberPhoneException();
    }
    public void editPassword(Buyer buyer, String password) throws Exception {
        if (buyer.getPassword().equals(password)){
            throw new AvailablePasswordException();
        }

        Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        Matcher matcherPassword = patternPassword.matcher(password);

        if (matcherPassword.find()){
            buyer.setPassword(password);
            return;
        }
        throw  new PasswordException();
    }
    public void increaseCredit(Buyer buyer, double amount,String cardNumber, String passwordCard, String CVV2) throws Exception{
        Pattern patternNumber = Pattern.compile("\\d{16}");
        Matcher matcherNumber = patternNumber.matcher(cardNumber);

        Pattern patternPassword = Pattern.compile("\\d{4}");
        Matcher matcherPassword = patternPassword.matcher(passwordCard);

        Pattern patternCVV2 = Pattern.compile("\\d{4}");
        Matcher matcherCVV2 = patternCVV2.matcher(CVV2);

        if (matcherNumber.find() && matcherPassword.find() && matcherCVV2.find()){
            admin.getRequestArrayList().add(new IncreaseCreditRequest(buyer, amount));
            return;
        }
        throw new CardException();
    }
    public ArrayList<Item> searchItem(String nameItem){
        ArrayList<Item> searchResults = new ArrayList<>();
        for (Item item : admin.getItemArrayList()){
            if ((item.getName()).toUpperCase().contains((nameItem.toUpperCase()))){
                searchResults.add(item);
            }
        }
        return searchResults;
    }
    public void addItemToCart(Buyer buyer, Item item, int number) throws Exception{
        if (item.getAvailableNumber() >= number){
            buyer.getCart().put(item,number);
            return;
        }
        throw new ItemOutOfStockException();
    }
    public void finalizeCart(Buyer buyer, ArrayList<String> discountCodes) throws Exception {
        double amount = 0;
        ArrayList<Discount> discountCart = new ArrayList<>();

        boolean isDiscountCode;
        for (String discountCode : discountCodes){
            isDiscountCode = false;
            for (Discount discount : buyer.getDiscountCodes()){
                if (discount.getDiscountCode().equals(discountCode)){
                    isDiscountCode = true;
                    discountCart.add(discount);
                    break;
                }
            }
            if (!isDiscountCode){
                throw new DiscountException();
            }
        }

        for (Discount discount : discountCart){
            if (discount.getCapacity() == 0 || discount.getDiscountValidity().isBefore(LocalDate.now())){
                throw new RuntimeException();
            }
        }

        for (Item item : buyer.getCart().keySet()) {
            amount += (item.getPrice() * buyer.getCart().get(item));
        }

        double discountPercentCart = 0;
        for (Discount discount : discountCart){
            discountPercentCart += discount.getDiscountPercent();
        }

        if (discountPercentCart > 90){
            discountPercentCart = 90;
        }

        amount = (amount * ((100 - discountPercentCart) / 100));
        if (amount <= buyer.getAccountCredit()) {
            for (Discount discount : discountCart){
                discount.setCapacity(discount.getCapacity() - 1);
            }
            for (Item item : buyer.getCart().keySet()) {
                item.setAvailableNumber(item.getAvailableNumber() - buyer.getCart().get(item));
//              بروزرسانی سبد خرید دیگران
                for (Buyer buyer1 : admin.getBuyerArrayList()) {
                    if (!(buyer1.getId().equals(buyer.getId()))) {
                        if (buyer1.getCart().containsKey(item)) {
                            if (buyer1.getCart().get(item) > item.getAvailableNumber()) {
                                buyer1.getCart().replace(item, item.getAvailableNumber());
                            }
                        }
                    }
                }
            }
            buyer.getPurchaseHistoryArrayList().add(new PurchaseInvoice(amount, buyer.getCart()));
            buyer.getCart().clear();
        } else {
            throw new LackOfMoney();
        }
    }
}
