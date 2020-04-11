package interfaces;

public abstract class Beverage {

    protected String description;

    public Beverage(String description) {
        this.description = description;
    }

    public String prepare() {
        return "\t" + getDescription() + "\n\t";
    }

    public String getDescription() {
        return description;
    }


    public abstract double cost();

}