package model.for_item.child_item_vehicles;

import model.for_item.Comment;

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
        stringBuilder.append("name : ").append(super.getName())
                .append("\nprice : ").append(super.getPrice())
                .append("\navailable number : ").append(super.getAvailableNumber())
                .append("\ncategory : ").append(super.getCategory())
                .append("\ncompany name : ").append(super.getCompanyName())
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
