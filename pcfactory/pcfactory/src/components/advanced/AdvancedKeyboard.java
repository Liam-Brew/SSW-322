package components.advanced;

import interfaces.Keyboard;

public class AdvancedKeyboard implements Keyboard {

    // Data fields.
    private String name = "Advanced Keyboard";
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