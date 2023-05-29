package controller;

import exceptions.DiscountException;
import exceptions.LackOfMoney;
import model.item.Item;
import model.user.Admin;
import model.user.Buyer;
import model.user.Discount;
import model.user.PurchaseInvoice;

import java.time.LocalDate;
import java.util.ArrayList;

public class CartController {
    Admin admin = Admin.getAdmin();

    public double getAmount(Buyer buyer, ArrayList<String> discountCodes) throws Exception {
        double amount = 0;
        ArrayList<Discount> discountCart = new ArrayList<>();

        boolean isDiscountCode;
        for (String discountCode : discountCodes){
            isDiscountCode = false;
            for (Discount discount : buyer.getDiscountCodes()){
                if (discount.getDiscountCode().equals(discountCode)){
                    isDiscountCode = true;
                    if (!discountCart.contains(discount)){
                        discountCart.add(discount);
                    }
                    break;
                }
            }
            if (!isDiscountCode){
                throw new DiscountException();
            }
        }

        for (Discount discount : discountCart){
            if (discount.getCapacity() == 0 || discount.getDiscountValidity().isBefore(LocalDate.now())){
                throw new RuntimeException();
            }
        }

        for (Item item : buyer.getCart().keySet()) {
            amount += (item.getPrice() * buyer.getCart().get(item));
        }

        double discountPercentCart = 0;
        for (Discount discount : discountCart) {
            discountPercentCart += discount.getDiscountPercent();
        }

        if (discountPercentCart > 100) {
            discountPercentCart = 100;
        }

        amount = (amount * ((100 - discountPercentCart) / 100));

        return amount;
    }
    public void finalizeCart(Buyer buyer, ArrayList<String> discountCodes, double amount) throws Exception {
        ArrayList<Discount> discountCart = new ArrayList<>();

        boolean isDiscountCode;
        for (String discountCode : discountCodes){
            isDiscountCode = false;
            for (Discount discount : buyer.getDiscountCodes()){
                if (discount.getDiscountCode().equals(discountCode)){
                    isDiscountCode = true;
                    discountCart.add(discount);
                    break;
                }
            }
            if (!isDiscountCode){
                throw new DiscountException();
            }
        }
        if (amount <= buyer.getAccountCredit()) {
            for (Discount discount : discountCart){
                discount.setCapacity(discount.getCapacity() - 1);
                if (discount.getCapacity() == 0){
                    buyer.getDiscountCodes().remove(discount);
                }
            }
            for (Item item : buyer.getCart().keySet()) {
                item.setAvailableNumber(item.getAvailableNumber() - buyer.getCart().get(item));
//              بروزرسانی سبد خرید دیگران
                updateCart(buyer, item);
            }
            buyer.getPurchaseHistoryArrayList().add(new PurchaseInvoice(amount, buyer.getCart()));
            buyer.getCart().clear();
        } else {
            throw new LackOfMoney();
        }
    }

    private void updateCart(Buyer buyer, Item item){
        for (Buyer buyer1 : admin.getBuyerArrayList()) {
            if (!(buyer1.getId().equals(buyer.getId()))) {
                if (buyer1.getCart().containsKey(item)) {
                    if (buyer1.getCart().get(item) > item.getAvailableNumber()) {
                        buyer1.getCart().replace(item, item.getAvailableNumber());
                    }
                }
            }
        }
    }
}
