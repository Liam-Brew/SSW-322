package interfaces;

public interface ComputerPartsFactory {

    /**
     * Adds the computer's monitor.
     * 
     * @return Monitor
     */
    public Monitor createMonitor();

    /**
     * Adds the computer's CPU.
     * 
     * @return CPU
     */
    public CPU createCPU();

    /**
     * Adds the computer's keyboard.
     * 
     * @return keyboard
     */
    public Keyboard createKeyboard();

    /**
     * Returns the price of the computer.
     * 
     * @return int
     */
    public int getPrice();

}