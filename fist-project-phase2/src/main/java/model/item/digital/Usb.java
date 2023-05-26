package model.item.digital;

import model.item.Comment;
import model.item.CommentStatus;

public class Usb extends StorageEquipment {
    public Usb(String name, double price, int availableNumber, double weight, double volume, double capacity, double usbVersion) {
        super(name, price, availableNumber, weight, volume, capacity);
        this.usbVersion = usbVersion;
    }

    private final double usbVersion;

    public double getUsbVersion() {
        return usbVersion;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n***********************************************");
        stringBuilder.append(super.toString())
                .append("\nUSB version : ").append(usbVersion)
                .append("\n______________________________________________________");
        for (Comment comment : super.getCommentArrayList()) {
            if (comment.getCommentStatus().equals(CommentStatus.ACCEPTED)){
                stringBuilder.append(comment);
                stringBuilder.append("\n______________________________________________________");
            }
        }
        stringBuilder.append("\n***********************************************");
        return stringBuilder.toString();
    }
}
