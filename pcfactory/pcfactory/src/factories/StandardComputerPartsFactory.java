package factories;

import components.standard.*;
import interfaces.*;

public class StandardComputerPartsFactory implements ComputerPartsFactory {

    // Data fields.
    private int price;

    // Constructor.
    public StandardComputerPartsFactory() {
        this.createMonitor();
        this.createCPU();
        this.createKeyboard();
    }

    // Methods.
    @Override
    public Monitor createMonitor() {
        Monitor monitor = new StandardMonitor();
        this.price += monitor.getPrice();
        return monitor;
    }

    @Override
    public CPU createCPU() {
        CPU cpu = new StandardCPU();
        this.price += cpu.getPrice();
        return cpu;
    }

    @Override
    public Keyboard createKeyboard() {
        Keyboard keyboard = new StandardKeyboard();
        this.price += keyboard.getPrice();
        return keyboard;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

}