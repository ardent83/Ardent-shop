package view;

import controller.AdminController;
import model.for_item.Item;
import model.for_item.child_item_stationary.PencilType;
import model.for_item.child_item_vehicles.BikeType;
import model.for_user.Admin;
import model.for_user.Buyer;
import model.for_user.request.Request;

import java.util.Scanner;

public class AdminPanel {
    public AdminPanel(){
        input = new Scanner(System.in);
        adminController = new AdminController();
    }
    private final Scanner input;
    public final Admin admin = Admin.getAdmin();
    private final AdminController adminController;
    public void adminMenu(){
        System.out.println("\nA:\\Users\\Admin>");
        String command = input.nextLine();
        String[] splited = command.split("\\s+");
        switch (splited[0]) {
            case "back":
                new MainPanel().mainPanel();
                break;
            case "viewRequest":
                viewRequest();
                break;
            case "viewUser":
                viewUser();
                break;
            case "add":
                add(splited);
                break;
            case "remove":
                remove(splited);
                break;
            case "edit" :
                edit(splited);
                break;
            case "accept" :
                accept(splited);
                break;
            case "rejection" :
                rejection(splited);
                break;
            case "help":
                help();
                break;
            default:
                System.out.println("\ncommand is wrong!");
                adminMenu();
                break;
        }
    }
    private void add(String[] splited){
        switch (splited[1]) {
            case "PC" :
                adminController.addPC(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), Double.parseDouble(splited[5]), Double.parseDouble(splited[6]), splited[7], Double.parseDouble(splited[8]));
                System.out.println("\nAdded successfully.");
                adminMenu();
                break;
            case "SSD":
                adminController.addSSD(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), Double.parseDouble(splited[5]), Double.parseDouble(splited[6]), Double.parseDouble(splited[7]), Double.parseDouble(splited[8]), Double.parseDouble(splited[9]));
                System.out.println("\nAdded successfully.");
                adminMenu();
                break;
            case "USB":
                adminController.addUSB(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), Double.parseDouble(splited[5]), Double.parseDouble(splited[6]), Double.parseDouble(splited[7]), Double.parseDouble(splited[8]));
                System.out.println("\nAdded successfully.");
                adminMenu();
                break;
            case "Food":
                adminController.addFood(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], splited[6]);
                System.out.println("\nAdded successfully.");
                adminMenu();
                break;
            case "Notebook":
                adminController.addNoteBook(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], Integer.parseInt(splited[6]), splited[7]);
                System.out.println("\nAdded successfully.");
                adminMenu();
                break;
            case "Pen":
                adminController.addPen(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], splited[6]);
                System.out.println("\nAdded successfully.");
                adminMenu();
                break;
            case "Pencil":
                adminController.addPencil(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], PencilType.valueOf(splited[6]));
                System.out.println("\nAdded successfully.");
                adminMenu();
                break;
            case "Bike":
                adminController.addBike(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], BikeType.valueOf(splited[6]));
                System.out.println("\nAdded successfully.");
                adminMenu();
                break;
            case "Car" :
                adminController.addCar(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], Double.parseDouble(splited[6]), Boolean.parseBoolean(splited[7]));
                System.out.println("\nAdded successfully.");
                adminMenu();
                break;
            default:
                System.out.println("\ncommand is wrong!");
                adminMenu();
                break;
        }
    }

    private void remove(String[] splited){
        for (Item item : admin.getItemArrayList()){
            if (item.getIdItem().equals(splited[1])){
                admin.getItemArrayList().remove(item);
                System.out.println("\nRemoved successfully.");
                adminMenu();
                return;
            }
        }
        System.out.println("\nInvalid ID!");
        adminMenu();
    }

    private void edit(String[] splited) {
        switch (splited[1]) {
            case "name":
                if(adminController.editName(splited[2], splited[3])){
                    System.out.println("\nEdit was done successfully");
                    adminMenu();
                    return;
                }
                System.out.println("\nInvalid ID!");
                adminMenu();
            case "price":
                if (adminController.editPrice(splited[2], Double.parseDouble(splited[3]))){
                    System.out.println("\nEdit was done successfully");
                    adminMenu();
                    return;
                }
                System.out.println("\nInvalid ID!");
                adminMenu();
            case "availableNumber":
                if (adminController.editAvailableNumber1(splited[2], Integer.parseInt(splited[0]))){
                    System.out.println("\nEdit was done successfully");
                    adminMenu();
                    return;
                }
                System.out.println("\nInvalid ID!");
                adminMenu();
        }
    }

    private void accept(String[] splited){
        if (adminController.acceptRequest1(splited[1])){
            System.out.println("\nThe request was successfully accepted.");
            adminMenu();
            return;
        }
        System.out.println("\nInvalid ID!");
        adminMenu();
    }

    private void rejection(String[] splited){
        if (adminController.requestRejection1(splited[1])){
            System.out.println("\nThe request was successfully rejection.");
            adminMenu();
            return;
        }
        System.out.println("\nInvalid ID!");
        adminMenu();
    }

    private void viewRequest(){
        for (Request request : admin.getRequestArrayList()){
            System.out.println(request.toString());
            System.out.println("\n_______________________________________");
        }
        adminMenu();
    }

    private void viewUser(){
        for (Buyer buyer : admin.getBuyerArrayList()){
            System.out.println(buyer.toString());
            System.out.println("\n_______________________________________");
        }
        adminMenu();
    }
    private void  help() {
        System.out.println("viewRequest");
        System.out.println("viewUser");
        System.out.println("add PC name price availableNUmber weight volume modelCPU ramCapacity");
        System.out.println("add SSD name price availableNUmber weight volume capacity readSpeed writSpeed");
        System.out.println("add USB name price availableNUmber weight volume capacity usbVersion");
        System.out.println("add Food name price availableNUmber productionDate expirationDate");
        System.out.println("add Notebook name price availableNUmber productionCountry numberOfPage paperType");
        System.out.println("add Pen name price availableNUmber productionCountry color");
        System.out.println("add Pencil name price availableNUmber productionCountry pencilType(H2, H, F, B, HB)");
        System.out.println("add Bike name price availableNUmber companyName bikeType(MOUNTAIN, ROAD, CITY, HYBRID)");
        System.out.println("add Car name price availableNUmber companyName engineVolume automatic(true, false)");
        System.out.println("remove itemID");
        System.out.println("edit name itemID newName");
        System.out.println("edit price itemID newPrice");
        System.out.println("edit availableNumber itemID newAvailableNumber");
        System.out.println("accept idRequest");
        System.out.println("rejection idRequest");
        System.out.println("back");
        System.out.println("\n_________________________________________________________________________");
        adminMenu();
    }
}
