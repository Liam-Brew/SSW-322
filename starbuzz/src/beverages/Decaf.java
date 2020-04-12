package beverages;

import abstract_classes.CoffeeBeverage;

public class Decaf extends CoffeeBeverage {

    public Decaf() {
        super("Decaf");
    }

    public double cost() {
        return 0.5;
    }

}