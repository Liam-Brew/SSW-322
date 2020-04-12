package ingredients;

import abstract_classes.*;

public class Jasmine extends Ingredient {

    public Jasmine(Beverage beverage) {
        super(beverage, "Jasmine");
    }

    public double cost() {
        return drink.cost() + 0.3;
    }

}