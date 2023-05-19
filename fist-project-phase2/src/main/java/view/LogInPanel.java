package view;

import controller.BuyerController;
import model.for_user.Admin;
import model.for_user.Buyer;

import java.util.Scanner;

public class LogInPanel {
    public LogInPanel() {
        input = new Scanner(System.in);
        buyerController = new BuyerController();
    }
    Scanner input;
    BuyerController buyerController;
    Admin admin = Admin.getAdmin();
    public void logIn() {
        System.out.println("Please enter your username and password.");
        String username = input.next();
        String password = input.next();
        if(username.equals("admin") && password.equals("admin")){
            new AdminPanel().adminMenu();
        } else {
            for (Buyer buyer : admin.getBuyerArrayList()){
                if(buyerController.logIn(username, password)){
                    new BuyerPanel(buyer).buyerMenu();
                    return;
                }
            }
            System.out.println("\nThe username or password is incorrect");
            System.out.println("\nSelect Number :\n1.log in \n2.back to main panel");
            int command = input.nextInt();
            switch (command) {
                case 1:
                    logIn();
                    return;
                case 2:
                    new MainPanel().mainPanel();
                    return;
                default:
                    new MainPanel().mainPanel();
                    break;
            }
        }
    }
}
