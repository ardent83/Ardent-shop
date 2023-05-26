package controller;

import exceptions.*;
import model.user.Admin;
import model.user.Buyer;
import model.user.request.SignUpRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController {
    private final Pattern patternEmail = Pattern.compile("^\\w+@(gmail|yahoo)\\.com$");
    private final Pattern patternNumber = Pattern.compile("\\d{11}");
    private final Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
    private final Admin admin = Admin.getAdmin();
    public void register(String email, String number, String password) throws Exception {
        for (Buyer buyer : admin.getBuyerArrayList()){
            if (buyer.getEmail().equals(email)){
                throw new AvailableEmailException();
            }
            if (buyer.getNumber().equals(number)){
                throw new AvailableNumberException();
            }
        }

        Matcher matcherEmail = patternEmail.matcher(email);
        Matcher matcherNumber = patternNumber.matcher(number);
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
}
