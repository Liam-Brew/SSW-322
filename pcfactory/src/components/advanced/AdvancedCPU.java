package components.advanced;

import interfaces.CPU;

public class AdvancedCPU implements CPU {

    // Data fields.
    private String name = "Advanced CPU";
    private int price = 800;

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