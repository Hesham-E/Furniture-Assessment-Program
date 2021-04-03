import java.text.ParseException;

public class Calculator extends Database {

    public Furniture[] inInventory; 
    public Furniture[] fillOrder;
    public Furniture[][] possibleCombinations; 
    public Furniture[] lowestPrice = new Furniture[100]; 

    public Calculator(String[] request) {
        super("jdbc:mysql://localhost", "JoshStoop", "Tennis%&");
        super.initializeConnection(); 
        int quantity=0;
        try {
            quantity = Integer.parseInt(request[2]);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Sorry, the quanitty you have requested is not a real number.");
            System.exit(0);
        }
        this.inInventory = super.findUsedFurniture(request[0], request[1], quantity);
        this.fillOrder = new Furniture[quantity];
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
        this.lowestPrice[0] = new Furniture("mesh", "chair");
    }

    public Furniture[] getFillOrder()
    {
        return this.fillOrder; 
    }
    
}
