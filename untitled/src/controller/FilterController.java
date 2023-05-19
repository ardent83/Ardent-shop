package controller;

import model.for_item.Category;
import model.for_item.Item;
import model.for_item.SubCategory;
import model.for_item.child_item_digital.Pc;
import model.for_item.child_item_digital.Ssd;
import model.for_item.child_item_digital.Usb;
import model.for_item.child_item_stationary.NoteBook;
import model.for_item.child_item_stationary.Pen;
import model.for_item.child_item_stationary.Pencil;
import model.for_item.child_item_stationary.PencilType;
import model.for_item.child_item_vehicles.Bike;
import model.for_item.child_item_vehicles.BikeType;
import model.for_item.child_item_vehicles.Car;
import model.for_user.Admin;

import java.util.ArrayList;

public class FilterController {
     private final Admin admin = Admin.getAdmin();
    public ArrayList<Item> filterCategory(Category category){
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : admin.getItemArrayList()){
            if(item.getCategory().equals(category)){
                items.add(item);
            }
        }
        return items;
    }
    public ArrayList<Item> filterSubCategory(SubCategory subCategory){
        ArrayList<Item> items = new ArrayList<>();
        if(subCategory.equals(SubCategory.USB)){
            for (Item item : filterCategory(Category.DIGITAL)){
                if(item instanceof Usb){
                    items.add(item);
                }
            }
        } else if (subCategory.equals(SubCategory.SSD)){
            for (Item item : filterCategory(Category.DIGITAL)){
                if(item instanceof Ssd){
                    items.add(item);
                }
            }
        } else if (subCategory.equals(SubCategory.PC)) {
            for (Item item : filterCategory(Category.DIGITAL)){
                if(item instanceof Pc){
                    items.add(item);
                }
            }
        } else if (subCategory.equals(SubCategory.PENCIL)){
            for (Item item : filterCategory(Category.STATIONERY)){
                if (item instanceof Pencil){
                    items.add(item);
                }
            }
        } else if (subCategory.equals(SubCategory.PEN)){
            for (Item item : filterCategory(Category.STATIONERY)){
                if (item instanceof Pen){
                    items.add(item);
                }
            }
        } else if (subCategory.equals(SubCategory.NOTEBOOK)) {
            for (Item item : filterCategory(Category.STATIONERY)){
                if (item instanceof NoteBook){
                    items.add(item);
                }
            }
        } else if(subCategory.equals(SubCategory.BIKE)){
            for (Item item : filterCategory(Category.VEHICLES)){
                if (item instanceof Bike){
                    items.add(item);
                }
            }
        } else if(subCategory.equals(SubCategory.CAR)){
            for (Item item : filterCategory(Category.VEHICLES)){
                if (item instanceof Car){
                    items.add(item);
                }
            }
        }
        return items;
    }
    public ArrayList<Item> filterProperty(SubCategory subCategory, double capacity){
        ArrayList<Item> items = new ArrayList<>();
        if (subCategory.equals(SubCategory.USB)) {
            for (Item item : filterSubCategory(SubCategory.USB)){
                if (item instanceof Usb){
                    if (((Usb) item).getCapacity() == capacity){
                        items.add(item);
                    }
                }
            }
        }
        if (subCategory.equals(SubCategory.SSD)) {
            for (Item item : filterSubCategory(SubCategory.SSD)){
                if (item instanceof Ssd){
                    if (((Ssd) item).getCapacity() == capacity){
                        items.add(item);
                    }
                }
            }
        }
        if (subCategory.equals(SubCategory.PC)){
            for (Item item : filterSubCategory(SubCategory.PC)){
                if (item instanceof Pc){
                    if (((Pc) item).getRamCapacity() == capacity){
                        items.add(item);
                    }
                }
            }
        }
        return items;
    }
    public ArrayList<Item> filterProperty(PencilType pencilType){
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : filterSubCategory(SubCategory.PENCIL)){
            if(item instanceof Pencil){
                if (((Pencil) item).getPencilType().equals(pencilType)){
                    items.add(item);
                }
            }
        }
        return items;
    }
    public ArrayList<Item> filterProperty(String color){
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : filterSubCategory(SubCategory.PEN)){
            if (item instanceof Pen){
                if (((Pen) item).getColor().equals(color)){
                    items.add(item);
                }
            }
        }
        return items;
    }
    public ArrayList<Item> filterProperty(int numberOfPage){
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : filterSubCategory(SubCategory.NOTEBOOK)){
            if(item instanceof NoteBook){
                if (((NoteBook) item).getNumberOfPage() == numberOfPage){
                    items.add(item);
                }
            }
        }
        return items;
    }
    public ArrayList<Item> filterProperty(BikeType bikeType){
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : filterSubCategory(SubCategory.BIKE)){
            if (item instanceof Bike){
                if(((Bike) item).getBikeType().equals(bikeType)){
                    items.add(item);
                }
            }
        }
        return items;
    }
    public ArrayList<Item> filterProperty(boolean isAutomatic){
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : filterSubCategory(SubCategory.CAR)){
            if(item instanceof Car){
                if (((Car) item).isAutomatic() == isAutomatic){
                    items.add(item);
                }
            }
        }
        return items;
    }
    public ArrayList<Item> filterPrice(double firstRang, double lastRang , ArrayList<Item> items){
        ArrayList<Item> itemsTemp = new ArrayList<>(items);
        itemsTemp.removeIf(item -> item.getPrice() < firstRang || item.getPrice() > lastRang);
        return itemsTemp;
    }
    public ArrayList<Item> filterScore(double firstRang, double lastRang , ArrayList<Item> items){
        ArrayList<Item> itemsTemp = new ArrayList<>(items);
        itemsTemp.removeIf(item -> item.getAverageScore() < firstRang || item.getAverageScore() > lastRang);
        return itemsTemp;
    }
    public ArrayList<Item> filterAvailableItem(ArrayList<Item> items){
        ArrayList<Item> itemsTemp = new ArrayList<>(items);
        itemsTemp.removeIf(item -> item.getAvailableNumber() == 0);
        return itemsTemp;
    }
    public ArrayList<Item> removeAllFilter(){
        return admin.getItemArrayList();
    }
}
