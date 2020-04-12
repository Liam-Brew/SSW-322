package abstract_classes;

public abstract class Ingredient extends Beverage {

    protected Beverage drink;

    public Ingredient(Beverage beverage, String descripton) {
        super(descripton);
        this.drink = beverage;
    }

    public String prepare() {
        return this.drink.prepare() + " \tadd " + this.getDescription() + "\n\t";
    }


    public String getDescription() {
        return super.getDescription();
    }


    public abstract double cost();

}