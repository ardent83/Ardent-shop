package model.for_user;
import model.for_item.Item;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PurchaseInvoice {
    public PurchaseInvoice(double amountPaid, HashMap<Item,Integer> itemArrayList) {
        this.idPurchaseInvoice = Integer.toHexString(countForId+61153);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date tempDate = new Date();
        this.date = formatter.format(tempDate);
        this.amountPaid = amountPaid;
        this.itemArrayList = new HashMap<>(itemArrayList);
        countForId++;
    }

    private static int countForId;
    private final String idPurchaseInvoice;
    private final String date;
    private final double amountPaid;
    private final HashMap<Item,Integer> itemArrayList;

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

    public HashMap<Item,Integer> getItemArrayList() {
        return itemArrayList;
    }

    static {
        countForId = 0;
    }

}
