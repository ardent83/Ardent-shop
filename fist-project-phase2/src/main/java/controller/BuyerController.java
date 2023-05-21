package controller;

import exceptions.DiscountException;
import exceptions.LackOfMoney;
import model.for_item.Item;
import model.for_user.Admin;
import model.for_user.Buyer;
import model.for_user.Discount;
import model.for_user.PurchaseInvoice;
import model.for_user.request.IncreaseCreditRequest;
import model.for_user.request.SignUpRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyerController {
    Admin admin = Admin.getAdmin();
    public int register(String email, String number, String password){
        Pattern patternEmail = Pattern.compile("^\\w+@(gmail|yahoo)\\.com$");
        Matcher matcherEmail = patternEmail.matcher(email);

        Pattern patternNumber = Pattern.compile("\\d{11}");
        Matcher matcherNumber = patternNumber.matcher(number);

        Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        Matcher matcherPassword = patternPassword.matcher(password);

        boolean emailNotExist = true;
        boolean numberNotExist = true;
        for (Buyer buyer : admin.getBuyerArrayList()){
            if (buyer.getEmail().equals(email)){
                emailNotExist = false;
            }
            if (buyer.getNumber().equals(number)){
                numberNotExist = false;
            }
        }


        if (!(matcherEmail.find())) {
            return 1;
        } else if (!(matcherNumber.find())) {
            return 2;
        } else if (!(matcherPassword.find())) {
            return 3;
        } else if (!(emailNotExist)) {
            return 4;
        } else if (!(numberNotExist)){
            return 5;
        } else {
            admin.getRequestArrayList().add(new SignUpRequest(email, number, password));
            return 0;
        }
    }

    public boolean logIn (String idBuyer, String password){
        for (Buyer buyer : admin.getBuyerArrayList()){
            if (buyer.getId().equals(idBuyer) && buyer.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public Buyer getBuyer(String idBuyer){
        for (Buyer buyer : admin.getBuyerArrayList()){
            if(buyer.getId().equals(idBuyer)){
                return buyer;
            }
        }
        return new Buyer("null","null","null");
    }
    public int editEmail(Buyer buyer, String email){
        Pattern patternEmail = Pattern.compile("^\\w+@(gmail|yahoo)\\.com$");
        Matcher matcherEmail = patternEmail.matcher(email);

        boolean emailNotExist = true;
        for (Buyer buyer1 : admin.getBuyerArrayList()){
            if (buyer1.getEmail().equals(email)){
                emailNotExist = false;
            }
        }

        if (matcherEmail.find() && emailNotExist){
            buyer.setEmail(email);
            return 0;
        }else if (!(matcherEmail.find())){
            return 1;
        }
        return 2;
    }
    public int editNumber(Buyer buyer, String number){
        Pattern patternNumber = Pattern.compile("\\d{11}");
        Matcher matcherNumber = patternNumber.matcher(number);

        boolean numberNotExist = true;
        for (Buyer buyer1 : admin.getBuyerArrayList()){
            if (buyer1.getNumber().equals(number)){
                numberNotExist = false;
            }
        }

        if (matcherNumber.find() && numberNotExist){
            buyer.setNumber(number);
            return 0;
        } else if (!(matcherNumber.find())) {
            return 1;
        }
        return 2;
    }
    public boolean editPassword(Buyer buyer, String password){
        Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        Matcher matcherPassword = patternPassword.matcher(password);
        if (matcherPassword.find()){
            buyer.setPassword(password);
            return true;
        }
        return false;
    }
    public boolean increaseCredit(Buyer buyer, double amount,String cardNumber, String passwordCard, String CVV2){
        Pattern patternNumber = Pattern.compile("\\d{16}");
        Matcher matcherNumber = patternNumber.matcher(cardNumber);

        Pattern patternPassword = Pattern.compile("\\d{4}");
        Matcher matcherPassword = patternPassword.matcher(passwordCard);

        Pattern patternCVV2 = Pattern.compile("\\d{4}");
        Matcher matcherCVV2 = patternCVV2.matcher(CVV2);

        if (matcherNumber.find() && matcherPassword.find() && matcherCVV2.find()){
            admin.getRequestArrayList().add(new IncreaseCreditRequest(buyer, amount));
            return true;
        }
        return false;
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
    public boolean addItemToCart(Buyer buyer, Item item, int number){
        if (item.getAvailableNumber() >= number){
            buyer.getCart().put(item,number);
            return true;
        }
        return false;
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
            discount.setCapacity(discount.getCapacity() - 1);
        }

        if (discountPercentCart > 90){
            discountPercentCart = 90;
        }

        amount = (amount * ((100 - discountPercentCart) / 100));
        if (amount <= buyer.getAccountCredit()) {
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
    public boolean finalizeCart(Buyer buyer){
        double amount = 0;
        for (Item item : buyer.getCart().keySet()){
            amount += (item.getPrice() * buyer.getCart().get(item));
        }
        if (amount <= buyer.getAccountCredit()){
            for (Item item : buyer.getCart().keySet()){
                item.setAvailableNumber(item.getAvailableNumber()-buyer.getCart().get(item));
//              بروزرسانی سبد خرید دیگران
                for (Buyer buyer1 : admin.getBuyerArrayList()){
                    if (!(buyer1.getId().equals(buyer.getId()))){
                        if (buyer1.getCart().containsKey(item)){
                            if (buyer1.getCart().get(item) > item.getAvailableNumber()){
                                buyer1.getCart().replace(item,item.getAvailableNumber());
                            }
                        }
                    }
                }
            }
            buyer.getPurchaseHistoryArrayList().add(new PurchaseInvoice(amount, buyer.getCart()));
            buyer.getCart().clear();
            return true;
        }
        return false;
    }
}
