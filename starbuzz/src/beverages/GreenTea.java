package beverages;

import abstract_classes.TeaBeverage;

public class GreenTea extends TeaBeverage {

    public GreenTea() {
        super("Green Tea");
    }

    public double cost() {
        return 1.0;
    }

}