package controller;

import exceptions.*;
import model.user.Admin;
import model.user.Buyer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditInformationController {
    public EditInformationController(Buyer buyer) {
        this.buyer = buyer;
    }

    private final Admin admin = Admin.getAdmin();
    private final Buyer buyer;
    public void editEmail(String email) throws Exception {
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
    public void editNumber(String number) throws Exception {
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
    public void editPassword(String password) throws Exception {
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
}
