package  beverages;

import abstract_classes.CoffeeBeverage;

public class HouseBlend extends CoffeeBeverage {

    public HouseBlend() {
        super("Houseblend");
    }

    public double cost() {
        return 0.8;
    }
}