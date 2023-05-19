package view;

import controller.BuyerController;
import controller.FilterController;
import model.for_item.Category;
import model.for_item.Comment;
import model.for_item.Item;
import model.for_item.SubCategory;
import model.for_user.Admin;
import model.for_user.request.CommentRequest;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemPanel {
    public ItemPanel() {
        input = new Scanner(System.in);
        buyerController = new BuyerController();
        filterController = new FilterController();
    }
    protected final Scanner input;
    protected final Admin admin = Admin.getAdmin();
    protected final BuyerController buyerController;
    protected final FilterController filterController;
    public void itemMenu() {
        System.out.println("\nSelect Number :\n1.view item \n2.search item \n3.filter items \n4.back");
        int command = input.nextInt();
        switch (command) {
            case 1:
                viewItems(admin.getItemArrayList());
                break;
            case 2:
                searchItem();
                break;
            case 3:
                filterItem(admin.getItemArrayList());
                break;
            case 4:
                MainPanel mainPanel = new MainPanel();
                mainPanel.mainPanel();
                break;
            default:
                System.out.println("\ncommand is wrong!");
                itemMenu();
                break;
        }
    }
    protected void viewItems(ArrayList<Item> items) {
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
            } else if (item.getAvailableNumber() == 0){
                stringBuilder.append("\nNot available!");
            }
            stringBuilder.append("\n___________________________________");
        }
        System.out.println(stringBuilder);
        System.out.println("\nSelect Number :\n1.next page \n2.search item \n3.filter items \n4.select a item \n5.remove filter \n6.back ");
        int command = input.nextInt();
        switch (command) {
            case 1:
                break;
            case 2:
                searchItem();
                return;
            case 3:
                filterItem(items);
                return;
            case 4:
                selectItem();
                return;
            case 5:
                removeFilter();
                return;
            case 6:
                itemMenu();
                return;
            default:
                System.out.println("\ncommand is wrong!");
                itemMenu();
                break;
        }
        System.out.println("\nSelect Number :\n1.search item \n2.filter items \n3.back");
        int command1 = input.nextInt();
        switch (command1) {
            case 1:
                searchItem();
                break;
            case 2:
                filterItem(items);
                break;
            case 3:
                itemMenu();
                break;
            default:
                System.out.println("\ncommand is wrong!");
                itemMenu();
                break;
        }
    }
    protected void searchItem(){
        System.out.println("\nPlease enter the search text.");
        input.nextLine();
        String nameItem = input.nextLine();
        if (buyerController.searchItem(nameItem).size() == 0){
            System.out.println("\nThere are no items with this name!");
            itemMenu();
        } else {
            viewItems(buyerController.searchItem(nameItem));
        }
    }
    protected void filterItem(ArrayList<Item> items1) {
        ArrayList<Item> items = new ArrayList<>(items1);
        System.out.println("Select Number :\n1.General features \n2.Category");
        int command = input.nextInt();
        switch (command) {
            case 1:
                System.out.println("\nSelect Number :\n1.Available \n2.Price range \n3.Score range ");
                int command1 = input.nextInt();
                switch (command1) {
                    case 1:
                        viewItems(filterController.filterAvailableItem(items));
                        break;
                    case 2:
                        System.out.println("\nPlease enter first rang and last rang.");
                        double firstRange = input.nextDouble();
                        double lastRange = input.nextDouble();
                        if (filterController.filterPrice(firstRange, lastRange, items).size() == 0){
                            System.out.println("\nThere is no item with this feature!");
                            itemMenu();
                            return;
                        }
                        viewItems(filterController.filterPrice(firstRange, lastRange, items));
                        break;
                    case 3:
                        System.out.println("\nPlease enter first rang and last rang.");
                        double firstRange1 = input.nextDouble();
                        double lastRange1 = input.nextDouble();
                        if (filterController.filterScore(firstRange1, lastRange1, items).size() == 0){
                            System.out.println("\nThere is no item with this feature!");
                            itemMenu();
                            return;
                        }
                        viewItems(filterController.filterScore(firstRange1, lastRange1, items));
                        break;
                    default:
                        System.out.println("\ncommand is wrong!");
                        itemMenu();
                        break;
                }
            case 2:
                System.out.println("\nSelect Number:\n1.Digital \n2.Food \n3.Stationary \n4.Vehicles ");
                int command2 = input.nextInt();
                switch (command2) {
                    case 1:
                        System.out.println("\nSelect Number : \n1.SSD \n2.USB \n3.PC \n4.All");
                        int command3 = input.nextInt();
                        switch (command3) {
                            case 1:
                                if(filterController.filterSubCategory(SubCategory.SSD).size() == 0){
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterSubCategory(SubCategory.SSD));
                                break;
                            case 2:
                                if(filterController.filterSubCategory(SubCategory.USB).size() == 0){
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterSubCategory(SubCategory.USB));
                                break;
                            case 3:
                                if(filterController.filterSubCategory(SubCategory.PC).size() == 0){
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterSubCategory(SubCategory.PC));
                                break;
                            case 4:
                                if(filterController.filterCategory(Category.DIGITAL).size() == 0){
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterCategory(Category.DIGITAL));
                                break;
                            default:
                                System.out.println("\ncommand is wrong!");
                                itemMenu();
                                break;
                        }
                    case 2:
                        if (filterController.filterCategory(Category.FOOD).size() == 0){
                            System.out.println("\nThere is no item with this feature!");
                            itemMenu();
                            return;
                        }
                        viewItems(filterController.filterCategory(Category.FOOD));
                        break;
                    case 3:
                        System.out.println("\nSelect Number :\n1.Notebook \n2.Pen \n3.Pencil \n4.All");
                        int command4 = input.nextInt();
                        switch (command4) {
                            case 1:
                                if (filterController.filterSubCategory(SubCategory.NOTEBOOK).size() == 0){
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterSubCategory(SubCategory.NOTEBOOK));
                                break;
                            case 2:
                                if (filterController.filterSubCategory(SubCategory.PEN).size() == 0){
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterSubCategory(SubCategory.PEN));
                                break;
                            case 3:
                                if (filterController.filterSubCategory(SubCategory.PENCIL).size() == 0){
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterSubCategory(SubCategory.PENCIL));
                                break;
                            case 4:
                                if (filterController.filterCategory(Category.STATIONERY).size() == 0) {
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterCategory(Category.STATIONERY));
                                break;
                            default:
                                System.out.println("\ncommand is wrong!");
                                itemMenu();
                                break;
                        }
                    case 4:
                        System.out.println("\nSelect Number :\n1.Bike \n2.Car \n3.All");
                        int command5 = input.nextInt();
                        switch (command5) {
                            case 1:
                                if (filterController.filterSubCategory(SubCategory.BIKE).size() == 0){
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterSubCategory(SubCategory.BIKE));
                                break;
                            case 2:
                                if (filterController.filterSubCategory(SubCategory.CAR).size() == 0){
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterSubCategory(SubCategory.CAR));
                                break;
                            case 3:
                                if (filterController.filterCategory(Category.VEHICLES).size() == 0){
                                    System.out.println("\nThere is no item with this feature!");
                                    itemMenu();
                                    return;
                                }
                                viewItems(filterController.filterCategory(Category.VEHICLES));
                                break;
                            default:
                                System.out.println("\ncommand is wrong!");
                                itemMenu();
                                break;
                        }
                }
        }
    }
    protected void removeFilter(){
        viewItems(admin.getItemArrayList());
    }
    protected void selectItem() {
        System.out.println("\nEnter item ID.");
        String idItem = input.next();
        for (Item item : admin.getItemArrayList()){
            if (item.getIdItem().equals(idItem)) {
                System.out.println(item);
                System.out.println("\nSelect Number :\n1.post comment \n2.back ");
                int command = input.nextInt();
                switch (command){
                    case 1:
                        postAComment(idItem);
                        return;
                    case 2:
                        viewItems(admin.getItemArrayList());
                        return;
                    default:
                        System.out.println("\ncommand is wrong!");
                        itemMenu();
                        return;
                }
            }
        }
        System.out.println("\nThere is no item with this ID!");
        itemMenu();
    }
    protected void postAComment(String idItem) {
        System.out.println("\nPlease enter your comment ");
        input.nextLine();
        String commentText = input.nextLine();
        for (Item item : admin.getItemArrayList()) {
            if (item.getIdItem().equals(idItem)) {
                Comment comment = new Comment(admin, item.getIdItem(), commentText, false);
                item.getCommentArrayList().add(comment);
                admin.getRequestArrayList().add(new CommentRequest(comment));
                System.out.println("\nThe comment was sent successfully");
                itemMenu();
                return;
            }
        }
        System.out.println("\nitem ID is wrong!");
        itemMenu();
    }
}
