package abstract_classes;

public abstract class CoffeeBeverage extends Beverage {

    public CoffeeBeverage(String description) {
        super(description);
    }

    public abstract double cost();

}