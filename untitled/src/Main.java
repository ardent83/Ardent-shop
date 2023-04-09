import view.MainPanel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exit = true;
        do {
            MainPanel mainPanel = new MainPanel();
            mainPanel.mainPanel();
            System.out.println("\nSelect Exit(true, false) ");
            exit = Boolean.parseBoolean(input.next());
        } while (exit);
    }
}