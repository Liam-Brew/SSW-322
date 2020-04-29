package factories;

import components.advanced.*;
import interfaces.*;

public class AdvancedComputerPartsFactory implements ComputerPartsFactory {

    // Data fields.
    private int price;

    // Constructor.
    public AdvancedComputerPartsFactory() {
        this.createMonitor();
        this.createCPU();
        this.createKeyboard();
    }

    // Methods.
    @Override
    public Monitor createMonitor() {
        Monitor monitor = new AdvancedMonitor();
        this.price += monitor.getPrice();
        return monitor;
    }

    @Override
    public CPU createCPU() {
        CPU cpu = new AdvancedCPU();
        this.price += cpu.getPrice();
        return cpu;
    }

    @Override
    public Keyboard createKeyboard() {
        Keyboard keyboard = new AdvancedKeyboard();
        this.price += keyboard.getPrice();
        return keyboard;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

}