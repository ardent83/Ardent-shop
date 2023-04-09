package view;

import controller.BuyerController;

import java.util.Scanner;

public class SignUpPanel {
    public SignUpPanel() {
        input = new Scanner(System.in);
        buyerController = new BuyerController();
    }

    Scanner input;
    BuyerController buyerController;

    public void signUp() {
        System.out.println("Please enter your email, number and password.\n" +
                "Number must have 11 digits!\n" +
                "Password must contain at least one digit [0-9]!\n" +
                "Password must contain at least one lowercase Latin character [a-z]!\n" +
                "Password must contain at least one uppercase Latin character [A-Z]!\n" +
                "Password must contain at least one special character like ! @ # & ( )!\n" +
                "Password must contain a length of at least 8 characters and a maximum of 20 characters!\n");
        String email = input.next();
        String number = input.next();
        String password = input.next();
        if (buyerController.register(email, number, password) == 0) {
            System.out.println("Request sent successfully!\n");
            new MainPanel().mainPanel();
        } else if (buyerController.register(email, number, password) == 1) {
            System.out.println("The email is invalid!\n");
            System.out.println("Select Number :\n1.Sign Up \n2.back to main panel");
            int command = input.nextInt();
            switch (command) {
                case 1:
                    signUp();
                    return;
                case 2:
                    new MainPanel().mainPanel();
                    return;
                default:
                    new MainPanel().mainPanel();
                    break;
            }
        } else if (buyerController.register(email, number, password) == 2) {
            System.out.println("The number is invalid!\n");
            System.out.println("Select Number :\n1.Sign Up \n2.back to Main panel");
            int command = input.nextInt();
            switch (command) {
                case 1:
                    signUp();
                    return;
                case 2:
                    new MainPanel().mainPanel();
                    return;
                default:
                    new MainPanel().mainPanel();
                    break;
            }
        } else if (buyerController.register(email, number, password) == 3) {
            System.out.println("The password is invalid!\n");
            System.out.println("Select Number :\n1.Sign Up \n2.back to main panel");
            int command = input.nextInt();
            switch (command) {
                case 1:
                    signUp();
                    return;
                case 2:
                    new MainPanel().mainPanel();
                    return;
                default:
                    new MainPanel().mainPanel();
                    break;
            }
        } else if (buyerController.register(email, number, password) == 4) {
            System.out.println("A user has registered with this email!\n");
            System.out.println("Select Number :\n1.Sign Up \n2.back to main panel");
            int command = input.nextInt();
            switch (command) {
                case 1:
                    signUp();
                    return;
                case 2:
                    new MainPanel().mainPanel();
                    return;
                default:
                    new MainPanel().mainPanel();
                    break;
            }
        } else if (buyerController.register(email, number, password) == 5) {
            System.out.println("A user has registered with this number!\n");
            System.out.println("Select Number :\n1.Sign Up \n2.back to main panel");
            int command = input.nextInt();
            switch (command) {
                case 1:
                    signUp();
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
