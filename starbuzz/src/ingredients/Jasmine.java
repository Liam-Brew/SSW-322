package ingredients;

import interfaces.Beverage;
import interfaces.Ingredient;

public class Jasmine extends Ingredient {

    public Jasmine(Beverage beverage) {
        super(beverage, "Jasmine");
    }

    public double cost() {
        return drink.cost() + 0.3;
    }

}