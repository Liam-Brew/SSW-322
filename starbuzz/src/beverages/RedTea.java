package beverages;

import abstract_classes.TeaBeverage;

public class RedTea extends TeaBeverage {

    public RedTea() {
        super("Red Tea");
    }

    public double cost() {
        return 0.8;
    }

}