package ingredients;

import interfaces.Beverage;
import interfaces.Ingredient;

public class Ginger extends Ingredient {

    public Ginger(Beverage beverage) {
        super(beverage, "Ginger");
    }

    public double cost() {
        return drink.cost() + 0.6;
    }

}