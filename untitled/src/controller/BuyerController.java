package controller;

import model.for_item.Item;
import model.for_user.Admin;
import model.for_user.Buyer;
import model.for_user.request.IncreaseCreditRequest;
import model.for_user.request.SignUpRequest;

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

        if(matcherEmail.find() && matcherNumber.find() && matcherPassword.find() && emailNotExist && numberNotExist){
            admin.getRequestArrayList().add(new SignUpRequest(email, number, password));
            return 0;
        } else if (!(matcherEmail.find())) {
            return 1;
        } else if (!(matcherNumber.find())) {
            return 2;
        } else if (!(matcherPassword.find())) {
            return 3;
        } else if (!(emailNotExist)) {
            return 4;
        }
        return 5;
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
    public boolean editEmail(Buyer buyer, String email){
        Pattern patternEmail = Pattern.compile("^\\w+@(gmail|yahoo)\\.com$");
        Matcher matcherEmail = patternEmail.matcher(email);
        if (matcherEmail.find()){
            buyer.setEmail(email);
            return true;
        }
        return false;
    }
    public boolean editNumber(Buyer buyer, String number){
        Pattern patternNumber = Pattern.compile("\\d{11}");
        Matcher matcherNumber = patternNumber.matcher(number);
        if (matcherNumber.find()){
            buyer.setNumber(number);
            return true;
        }
        return false;
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
            for (int i = 0; i < number; i++) {
                buyer.getCart().add(item);
            }
            return true;
        }
        return false;
    }
    public boolean finalizeCart(Buyer buyer){
        double amount = 0;
        for (Item item : buyer.getCart()){
            amount += item.getPrice();
        }
        if (amount <= buyer.getAccountCredit()){
            buyer.setAccountCredit(buyer.getAccountCredit()-amount);
            for (Item item : buyer.getCart()){
                item.setAvailableNumber(item.getAvailableNumber()-1);
                for (Buyer buyer1 : admin.getBuyerArrayList()){
                    if (!(buyer1.getId().equals(buyer.getId()))){
                        int count = 0;
                        for (Item item1 : buyer1.getCart()){
                            if (item1.getIdItem().equals(item.getIdItem())){
                                count++;
                            }
                        }
                        if (count > item.getAvailableNumber()){
                            buyer1.getCart().remove(item);
                        }
                    }
                }
            }
            buyer.getCart().removeAll(buyer.getCart());
            return true;
        }
        return false;
    }
}
