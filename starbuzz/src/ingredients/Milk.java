package ingredients;

import abstract_classes.*;

public class Milk extends Ingredient {

    public Milk(Beverage beverage) {
        super(beverage, "Milk");
    }

    public double cost() {
        return drink.cost() + 0.3;
    }

}