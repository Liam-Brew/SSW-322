package abstract_classes;

public abstract class TeaBeverage extends Beverage {

    public TeaBeverage(String description) {
        super(description);
    }

    public abstract double cost();

}