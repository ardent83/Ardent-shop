package model.user;
import model.item.Item;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class PurchaseInvoice {
    public PurchaseInvoice(double amountPaid, HashMap<Item,Integer> itemIntegerHashMap) {
        this.idPurchaseInvoice = Integer.toHexString(countForId+61153);
        this.date = String.valueOf(LocalDate.now());
        this.amountPaid = amountPaid;
        this.itemIntegerHashMap = new HashMap<>(itemIntegerHashMap);
        countForId++;
    }

    private static int countForId;
    private final String idPurchaseInvoice;
    private final String date;
    private final double amountPaid;
    private final HashMap<Item,Integer> itemIntegerHashMap;

    public static int getCountForId() {
        return countForId;
    }

    public String getIdPurchaseInvoice() {
        return idPurchaseInvoice;
    }

    public String getDate() {
        return date;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public HashMap<Item,Integer> getItemIntegerHashMap() {
        return itemIntegerHashMap;
    }

    static {
        countForId = 0;
    }

}
