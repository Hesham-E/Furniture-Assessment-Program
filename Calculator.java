

/**
 * Calculator.java for ENSF409 final project W2021
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @version 1.0
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
     * fillOrder: Furniture[]               - contains the order that was placed by the user, 
     *                                          the length of which is the quantity requested
     * possibleCombinations: Furniture[][]  - rows will each be a set of Furniture picked from 
     *                                          inInventory, which may or may not fill the order.
     *                                          The row that fills the order in the cheapest way 
     *                                          possible will be given to lowestPrice
     * lowestPrice: Furniture[]             - will contain the set of furniture items from the database
     *                                          that fill the order in the cheapest way. 
     */
    public Furniture[] inInventory; 
    public Furniture[] fillOrder;
    public Furniture[][] possibleCombinations; 
    public Furniture[] lowestPrice = new Furniture[100]; 

    /**
     * Constructor for Calculaotr class
     * 
     * @param request is the user request, which is a String[] of length 3
     * contains a super constructor for the Database class, then initializes the connection 
     * in the specified database
     * sets the inInventory member using a super method from the Database class
     * initializes the length of fillOrder with the integer value from request[2]
     */
    public Calculator(String[] request) {
        super("jdbc:mysql://localhost", "youruser", "yourpass"); // ENSURE THIS IS SET UP FOR SUBMISSION 
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
        this.fillOrder = new Furniture[quantity];
    }

    /** 
     * to be completed in the ryukio branch of the ENSF409FinalProject repository
     */
    public void priceCalculator ()
    {
        //coming soon  
        /*
            lowestPrice will end up being filled with furniture types
            the sum of which will result in every boolean in fillOrder being set true, 
            as requested, Lowest price will be including the ID numbers and prices of 
            each member in use. 
        */
        // included from debugging on April 3rd 20201 
        this.lowestPrice[0] = new Furniture("mesh", "chair");
    }
} // end of class declaration 