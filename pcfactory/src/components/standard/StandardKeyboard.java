package components.standard;

import interfaces.Keyboard;

public class StandardKeyboard implements Keyboard {

    // Data fields.
    private String name = "Standard Keyboard";
    private int price = 200;

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