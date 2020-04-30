package components.advanced;

import interfaces.Monitor;

public class AdvancedMonitor implements Monitor {

    // Data fields.
    private String name = "Advanced Monitor";
    private int price = 1000;

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