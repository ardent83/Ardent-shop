package model.for_item.child_item_digital;

import model.for_item.Category;
import model.for_item.Comment;
import model.for_item.CommentStatus;

public class Pc extends DigitalItem {
    public Pc(String name, double price, int availableNumber, double weight, double volume, String modelCpu, double ramCapacity) {
        super(name, price, availableNumber, Category.DIGITAL, weight, volume);
        this.modelCpu = modelCpu;
        this.ramCapacity = ramCapacity;
    }

    private final String modelCpu;
    private final double ramCapacity;

    public String getModelCpu() {
        return modelCpu;
    }

    public double getRamCapacity() {
        return ramCapacity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n***********************************************")
                .append("\nname : ").append(super.getName())
                .append("\nScore : ").append(getAverageScore())
                .append("\nprice : ").append(super.getPrice())
                .append("\navailable number : ").append(super.getAvailableNumber())
                .append("\ncategory : ").append(super.getCategory())
                .append("\nwight : ").append(super.getWeight())
                .append("\nvolume : ").append(super.getVolume())
                .append("\nmodel CPU : ").append(modelCpu)
                .append("\nRAM capacity : ").append(ramCapacity)
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
