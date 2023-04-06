package model.for_item.child_item_digital;

import model.for_item.Category;

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
}
