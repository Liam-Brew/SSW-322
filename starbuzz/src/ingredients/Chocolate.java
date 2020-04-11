package ingredients;

import interfaces.Beverage;
import interfaces.Ingredient;

public class Chocolate extends Ingredient {

    public Chocolate(Beverage beverage) {
        super(beverage, "Chocolate");
    }

    public double cost() {
        return drink.cost() + 0.3;
    }

}