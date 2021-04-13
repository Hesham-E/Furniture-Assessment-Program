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
 * @version 1.4
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
     * fillOrder: Furniture[][]               - contains the order that was placed by the user, 
     *                                          the length of which is the quantity requested
     * possibleCombinations: List<Furniture[]>  - rows will each be a set of Furniture picked from 
     *                                          inInventory, which may or may not fill the order.
     *                                          The row that fills the order in the cheapest way 
     *                                          possible will be given to lowestPrice
     * lowestPrice: Furniture[]             - will contain the set of furniture items from the database
     *                                          that fill the order in the cheapest way.
     *  
     * pricesTotal                             - ArrayList of the PriceTotals for each combination found
     * 
     * sortedArray                             - Array of sorted PriceTotals from ArrayList
     */
    public Furniture[] lowestPrice;
    public Furniture[] inInventory; 
    public Furniture[][] fillOrder;
    public ArrayList<String> idsInFillOrder = new ArrayList<String>();
    public ArrayList<Integer> pricesTotal = new ArrayList<Integer>();
    public int[] sortedArray;
    public int insertedItems = 0;
    public List<Furniture[]> possibleCombinations = new ArrayList<Furniture[]>();
    public Calculator(String[] request) {
        super("jdbc:mysql://localhost/INVENTORY", "root", "1234");
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
        this.inInventory = super.findUsedFurniture(request[0], request[1], quantity);
        Arrays.sort(this.inInventory);
        this.fillOrder = new Furniture[quantity][3];
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
        // If there only one item in the inventory, then check if that item fits the client order requirements
        if(inInventory.length == 1){
            if((inInventory[0].isFilled()) && (fillOrder.length == 1)){
                Furniture dummy = new Furniture(new String(inInventory[0].getType()), new String(inInventory[0].category));
                fillOrder[0][0] = inInventory[0];
                fillOrder[0][1] = dummy;
                fillOrder[0][2] = dummy;
                fillOrder[0][3] = dummy;
                return true;
            }
            else{
                return false;
            }
        }
        // find all possible combinations of the order using a brute force algorithm
        if(inInventory[0].category.equals("lamp")){
            Furniture dummy = new Furniture(new String(inInventory[0].getType()), new String(inInventory[0].category));
            for(int i = 0; i<inInventory.length;i++){
                boolean[] boolArray = {inInventory[i].getBool(0), inInventory[i].getBool(1)};
                if(ifFilled(boolArray)){
                    addToCombinations(inInventory[i], dummy, dummy, dummy);
                }
                for(int j = i+1; j<inInventory.length; j++){
                    boolean[] boolArray1 = {inInventory[i].getBool(0), inInventory[i].getBool(1)};
                    boolean[] boolArray2 = {inInventory[j].getBool(0), inInventory[j].getBool(1)};
                    boolean[] combinedBool = combineArray(boolArray1, boolArray2);
                    if(ifFilled(combinedBool)){
                        addToCombinations(inInventory[i], inInventory[j], dummy, dummy);
                    }
                }
            }
            // if there are no possible combinations, return false
            if(possibleCombinations.size() == 0){
                return false;
            }
            // if the possible combinations found is less than the client order length, return false
            else if(possibleCombinations.size() < fillOrder.length){
                return false;
            }
            else{
                calculatePricesTotal();
                for(int i = 0; i<sortedArray.length;i++){
                    if(insertedItems == fillOrder.length){
                        break;
                    }
                    insertItems(sortedArray[i], i);
                }
                if(insertedItems < fillOrder.length){
                    return false;
                }
                combinationsToLowestPrice();
                return true;
            }
        }
        // Brute force algorithm for chair
        else if(inInventory[0].category.equals("chair")){
            Furniture dummy = new Furniture(new String(inInventory[0].getType()), new String(inInventory[0].category));
            for(int i = 0; i<inInventory.length;i++){
                boolean[] boolArray = {inInventory[i].getBool(0), inInventory[i].getBool(1),inInventory[i].getBool(2),inInventory[i].getBool(3)};
                if(ifFilled(boolArray)){
                    addToCombinations(inInventory[i], dummy, dummy, dummy);
                }
                for(int j = i+1; j<inInventory.length; j++){
                    boolean[] boolArray1 = {inInventory[i].getBool(0), inInventory[i].getBool(1),inInventory[i].getBool(2),inInventory[i].getBool(3)};
                    boolean[] boolArray2 = {inInventory[j].getBool(0), inInventory[j].getBool(1),inInventory[j].getBool(2),inInventory[j].getBool(3)};
                    boolean[] combinedBool = combineArray(boolArray1, boolArray2);
                    if(ifFilled(combinedBool)){
                        addToCombinations(inInventory[i], inInventory[j], dummy, dummy);
                    }
                    for(int k = j+1; k<inInventory.length;k++){
                        boolean[] boolArray3 = {inInventory[i].getBool(0), inInventory[i].getBool(1),inInventory[i].getBool(2),inInventory[i].getBool(3)};
                        boolean[] boolArray4 = {inInventory[j].getBool(0), inInventory[j].getBool(1),inInventory[j].getBool(2),inInventory[j].getBool(3)};
                        boolean[] boolArray5 = {inInventory[k].getBool(0), inInventory[k].getBool(1),inInventory[k].getBool(2),inInventory[k].getBool(3)};
                        boolean[] combinedArray2 = combineArray(boolArray3, boolArray4, boolArray5);
                        if(ifFilled(combinedArray2)){
                            addToCombinations(inInventory[i], inInventory[j], inInventory[k], dummy);
                        }
                        for(int l = k+1; l<inInventory.length;l++){
                            boolean[] boolArray6 = {inInventory[i].getBool(0), inInventory[i].getBool(1),inInventory[i].getBool(2),inInventory[i].getBool(3)};
                            boolean[] boolArray7 = {inInventory[j].getBool(0), inInventory[j].getBool(1),inInventory[j].getBool(2),inInventory[j].getBool(3)};
                            boolean[] boolArray8 = {inInventory[k].getBool(0), inInventory[k].getBool(1),inInventory[k].getBool(2),inInventory[k].getBool(3)};
                            boolean[] boolArray9 = {inInventory[l].getBool(0), inInventory[l].getBool(1),inInventory[l].getBool(2),inInventory[l].getBool(3)};
                            boolean[] combinedArray3 = combineArray(boolArray6, boolArray7, boolArray8, boolArray9);
                            if(ifFilled(combinedArray3)){
                                addToCombinations(inInventory[i], inInventory[j], inInventory[k], inInventory[l]);
                            }
                        }
                    }

                }
            }
            // if there are no possible combinations, return false
            if(possibleCombinations.size() == 0){
                return false;
            }
            // if the possible combinations found is less than the client order length, return false
            else if(possibleCombinations.size() < fillOrder.length){
                return false;
            }
            else{
                calculatePricesTotal();
                for(int i = 0; i<sortedArray.length;i++){
                    if(insertedItems == fillOrder.length){
                        break;
                    }
                    insertItems(sortedArray[i], i);
                }
                if(insertedItems < fillOrder.length){
                    return false;
                }
                combinationsToLowestPrice();
                return true;
            }
        }
        // Brute force algorithm for filing and desk
        else{
            Furniture dummy = new Furniture(new String(inInventory[0].getType()), new String(inInventory[0].category));
            for(int i = 0; i<inInventory.length;i++){
                boolean[] boolArray = {inInventory[i].getBool(0), inInventory[i].getBool(1),inInventory[i].getBool(2)};
                if(ifFilled(boolArray)){
                    addToCombinations(inInventory[i], dummy, dummy, dummy);
                }
                for(int j = i+1; j<inInventory.length; j++){
                    boolean[] boolArray1 = {inInventory[i].getBool(0), inInventory[i].getBool(1),inInventory[i].getBool(2)};
                    boolean[] boolArray2 = {inInventory[j].getBool(0), inInventory[j].getBool(1),inInventory[j].getBool(2)};
                    boolean[] combinedBool = combineArray(boolArray1, boolArray2);
                    if(ifFilled(combinedBool)){
                        addToCombinations(inInventory[i], inInventory[j], dummy, dummy);
                    }
                    for(int k = j+1; k<inInventory.length;k++){
                        boolean[] boolArray3 = {inInventory[i].getBool(0), inInventory[i].getBool(1),inInventory[i].getBool(2)};
                        boolean[] boolArray4 = {inInventory[j].getBool(0), inInventory[j].getBool(1),inInventory[j].getBool(2)};
                        boolean[] boolArray5 = {inInventory[k].getBool(0), inInventory[k].getBool(1),inInventory[k].getBool(2)};
                        boolean[] combinedArray2 = combineArray(boolArray3, boolArray4, boolArray5);
                        if(ifFilled(combinedArray2)){
                            addToCombinations(inInventory[i], inInventory[j], inInventory[k], dummy);
                        }
                    }

                }
            }
            // if there are no possible combinations, return false
            if(possibleCombinations.size() == 0){
                return false;
            }
            // if the possible combinations found is less than the client order length, return false
            else if(possibleCombinations.size() < fillOrder.length){
                return false;
            }
            else{
                calculatePricesTotal();
                for(int i = 0; i<sortedArray.length;i++){
                    if(insertedItems == fillOrder.length){
                        break;
                    }
                    insertItems(sortedArray[i], i);
                }
                if(insertedItems < fillOrder.length){
                    return false;
                }
                combinationsToLowestPrice();
                return true;
            }

        }
    }
    /**
     * Combines two boolean arrays into one boolean array by ORing the two arrays.
     * @param array1 - boolean array that consists of the first item
     * @param array2 - boolean array that consists of the Second item
     * @return boolean array that is the result of oring the arrays
     */
    public boolean[] combineArray(boolean[] array1, boolean[]array2){
        boolean[] newBool = new boolean[array1.length];
        for(int i = 0; i<array1.length;i++){
            if(array1[i] || array2[i]){
                newBool[i] = true;
            }
            else{
                newBool[i] = false;
            }
        }
        return newBool;
    }
    /**
     * Dulipicate combineArray Function with three arguments
     * @param array1  boolean array that consists of the first item
     * @param array2  boolean array that consists of the Second item
     * @param array3  boolean array that consists of the Third item
     * @return boolean array that is the result of oring the arrays
     */
    public boolean[] combineArray(boolean[] array1, boolean[]array2, boolean[]array3 ){
        boolean[] newBool = new boolean[array1.length];
        for(int i = 0; i<array1.length;i++){
            if(array1[i] || array2[i] || array3[i]){
                newBool[i] = true;
            }
            else{
                newBool[i] = false;
            }
        }
        return newBool;
    }
    /**
     * Dulipicate combineArray Function with four arguments
     * @param array1 boolean array that consists of the first item
     * @param array2 boolean array that consists of the Second item
     * @param array3 boolean array that consists of the Third item
     * @param array4 boolean array that consists of the Fourth item
     * @return boolean array that is the result of oring the arrays
     */
    public boolean[] combineArray(boolean[] array1, boolean[]array2, boolean[]array3, boolean[]array4){
        boolean[] newBool = new boolean[array1.length];
        for(int i = 0; i<array1.length;i++){
            if(array1[i] || array2[i] || array3[i] || array4[i]){
                newBool[i] = true;
            }
            else{
                newBool[i] = false;
            }
        }
        return newBool;
    }
    /**
     * adds a possible Combination to possibleCombinations class variable.
     * @param item1 first Furniture Item to be added
     * @param item2 second Furniture Item to be added
     * @param item3 third Furniture Item to be added
     * @param item4 third Furniture Item to be added
     */
    public void addToCombinations(Furniture item1, Furniture item2, Furniture item3, Furniture item4){
            possibleCombinations.add(new Furniture[] {item1, item2, item3, item4});
    }
    /**
     * prints out all possible combinations in the possibleCombinations 2d array. priceCalculator has to be run first.
     */
    public void printCombinations(){
        for(int i = 0; i<possibleCombinations.size();i++){
            System.out.print(possibleCombinations.get(i)[0].getID() + ", ");
            System.out.print(possibleCombinations.get(i)[1].getID() + ", ");
            System.out.print(possibleCombinations.get(i)[2].getID() + ", ");
            System.out.println(possibleCombinations.get(i)[3].getID());
        }
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
            for(int j = 0; j<4;j++){
                if(possibleCombinations.get(i)[j].getPrice() != -1){
                    sum += possibleCombinations.get(i)[j].getPrice();
                }
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
     * Loops through the pricesTotal array to check if the sum from the sortedArray is equal to the pricesTotal ArrayList that has the exact index
     * of the corresponding combination
     * If it found, then add the combination to the fillOrder array 
     * @param sum Sum of a combination from sortedArray
     * @param index index of the fillOrder to be inserted to fillOrder
     */
    public void insertItems(int sum, int index){
        for(int i = 0; i<pricesTotal.size();i++){
            if(sum == pricesTotal.get(i)){
                if(checkExistingCombination(possibleCombinations.get(i))){
                    fillOrder[index] = possibleCombinations.get(i);
                    insertedItems++;
                    return;
                }
            }
        }
    }
    /**
     * Void Function that adds all combinations to lowestprice in a single array
     */
    public void combinationsToLowestPrice(){
        ArrayList<Furniture> lowestprice = new ArrayList<Furniture>();
        for(int i = 0; i<fillOrder.length; i++){
            for(int j = 0; j<4;j++){
                if(fillOrder[i][j].getPrice() != -1){
                    lowestprice.add(fillOrder[i][j]);
                }
            }
        }
        this.lowestPrice = lowestprice.toArray(new Furniture[lowestprice.size()]);
    }
    /**
     * Getter function that returns the fillOrder of the client, that is a 2D Furniture array of items
     * @return returns 2D array of the fillOrder of the cilent
     */
    public Furniture[][] getFillOrder()
    {
        return this.fillOrder; 
    }
    /**
     * Checks if the all values of a boolean array is true, else return false
     * @param array boolean Array to be checked
     * @return returns true if every boolean value is true, else returns false
     */
    public boolean ifFilled(boolean[] array)
    {
    for(boolean b : array){
        if(!b){
            return false;
        } 
    }
    return true;
    }
    public boolean checkExistingCombination(Furniture[] combination){
        for(int i = 0; i<idsInFillOrder.size();i++){
            if(idsInFillOrder.get(i).equals("null")){
                return false;
            }
            if(idsInFillOrder.get(i).equals(combination[0].getID())){
                return false;
            }
            if(idsInFillOrder.get(i).equals(combination[0].getID())){
                return false;
            }
            if(idsInFillOrder.get(i).equals(combination[0].getID())){
                return false;
            }
            if(idsInFillOrder.get(i).equals(combination[0].getID())){
                return false;
            }
        }
        for(int i = 0;i<combination.length;i++){
            if(combination[i].getID().equals("null")){
                continue;
            }
            idsInFillOrder.add(combination[i].getID());
        }
        return true;
    }
    
}
