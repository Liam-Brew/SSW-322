package components.standard;

import interfaces.CPU;

public class StandardCPU implements CPU {

    // Data fields.
    private String name = "Standard CPU";
    private int price = 500;

    // Methods.
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}