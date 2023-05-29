package view;

import UI.MainPanel;
import controller.AdminController;
import controller.ItemController;
import javafx.stage.Stage;
import model.item.Category;
import model.item.Item;
import model.item.SubCategory;
import model.item.stationary.PencilType;
import model.item.vehicles.BikeType;
import model.user.Admin;
import model.user.Buyer;
import model.user.request.Request;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminPanel {
    public AdminPanel(){
        input = new Scanner(System.in);
        adminController = new AdminController();
    }
    private final Scanner input;
    public final Admin admin = Admin.getAdmin();
    private final AdminController adminController;
    public void adminMenu() {
        System.out.print("\nA:\\Users\\Admin> ");
        String command = input.nextLine();
        String[] splited = command.split("\\s+");
        try {
            switch (splited[0]) {
                case "viewRequest" -> viewRequest();
                case "viewUser" -> viewUser();
                case "add" -> add(splited);
                case "remove" -> remove(splited);
                case "edit" -> edit(splited);
                case "accept" -> accept(splited);
                case "rejection" -> rejection(splited);
                case "help" -> help();
                case "back" -> new MainPanel().start(new Stage());
                case "addDiscount" -> {
                    adminController.addDiscountToItem(splited[1]);
                    adminMenu();
                }
                case "removeDiscount" -> {
                    adminController.removeDiscountOfItem(splited[1]);
                    adminMenu();
                }
                case "giveDiscount" -> {
                    adminController.GiveDiscountCode();
                    adminMenu();
                }
                case "viewItems" -> {
                    viewItems();
                    adminMenu();
                }
                default -> {
                    System.out.println("\ncommand is wrong!");
                    adminMenu();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            adminMenu();
        }
    }
    private void add(String[] splited) {
        try {
            switch (splited[1]) {
                case "PC" -> {
                    adminController.addPC(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), Double.parseDouble(splited[5]), Double.parseDouble(splited[6]), splited[7], Double.parseDouble(splited[8]));
                    System.out.println("\nAdded successfully.");
                    adminMenu();
                }
                case "SSD" -> {
                    adminController.addSSD(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), Double.parseDouble(splited[5]), Double.parseDouble(splited[6]), Double.parseDouble(splited[7]), Double.parseDouble(splited[8]), Double.parseDouble(splited[9]));
                    System.out.println("\nAdded successfully.");
                    adminMenu();
                }
                case "USB" -> {
                    adminController.addUSB(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), Double.parseDouble(splited[5]), Double.parseDouble(splited[6]), Double.parseDouble(splited[7]), Double.parseDouble(splited[8]));
                    System.out.println("\nAdded successfully.");
                    adminMenu();
                }
                case "Food" -> {
                    adminController.addFood(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], splited[6]);
                    System.out.println("\nAdded successfully.");
                    adminMenu();
                }
                case "Notebook" -> {
                    adminController.addNoteBook(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], Integer.parseInt(splited[6]), splited[7]);
                    System.out.println("\nAdded successfully.");
                    adminMenu();
                }
                case "Pen" -> {
                    adminController.addPen(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], splited[6]);
                    System.out.println("\nAdded successfully.");
                    adminMenu();
                }
                case "Pencil" -> {
                    adminController.addPencil(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], PencilType.valueOf(splited[6]));
                    System.out.println("\nAdded successfully.");
                    adminMenu();
                }
                case "Bike" -> {
                    adminController.addBike(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], BikeType.valueOf(splited[6]));
                    System.out.println("\nAdded successfully.");
                    adminMenu();
                }
                case "Car" -> {
                    adminController.addCar(splited[2], Double.parseDouble(splited[3]), Integer.parseInt(splited[4]), splited[5], Double.parseDouble(splited[6]), Boolean.parseBoolean(splited[7]));
                    System.out.println("\nAdded successfully.");
                    adminMenu();
                }
                default -> {
                    System.out.println("\ncommand is wrong!");
                    adminMenu();
                }
            }
        }
        catch (Exception e){
            System.out.println("Exception " + e.getMessage());
            adminMenu();
        }
    }

    private void remove(String[] splited) {
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
        try {
            switch (splited[1]) {
                case "name" -> {
                    adminController.editName(splited[2], splited[3]);
                    System.out.println("\nEdit was done successfully");
                    adminMenu();
                }
                case "price" -> {
                    adminController.editPrice(splited[2], Double.parseDouble(splited[3]));
                    System.out.println("\nEdit was done successfully");
                    adminMenu();
                }
                case "availableNumber" -> {
                    adminController.editAvailableNumber1(splited[2], Integer.parseInt(splited[0]));
                    System.out.println("\nEdit was done successfully");
                    adminMenu();
                }
                default -> {
                    System.out.println("\ncommand is wrong!");
                    adminMenu();
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            adminMenu();
        }
    }

    private void accept(String[] splited) {
        try {
            adminController.acceptRequest1(splited[1]);
            System.out.println("\nThe request was successfully accepted.");
            adminMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            adminMenu();
        }
    }

    private void rejection(String[] splited) {
        try {
            adminController.requestRejection1(splited[1]);
            System.out.println("\nThe request was successfully rejection.");
            adminMenu();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            adminMenu();
        }
    }

    private void viewRequest() {
        for (Request request : admin.getRequestArrayList()){
            System.out.println(request.toString());
            System.out.println("\n_______________________________________");
        }
        adminMenu();
    }

    private void viewUser() {
        for (Buyer buyer : admin.getBuyerArrayList()){
            System.out.println(buyer.toString());
            System.out.println("\n_______________________________________");
        }
        adminMenu();
    }
    private void viewItems() {
        ItemController itemController = new ItemController();
        ArrayList<Item> items = new ArrayList<>(itemController.filterCategory(Category.DIGITAL));
        items.addAll(itemController.filterSubCategory(SubCategory.PEN));
        items.addAll(itemController.filterSubCategory(SubCategory.PENCIL));
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item : items) {
            stringBuilder.append("\nName : ").append(item.getName());
            stringBuilder.append("\nItem ID : ").append(item.getIdItem());
            stringBuilder.append("\nScore : ").append(item.getAverageScore());
            if (item.getAvailableNumber() > 3) {
                stringBuilder.append("\nPrice : ").append(item.getPrice());
            } else if (item.getAvailableNumber() <= 3 && item.getAvailableNumber() > 0) {
                stringBuilder.append("\nPrice : ").append(item.getPrice());
                stringBuilder.append("\nOnly ").append(item.getAvailableNumber()).append(" left in stock!");
            } else if (item.getAvailableNumber() == 0) {
                stringBuilder.append("\nNot available!");
            }
            stringBuilder.append("\n___________________________________");
        }
        System.out.println(stringBuilder);
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
        System.out.println("addDiscount idItem");
        System.out.println("removeDiscount idItem");
        System.out.println("giveDiscount");
        System.out.println("viewItems");
        System.out.println("back");
        System.out.println("\n_________________________________________________________________________");
        adminMenu();
    }
}
