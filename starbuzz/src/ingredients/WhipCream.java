package ingredients;

import interfaces.Beverage;
import interfaces.Ingredient;

public class WhipCream extends Ingredient {

    public WhipCream(Beverage beverage) {
        super(beverage, "Whip Cream");
    }

    public double cost() {
        return drink.cost() + 0.3;
    }

}