package controller;

import model.for_item.CommentStatus;
import model.for_item.Item;
import model.for_item.child_item_digital.Pc;
import model.for_item.child_item_digital.Ssd;
import model.for_item.child_item_digital.Usb;
import model.for_item.child_item_food.Food;
import model.for_item.child_item_stationary.NoteBook;
import model.for_item.child_item_stationary.Pen;
import model.for_item.child_item_stationary.Pencil;
import model.for_item.child_item_stationary.PencilType;
import model.for_item.child_item_vehicles.Bike;
import model.for_item.child_item_vehicles.BikeType;
import model.for_item.child_item_vehicles.Car;
import model.for_user.Admin;
import model.for_user.request.CommentRequest;
import model.for_user.request.IncreaseCreditRequest;
import model.for_user.request.Request;
import model.for_user.request.SignUpRequest;

import java.util.ArrayList;

public class AdminController {
    Admin admin = Admin.getAdmin();
    public void addSSD(String name, double price, int availableNumber, double weight, double volume, double capacity, double readSpeed, double writSpeed){
        admin.getItemArrayList().add(new Ssd(name, price, availableNumber, weight, volume, capacity, readSpeed, writSpeed));
    }
    public void addUSB(String name, double price, int availableNumber, double weight, double volume, double capacity, double usbVersion){
        admin.getItemArrayList().add(new Usb(name, price, availableNumber, weight, volume, capacity, usbVersion));
    }
    public void addPC(String name, double price, int availableNumber, double weight, double volume, String modelCpu, double ramCapacity){
        admin.getItemArrayList().add(new Pc(name, price, availableNumber, weight, volume, modelCpu, ramCapacity));
    }
    public void addFood(String name, double price, int availableNumber, String productionDate, String expirationDate){
        admin.getItemArrayList().add(new Food(name, price, availableNumber, productionDate, expirationDate));
    }
    public void addNoteBook(String name, double price, int availableNumber, String producingCountry, int numberOfPage, String paperType){
        admin.getItemArrayList().add(new NoteBook(name, price, availableNumber, producingCountry, numberOfPage, paperType));
    }
    public void addPen(String name, double price, int availableNumber, String producingCountry, String color){
        admin.getItemArrayList().add(new Pen(name, price, availableNumber, producingCountry, color));
    }
    public void addPencil(String name, double price, int availableNumber, String producingCountry, PencilType pencilType){
        admin.getItemArrayList().add(new Pencil(name, price, availableNumber, producingCountry, pencilType));
    }
    public void addBike(String name, double price, int availableNumber, String companyName, BikeType bikeType){
        admin.getItemArrayList().add(new Bike(name, price, availableNumber, companyName, bikeType));
    }
    public void addCar(String name, double price, int availableNumber, String companyName, double engineVolume, boolean automatic){
        admin.getItemArrayList().add(new Car(name, price, availableNumber, companyName, engineVolume, automatic));
    }
    public boolean acceptRequest1(String idRequest){
        for (Request request : admin.getRequestArrayList()){
            if (request.getIdRequest().equals(idRequest)){
                admin.acceptRequest(request);
                return true;
            }
        }
        return false;
    }

    public boolean requestRejection1(String idRequest){
        boolean check = false;
        ArrayList<Request> requests = new ArrayList<>();
        for (Request request : admin.getRequestArrayList()) {
            if (request.getIdRequest().equals(idRequest)){
                if(request instanceof SignUpRequest){
                    requests.add(request);
                } else if (request instanceof CommentRequest) {
                    ((CommentRequest) request).getComment().setCommentStatus(CommentStatus.FAILED);
                    requests.add(request);
                } else if (request instanceof IncreaseCreditRequest){
                    requests.add(request);
                }
                check = true;
            }
        }
        admin.getRequestArrayList().removeAll(requests);
        return check;
    }
    public boolean editName(String idItem, String newName){
        for (Item item : admin.getItemArrayList()){
            if (item.getIdItem().equals(idItem)){
                admin.editNameItem(item, newName);
                return true;
            }
        }
        return false;
    }
    public boolean editPrice(String idItem, double newPrice){
        for (Item item : admin.getItemArrayList()){
            if (item.getIdItem().equals(idItem)){
                admin.editPriceItem(item, newPrice);
                return true;
            }
        }
        return false;
    }
    public boolean editAvailableNumber1(String idItem, int newAvailableNumber){
        for (Item item : admin.getItemArrayList()){
            if (item.getIdItem().equals(idItem)){
                admin.editAvailableNumber(item, newAvailableNumber);
                return true;
            }
        }
        return false;
    }
}
