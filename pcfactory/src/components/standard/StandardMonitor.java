package components.standard;

import interfaces.Monitor;

public class StandardMonitor implements Monitor {

    // Data fields.
    private String name = "Standard Monitor";
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