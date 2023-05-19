package view;

import java.util.Scanner;

public class MainPanel {
    public MainPanel (){
        input = new Scanner(System.in);
    }

    private final Scanner input;
    public void mainPanel() {
        System.out.println("Select Number :\n1.Sign Up \n2.Log in \n3.Item page ");
        int command = input.nextInt();
        switch (command) {
            case 1:
                new SignUpPanel().signUp();
                break;
            case 2:
                new LogInPanel().logIn();
                break;
            case 3:
                new ItemPanel().itemMenu();
                break;
        }
    }

}
