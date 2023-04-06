package model.for_item.child_item_digital;

import model.for_item.Category;

public class Usb extends StorageEquipment {
    public Usb(String name, double price, int availableNumber, double weight, double volume, double capacity, double usbVersion) {
        super(name, price, availableNumber, weight, volume, capacity);
        this.usbVersion = usbVersion;
    }

    private final double usbVersion;

    public double getUsbVersion() {
        return usbVersion;
    }
}
