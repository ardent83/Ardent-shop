package controller;

import exceptions.InputException;
import model.user.Admin;
import model.user.Buyer;

public class LogInController {
    private final Admin admin = Admin.getAdmin();
    public Buyer logIn (String email, String password) throws Exception{
        for (Buyer buyer : admin.getBuyerArrayList()){
            if (buyer.getEmail().equals(email) && buyer.getPassword().equals(password)){
                return buyer;
            }
        }
        throw new InputException("username or password is invalid!");
    }
}
