import java.util.Scanner;

import factories.*;

public class Computer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nPlease enter the number corresponding to computer you'd like to build.");
            System.out.println("1: Standard Computer\n2: Advanced Computer \n3: Exit");

            String userInput = input.nextLine();

            if (userInput.equals("3"))
                break;
            else if (userInput.equals("1")) {
                StandardComputerPartsFactory spc = new StandardComputerPartsFactory();
                System.out.println("Total price for a Standard PC: " + spc.getPrice());
            } else if (userInput.equals("2")) {
                AdvancedComputerPartsFactory apc = new AdvancedComputerPartsFactory();
                System.out.println("Total price for an Advanced PC: " + apc.getPrice());
            } else
                System.out.println("Error: invalid selection. Please try again.");

        }
        input.close();
    }
}