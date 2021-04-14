package edu.ucalgary.ensf409;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Calculator.java for ENSF409 final project W2021
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @author Faisal Hossain <a href="mailto:faisal.hossain1@ucalgary.ca">faisal.hossain1@ucalgary.ca</a>
 * @author Hesham Elkaliouby <a href="mailto:hesham.elkaliouby@ucalgary.ca">hesham.elkaliouby@ucalgary.ca</a>
 * @author Dagvadorj Altankhuyag <a href="mailto:dagvadorj.altankhuya@ucalgary.ca">dagvadorj.altankhuya@ucalgary.ca</a>
 * @version 1.6
 * @since 1.0
 * contains several methods to obtain information from the database
 * and calculate the cheapest option for the clients request.
 * these methods make use of the furniture class and extension of the Database class
 */
public class Calculator extends Database {
    /**
     * Data Members:
     * inInventory: Furniture[]             - will be filled with every piece of furniture in 
     *                                          the database that matches the type and category as 
     *                                          requested 
     * possibleCombinations: List<Furniture[]  - rows will each be a set of Furniture picked from 
     *                                          inInventory, which may or may not fill the order.
     *                                          The row that fills the order in the cheapest way 
     *                                          possible will be given to lowestPrice
     * lowestPrice: Furniture[]                - will contain the set of furniture items from the database
     *                                          that fill the order in the cheapest way.
     *  
     * pricesTotal: ArrayList<Integer>         - ArrayList of the PriceTotals for each combination found
     * 
     * sortedArray: int[]                       - Array of sorted PriceTotals from ArrayList
     * counter: int                             - Counter that is used to check every possible combination to fulfill the client's order
     */
    public Furniture[] lowestPrice;
    public Furniture[] inInventory; 
    public ArrayList<Integer> pricesTotal = new ArrayList<Integer>();
    public int[] sortedArray;
    public ArrayList<Furniture> currentFurniture = new ArrayList<Furniture>();
    public int orderQuantity;
    public int counter = 0;
    public List<Furniture[]> possibleCombinations = new ArrayList<Furniture[]>();
    public Calculator(String[] request) {
        super("jdbc:mysql://localhost/INVENTORY", "Username", "Password");
        super.initializeConnection(); 
        int quantity=0;
        try {
            quantity = Integer.parseInt(request[2]);
        }
        catch (NumberFormatException e)
        {
            // if the user asks for a non integer number of furniture pieces 
            System.out.println("Sorry, the quanity you have requested is not a real number.");
            System.exit(0);
        }
        this.orderQuantity = quantity;
        this.inInventory = super.findUsedFurniture(request[0], request[1], quantity);
    }
    /**
     * Calculates and finds all the combinations of the inventory. After doing this, it checks if it fits the client order. If it doesn't, then return false, else return true.
     * 
     * @return true if the client order has been fulfilled and false if the client order cannot be completed
     */
    public boolean priceCalculator ()
    {
        // If inventory is empty, return false
        if(inInventory.length == 0){
            return false;
        }
        for(int i = 0; i<inInventory.length;i++){
            currentFurniture.add(inInventory[i]);
            counter = i + 1;
            for(int j = i + 1; j<=inInventory.length;j++){
                if(ifFilled()){
                    possibleCombinations.add(currentFurniture.toArray(new Furniture[currentFurniture.size()]));
                }
                if(counter == inInventory.length){
                    currentFurniture = new ArrayList<Furniture>();
                    break;
                }
                if(j == inInventory.length){
                    currentFurniture = new ArrayList<Furniture>();
                    currentFurniture.add(inInventory[i]);
                    j = counter;
                    counter++;
                    continue;
                }
                currentFurniture.add(inInventory[j]);
            }
        }
        if(possibleCombinations.size() == 0){
            return false;
        }
        else{
            calculatePricesTotal();
            combinationsToLowestPrice();
        }
        return true;
    }
    /**
     * Calculates the total price of every combination found in possibleCombinations 
     * and stores that information in a ArrayList integer array pricesTotal
     * pricesTotal is then converted to a Int Array using the integertoInt helper function
     * and sorts that Array using Arrays.sort
     */
    public void calculatePricesTotal(){
        for(int i = 0;i<possibleCombinations.size();i++){
            int sum = 0;
            for(int j = 0; j<possibleCombinations.get(i).length;j++){
                sum += possibleCombinations.get(i)[j].getPrice();
            }
            pricesTotal.add(sum);
        }
        sortedArray = integerToInt();
        Arrays.sort(sortedArray);
    }
    /**
     * Helper function that converts a Arraylist Integer Array to a int Array
     * @return returns an int Array that corresponds to the ArrayList integer array
     */
    private int[] integerToInt(){
        int[] returnInt = new int[pricesTotal.size()];
        for(int i = 0; i<pricesTotal.size();i++){
            returnInt[i] = pricesTotal.get(i).intValue();
        }
        return returnInt;
    }
    /**
     * Void Function that adds the lowest price Combination from possibleCombinations to lowestprice data member in a single array
     */
    public void combinationsToLowestPrice(){
        ArrayList<Furniture> lowestprice = new ArrayList<Furniture>();
        for(int i = 0; i<pricesTotal.size(); i++){
            if(pricesTotal.get(i) == sortedArray[0]){
                for(int j = 0; j<possibleCombinations.get(i).length;j++){
                    lowestprice.add(possibleCombinations.get(i)[j]);
                }
                break;
            }
        }
        this.lowestPrice = lowestprice.toArray(new Furniture[lowestprice.size()]);
    }
    /**
     * Checks if the currentCombination data member is fulfilling the client order, else it will return false
     * @param array boolean Array to be checked
     * @return returns true if every boolean value is true, else returns false
     */
    public boolean ifFilled(){
        if(inInventory[0].category.equals("filing")){
            int bool1 = 0;
            int bool2 = 0;
            int bool3 = 0;
            for(int i = 0; i<currentFurniture.size();i++){
                if(currentFurniture.get(i).getBool(0)){
                    bool1++;
                }
                if(currentFurniture.get(i).getBool(1)){
                    bool2++;
                }
                if(currentFurniture.get(i).getBool(2)){
                    bool3++;
                }
            }
            if((bool1 >= orderQuantity) &&(bool2 >= orderQuantity)&&(bool3 >= orderQuantity)){
                return true;
            }
            else{
                return false;
            }
        }
        if(inInventory[0].category.equals("lamp")){
            int bool1 = 0;
            int bool2 = 0;
            for(int i = 0; i<currentFurniture.size();i++){
                if(currentFurniture.get(i).getBool(0)){
                    bool1++;
                }
                if(currentFurniture.get(i).getBool(1)){
                    bool2++;
                }
            }
            if((bool1 >= orderQuantity) &&(bool2 >= orderQuantity)){
                return true;
            }
            else{
                return false;
            }
        }
        if(inInventory[0].category.equals("desk")){
            int bool1 = 0;
            int bool2 = 0;
            int bool3 = 0;
            for(int i = 0; i<currentFurniture.size();i++){
                if(currentFurniture.get(i).getBool(0)){
                    bool1++;
                }
                if(currentFurniture.get(i).getBool(1)){
                    bool2++;
                }
                if(currentFurniture.get(i).getBool(2)){
                    bool3++;
                }
            }
            if((bool1 >= orderQuantity) &&(bool2 >= orderQuantity)&&(bool3 >= orderQuantity)){
                return true;
            }
            else{
                return false;
            }
        }
        if(inInventory[0].category.equals("chair")){
            int bool1 = 0;
            int bool2 = 0;
            int bool3 = 0;
            int bool4 = 0;
            for(int i = 0; i<currentFurniture.size();i++){
                if(currentFurniture.get(i).getBool(0)){
                    bool1++;
                }
                if(currentFurniture.get(i).getBool(1)){
                    bool2++;
                }
                if(currentFurniture.get(i).getBool(2)){
                    bool3++;
                }
                if(currentFurniture.get(i).getBool(3)){
                    bool4++;
                }
            }
            if((bool1 >= orderQuantity) &&(bool2 >= orderQuantity)&&(bool3 >= orderQuantity)&&(bool4 >= orderQuantity)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    
}
