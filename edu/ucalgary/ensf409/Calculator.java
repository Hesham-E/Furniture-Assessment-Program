package edu.ucalgary.ensf409;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Calculator.java for ENSF409 final project W2021
 * Written by Josh Vanderstoop 
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
     * possibleCombinations: List<Furniture[]  - rows will each be a set of Furniture picked from 
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
    public ArrayList<Integer> pricesTotal = new ArrayList<Integer>();
    public int[] sortedArray;
    public List<Furniture[]> possibleCombinations = new ArrayList<Furniture[]>();
    public Calculator(String[] request) {
        super("jdbc:mysql://localhost/INVENTORY", "root", "1234"); // ENSURE THIS IS SET UP FOR SUBMISSION 
        super.initializeConnection(); 
        int quantity=0;
        try {
            quantity = Integer.parseInt(request[2]);
        }
        catch (NumberFormatException e)
        {
            // if the user asks for a non integer number of furniture pieces 
            System.out.println("Sorry, the quanitty you have requested is not a real number.");
            System.exit(0);
        }
        this.inInventory = super.findUsedFurniture(request[0], request[1], quantity);
        this.fillOrder = new Furniture[quantity][3];
    }

    public boolean priceCalculator ()
    {
        //coming soon  
        /*
            lowestPrice will end up being filled with furniture types
            the sum of which will result in every boolean in fillOrder being set true, 
            as requested, Lowest price will be including the ID numbers and prices of 
            each member in use. 
        */
        if(inInventory.length == 0){
            return false;
        }
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
        if(inInventory[0].category.equals("lamp")){
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
                }
            }
            if(possibleCombinations.size() == 0){
                return false;
            }
            else if(possibleCombinations.size() < fillOrder.length){
                return false;
            }
            else{
                calculatePricesTotal();
                for(int i = 0; i<fillOrder.length;i++){
                    insertItems(sortedArray[i], i);
                }
                combinationsToLowestPrice();
                return true;
            }
        }
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
            if(possibleCombinations.size() == 0){
                return false;
            }
            else if(possibleCombinations.size() < fillOrder.length){
                return false;
            }
            else{
                calculatePricesTotal();
                for(int i = 0; i<fillOrder.length;i++){
                    insertItems(sortedArray[i], i);
                }
                combinationsToLowestPrice();
                return true;
            }
        }
        else{
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
                    }

                }
            }
            if(possibleCombinations.size() == 0){
                return false;
            }
            else if(possibleCombinations.size() < fillOrder.length){
                return false;
            }
            else{
                calculatePricesTotal();
                for(int i = 0; i<fillOrder.length;i++){
                    insertItems(sortedArray[i], i);
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
     * @return
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
     * @param array1
     * @param array2
     * @param array3
     * @return
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
     * @param array1
     * @param array2
     * @param array3
     * @param array4
     * @return
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
     * adds a possible Combination to possibleCombinations
     * @param item1
     * @param item2
     * @param item3
     * @param item4
     */
    public void addToCombinations(Furniture item1, Furniture item2, Furniture item3, Furniture item4){
            possibleCombinations.add(new Furniture[] {item1, item2, item3, item4});
    }
    public void printCombinations(){
        for(int i = 0; i<possibleCombinations.size();i++){
            System.out.print(possibleCombinations.get(i)[0].getID() + ", ");
            System.out.print(possibleCombinations.get(i)[1].getID() + ", ");
            System.out.print(possibleCombinations.get(i)[2].getID() + ", ");
            System.out.println(possibleCombinations.get(i)[3].getID());
        }
    }
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
    private int[] integerToInt(){
        int[] returnInt = new int[pricesTotal.size()];
        for(int i = 0; i<pricesTotal.size();i++){
            returnInt[i] = pricesTotal.get(i).intValue();
        }
        return returnInt;
    }
    public void insertItems(int sum, int index){
        for(int i = 0; i<pricesTotal.size();i++){
            if(sum == pricesTotal.get(i)){
                fillOrder[index] = possibleCombinations.get(i);
                return;
            }
        }
    }
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
    public Furniture[][] getFillOrder()
    {
        return this.fillOrder; 
    }
    /**
     * Checks if the all values of a boolean array is true, else return false
     * @param array
     * @return
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
    
}