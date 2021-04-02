public class Calculator {

    private Furniture[] inInventory; 
    public Furniture[] fillOrder;
    public Furniture[] LowestPrice; 
    public Calculator(Furniture[] inInventory, Furniture[] fillOrder) {
        this.inInventory = inInventory;
        this.fillOrder = fillOrder; 
    }

    public void priceCalculator ()
    {
        //coming soon  
        /*
            lowestPrice will end up being filled with furniture types
            the sum of which will result in every boolean in fillOrder being set true, 
            as requested, Lowest price will be including the ID numbers and prices of 
            each member in use. 
        */
    }

    public Furniture[] getFillOrder()
    {
        return this.fillOrder; 
    }
    
}
