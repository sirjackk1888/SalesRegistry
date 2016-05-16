    
package Salesregistry;

import java.util.*;

public class Application {
    
    String tempString;
    ArrayList<ArrayList<String>> salesRegistryArray = new ArrayList<>(); 
    String name,
            numberSoldString,
            costPerItemString,
            bulkQuantityString,
            totalSalesString,
            bulkDiscountPercentageString,
            totalDiscountString = null;
        Float bulkQuantityFloat = 0.00f,
            numberSoldFloat = 0.00f,
            costPerItemFloat = 0.00f,
            totalDiscountFloat = 0.00f,
            bulkDiscountPercentageFloat = 0.00f,
            tempTotalSales,
            tempCostPerItem = 0.00f;
        
    public void registerSale() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the sales registry");
        
        do {
            System.out.println("Please enter add (to add a sale) or quit");
            
            do {
                try {
                    tempString = (keyboard.nextLine().toLowerCase()); //gathers user input

                    if(tempString.equals("quit")) {
                        System.exit(0);
                    }
                    if((tempString.equals("add") == false) 
                        && (tempString.equals("quit") == false)){
                        throw new IllegalArgumentException(); //illegal user input will trigger an error
                   }
                } catch(IllegalArgumentException e) {
                    System.out.println("Please enter add or quit only.");
                    //error displays message
                }
            }while(!tempString.equals("add"));
                
            if(!salesRegistryArray.isEmpty()) {
                    addToSales();
                } else {
                    addSale();
                }
        } while(tempString.equals("add"));
    }
        
    public void calculateSales() {
        try {
            bulkQuantityFloat = Float.parseFloat(bulkQuantityString);
            costPerItemFloat= Float.parseFloat(costPerItemString);
            tempCostPerItem = Float.parseFloat(costPerItemString);
            bulkDiscountPercentageFloat= Float.parseFloat(bulkDiscountPercentageString);

        }catch(NumberFormatException e) {}

        if(numberSoldFloat >= bulkQuantityFloat) {
            costPerItemFloat = (costPerItemFloat * (bulkDiscountPercentageFloat));
            totalDiscountFloat = (tempCostPerItem - costPerItemFloat);

        } else {
            System.out.println("This quantity does not qaulify for bulk discounts");
            if(totalDiscountFloat == null) {
                totalDiscountFloat = 0f;
            }
        }

        tempTotalSales = numberSoldFloat * costPerItemFloat;
        totalSalesString = tempTotalSales.toString();
        numberSoldString = numberSoldFloat.toString();
        costPerItemString = costPerItemFloat.toString();
        bulkQuantityString = bulkQuantityFloat.toString();
        bulkDiscountPercentageString = bulkDiscountPercentageFloat.toString();
        totalDiscountString = Double.toString(totalDiscountFloat);

        salesRegistryArray.add(new ArrayList<>(Arrays.asList(
                                name, totalSalesString, numberSoldString, 
                                costPerItemString, totalDiscountString, bulkQuantityString, 
                                bulkDiscountPercentageString)));
        }
    
    public void addSale() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the product name");
        name = keyboard.next();
        System.out.println("please enter the number sold this sale");
        numberSoldString = keyboard.next();
        System.out.println("Please enter the cost per unit");
        costPerItemString = keyboard.next();
        System.out.println("Bulk quantity for this item is: ?");
        bulkQuantityString = keyboard.next();
        System.out.println("Bulk percentage for this item is: ?" +
                " please enter percentage as a decimal of 100" +
                " I.E. 40% is .40");
        bulkDiscountPercentageString = keyboard.next();
        numberSoldFloat = Float.parseFloat(numberSoldString);
        calculateSales();
    }
        
    public void addToSales() {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> productName = new ArrayList<>();
        //creates a temporary array to establish which array 
        //within the registry will be modified
        
        System.out.println("Please enter product you wish to add to or type add for a new product sale");
        System.out.println("current product sales registered are:");
        Collections.sort(salesRegistryArray, (ArrayList<String> o1, 
                ArrayList<String> o2) -> o1.get(0).compareTo(o2.get(0)));
        //sorts the first row of the animal registry array by natural order
                
        for (ArrayList<String> productNameDisplay : salesRegistryArray) {
            if (productNameDisplay.size() > salesRegistryArray.size()-1) {
                    System.out.println(productNameDisplay.get(0) + " ");
            }
        }
        //this loop displays the product names in the registry
        //(first element of each embedded array list)
        
        String userSelection = keyboard.next();
        if(userSelection.equals("add")) {
            addSale();
        } else {
            int index = 0;
            for (int i = 0; i < salesRegistryArray.size(); i++) {
                productName.add(salesRegistryArray.get(index).get(0));
                index = index + 1;
            }
            // this loop adds the first element of each embedded arraylist 
            //to a temporary array for binary searching
            int tempSale = Collections.binarySearch(productName, userSelection);
            System.out.println();
            System.out.println("Please enter number sold");
            tempTotalSales = Float.parseFloat(salesRegistryArray.get(tempSale).get(2));
            System.out.println(tempTotalSales + " checkpoint reached");
        }
    } 
}
