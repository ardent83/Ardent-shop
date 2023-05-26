package model.item.vehicles;

import model.item.Comment;

public class Bike extends Vehicles {
    public Bike(String name, double price, int availableNumber, String companyName, BikeType bikeType) {
        super(name, price, availableNumber, companyName);
        this.bikeType = bikeType;
    }

    BikeType bikeType;

    public BikeType getBikeType() {
        return bikeType;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n***********************************************");
        stringBuilder.append(super.toString())
                .append("\nbike type : ").append(bikeType)
                .append("\n______________________________________________________");
        for (Comment comment : super.getCommentArrayList()) {
            stringBuilder.append(comment.toString());
            stringBuilder.append("\n______________________________________________________");
        }
        stringBuilder.append("\n***********************************************");
        return stringBuilder.toString();
    }
}
