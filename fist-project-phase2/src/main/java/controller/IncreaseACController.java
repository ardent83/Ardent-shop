package controller;

import exceptions.CardException;
import model.user.Admin;
import model.user.Buyer;
import model.user.request.IncreaseCreditRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IncreaseACController {
    private final Admin admin = Admin.getAdmin();
    public void increaseCredit(Buyer buyer, double amount, String cardNumber, String passwordCard, String CVV2) throws Exception{
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
}
