package beverages;

import abstract_classes.TeaBeverage;

public class WhiteTea extends TeaBeverage {

    public WhiteTea() {
        super("White Tea");
    }

    public double cost() {
        return 1.0;
    }

}