package main;

import java.util.Scanner;

import abstract_classes.*;
import beverages.*;
import ingredients.*;

public class Starbuzz {

    // Create the drink
    private static Beverage mixDrink(String[] order) {
        Beverage beverage;
        switch (order[0].toLowerCase()) {
            // what type of drink is being created?
            case "tea":
                if (order.length > 1) {
                    // All milk - based teas
                    beverage = addToTea(order, new Milk(new RedTea()));
                    break;
                } else {
                    System.out.println("Error: beverage doesn't exist");
                    System.exit(1);
                }
            case "espresso":
                beverage = addToCoffee(order, new Espresso(), 1);
                break;
            case "decaf":
                if (order.length >= 2
                        && !(order[1].equals("milk") || order[1].equals("chocolate") || order[1].equals("whipcream"))) {
                    beverage = addToCoffee(order, decafBeverage(order), 2);
                } else {
                    beverage = addToCoffee(order, new Decaf(), 1);
                }
                break;
            case "houseblend":
                beverage = addToCoffee(order, new HouseBlend(), 1);
                break;
            case "mocha":
                beverage = addToCoffee(order, new Chocolate(new Espresso()), 1);
                break;
            case "latte":
                beverage = addToCoffee(order, new Milk(new Espresso()), 1);
                break;
            case "cappucino":
                beverage = addToCoffee(order, new WhipCream(new Espresso()), 1);
                break;
            default:
                beverage = teaBeverage(order);
        }
        return beverage;
    }

    private static Beverage teaBeverage(String[] order) {
        Beverage beverage;

        if (order.length < 2) {
            System.out.println("Error: improper recipe");
            System.exit(1);
        }

        switch (order[0].toLowerCase()) {
            case "red":
                beverage = addToTea(order, new RedTea());
                break;
            case "green":
                beverage = addToTea(order, new GreenTea());
                break;
            case "white":
                beverage = addToTea(order, new WhiteTea());
                break;
            // Derivatives
            case "flower":
                beverage = addToTea(order, new Jasmine(new GreenTea()));
                break;
            case "ginger":
                beverage = addToTea(order, new Ginger(new GreenTea()));
                break;
            default:
                beverage = null;
                System.out.println("Error: improper recipe");
                System.exit(1);
        }
        return beverage;
    }

    private static Beverage decafBeverage(String[] order) {
        Beverage drink;
        switch (order[1].toLowerCase()) {
            case "mocha":
                drink = new Chocolate(new Decaf());
                break;
            case "cappucino":
                drink = new WhipCream(new Decaf());
                break;
            case "latte":
                drink = new Milk(new Decaf());
                break;
            default:
                drink = null;
                System.out.println("Error: improper recipe");
                System.exit(1);
        }
        return drink;
    }

    private static Beverage addToCoffee(String[] order, Beverage drink, int ingred) {
        for (int not_used = 0; ingred < order.length; ingred++) {
            switch (order[ingred].toLowerCase()) {
                case "chocolate":
                    drink = new Chocolate(drink);
                    break;
                case "milk":
                    drink = new Milk(drink);
                    break;
                case "whipcream":
                    drink = new WhipCream(drink);
                    break;
                default:
                    System.out.println("Invalid coffee ingredient: '" + order[ingred] + "'");
            }
        }
        return drink;
    }

    private static Beverage addToTea(String[] order, Beverage drink) {
        // Not tea
        if (!order[1].equals("tea")) {
            System.out.println("Can't add tea ingredient to coffee drink.");
            System.exit(1);
        }

        // Iterates over customer order to add ingredients (past tea name) to tea
        for (int ingred = 2; ingred < order.length; ingred++) {
            switch (order[ingred].toLowerCase()) {
                case "ginger":
                    drink = new Ginger(drink);
                    break;
                case "jasmine":
                    drink = new Jasmine(drink);
                    break;
                case "milk":
                    drink = new Milk(drink);
                    break;
                default:
                    System.out.println("Error: tea ingredient doesnt not exist'" + order[ingred] + "'");
            }
        }
        return drink;
    }

    public static void main(String args[]) {
        String usage = "\nUsage: <beverage name> [<ingredient 1, ingredient 2, ingredient 3>]";
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter you drink recipe in the following format (enter nothing to exit):" + usage);

            String orderStr = input.nextLine().toLowerCase();

            if (orderStr.equals(""))
                break;

            String[] order = orderStr.split(" ");

            if (order.length < 1) {
                System.err.println(usage);
                System.exit(1);
            }

            Beverage drink = mixDrink(order);

            System.out.printf("\nThe total cost of your order is: $%.2f\n", drink.cost());
            System.out.printf("The beverage is prepared as follows: \n\n %s\n", drink.prepare());

        }

        input.close();

    }

}