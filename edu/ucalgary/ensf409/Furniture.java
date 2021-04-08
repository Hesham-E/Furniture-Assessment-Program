package edu.ucalgary.ensf409; 


/**
 * Furnitue.java for ENSF409 Final Project 
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @version 1.0
 * @since 1.0
 * Furniture is used in the Calculator and Database classes in the form of Furniture arrays 
 *  this class also contains 4 private subclasses, each of which is a member of the Furniture class
 */

public class Furniture implements Comparable<Furniture>{

    /**
     * Data Members: 
     * category: String     - decides which of the following subclasses should be instantiated to fill the client order 
     * chair: Chair         - an Object of type Chair used to fill the client order
     * desk: Desk           - an Object of type Desk used to fill the client order
     * filing: filing       - an Object of type Filing used to fill the client order
     * lamp: Lamp           - an Object of type Lamp used to fill the client order
     */
    public String category; 
    public Chair chair = null; 
    public Desk desk = null;
    public Filing filing = null; 
    public Lamp lamp = null; 

    /**
     * Furniture Constructor
     *      - called for cominations in Database.java
     * @param type is given from the client order, 
     * @param category also given from the client order
     * @param ID pulled from the database in Database.java
     * @param b1 always given by the database as a boolean value 
     * @param b2 always given by the database as a boolean value 
     * @param b3 can be given by the database if the category uses 3 or more boolean columns
     * @param b4 can be given by the database if the category uses 4 boolean columns (chair only)
     * @param price always taken from the database
     * @param manuID always taken from the database
     */
    public Furniture(String type, String category, String ID, boolean b1, boolean b2, boolean b3, boolean b4, int price, String manuID)
    { 
        this.category = category; 
        if (this.category.equals("chair"))
        {   // Chair needs all 4 boolean values to be filled in from the chair table in the database 
            this.chair = new Chair(ID, type, b1, b2, b3, b4, price, manuID);
        }
        if (this.category.equals("desk"))
        {  // Desk only requires b1, b2, and b3 to be filled in from the desk table in the database 
            this.desk = new Desk(ID, type, b1, b2, b3, price, manuID);
        }
        if (this.category.equals("lamp"))
        {   // Lamp only requires b1 and b2 to be filled in from the lamp table in the database 
            this.lamp = new Lamp(ID, type, b1, b2, price, manuID);
        }
        if (this.category.equals("filing"))
        {   // Filing only requires b1, b2, and b3 to be filled in from the filing table in the database
            this.filing = new Filing(ID, type, b1, b2, b3, price, manuID);
        }
    }
    @Override
    public int compareTo(Furniture compareFurn){
        return Integer.compare(getPrice(), compareFurn.getPrice());
    }

    /**
     * Furniture overloaded constructor
     * @param type given in client request 
     * @param category given in the client request
     * used to build an object of type "category" which will be filled with false booleans 
     * and can be compared to calculate a combination 
     */
    public Furniture(String type, String category)
    {
        this.category = category; 
        if (this.category.equals("chair"))
        {
            this.chair = new Chair("null", type, false, false, false, false, -1, "null");
        }
        if (this.category.equals("desk"))
        {
            this.desk = new Desk("null", type, false, false, false, -1, "null");
        }
        if (this.category.equals("lamp"))
        {
            this.lamp = new Lamp("null", type, false, false, -1, "null");
        }
        if (this.category.equals("filing"))
        {
            this.filing = new Filing("null", type, false, false, false, -1, "null");
        }
    }

    /**
     * isFilled method 
     * uses the category of the member being tested to test if every boolean of this member is true,
     * and thus the order for that piece has been filled
     * @return true if the furniture item that called this method is a whole piece
     */
    public boolean isFilled ()
    {
        // Note: the '?' operator determins if the () expression is true, and picks the first option. otherwise chooses the second option
        if (this.category.equals("chair"))
        {
            return (this.chair.legs && this.chair.arms && this.chair.seat && this.chair.cushion)? true : false;    
        }
        if (this.category.equals("desk"))
        {
            return (this.desk.legs && this.desk.drawer && this.desk.top)? true : false; 
        }
        if (this.category.equals("lamp"))
        {
            return (this.lamp.base && this.lamp.bulb)? true : false; 
        }
        if (this.category.equals("filing"))
        {
            return (this.filing.cabinet && this.filing.drawers && this.filing.rails)? true : false; 
        }
        return false;
    }

    /**
     * getID getter method 
     * provided easy access to a members ID String
     * @return the ID of the furniture object that called the function
     */
    public String getID()
    {
        if (this.category .equals("chair")) 
            return this.chair.ID; 
        if (this.category .equals("desk"))
            return this.desk.ID;
        if (this.category .equals("lamp"))
            return this.lamp.ID; 
        if (this.category .equals("filing"))
            return this.filing.ID;
        return "not found";
    }

    /**
     * getManuID getter method
     * provides easy access to a members ManuID String
     * @return the ManuID of the furniture object that called the function
     */
    public String getManuID()
    {
        if (this.category .equals("chair")) 
            return this.chair.manuID; 
        if (this.category .equals("desk"))
            return this.desk.manuID;
        if (this.category .equals("lamp"))
            return this.lamp.manuID; 
        if (this.category .equals("filing"))
            return this.filing.manuID;
        return "not found";
    }

    /**
     * getPrice getter method 
     * provides access to the members Price in integer form 
     * @return integer price value of the furniture piece
     */
    public int getPrice()
    {
        if (this.category .equals("chair")) 
            return this.chair.price; 
        if (this.category .equals("desk"))
            return this.desk.price;
        if (this.category .equals("lamp"))
            return this.lamp.price; 
        if (this.category .equals("filing"))
            return this.filing.price;
        return 0; 
    }
    public String getType()
    {
        if (this.category.equals("chair")) 
            return this.chair.type; 
        if (this.category.equals("desk"))
            return this.desk.type;
        if (this.category.equals("lamp"))
            return this.lamp.type; 
        if (this.category.equals("filing"))
            return this.filing.type;
        return "not found"; 
    }
    public Boolean getBool(int index)
    {
        if(index == 0){
            if (this.category.equals("chair")){
                return chair.legs;
            }
            if (this.category.equals("desk")){
                return desk.legs;
            }
            if (this.category.equals("lamp")){
                return lamp.base;
            }
            if (this.category.equals("filing")){
                return filing.drawers;
            } 
        }
        if(index == 1){
            if (this.category.equals("chair")){
                return chair.arms;
            }
            if (this.category.equals("desk")){
                return desk.top;
            }
            if (this.category.equals("lamp")){
                return lamp.bulb;
            }
            if (this.category.equals("filing")){
                return filing.rails;
            } 
        }
        if(index == 2){
            if (this.category.equals("chair")){
                return chair.seat;
            }
            if (this.category.equals("desk")){
                return desk.drawer;
            }
            if (this.category.equals("filing")){
                return filing.cabinet;
            } 
        }
        if(index == 3){
            if (this.category.equals("chair")){
                return chair.cushion;
            } 
        }
        return false;
    }

    /**
     * Private subclasses 
     */

     /**
      * subclass Desk
      * contains a constructor to initialize the object
      * designed to mimic a row of the database tabe "desk"
      */
    private class Desk {
        /**
         * data members 
         */
        public String ID; 
        public String type; 
        public boolean legs; 
        public boolean top; 
        public boolean drawer;  
        public int price; 
        public String manuID;

        /**
         * Desk constructor
         * @param ID
         * @param type
         * @param legs
         * @param top
         * @param drawer
         * @param price
         * @param manuID
         */
        public Desk(String ID, String type, boolean legs, boolean top, boolean drawer, int price, String manuID)
        {
            this.ID = ID; 
            this.type = type; 
            this.price = price; 
            this.manuID = manuID; 
            this.legs = legs; 
            this.top = top; 
            this.drawer = drawer; 
        }
    }

    /**
      * subclass Lamp 
      * contains a constructor to initialize the object
      * designed to mimic a row of the database tabe "lamp"
      */
    private class Lamp{
        /**
         * data members
         */
        public String ID; 
        public String type; 
        public boolean base; 
        public boolean bulb;  
        public int price; 
        public String manuID;

        /**
         * Lamp constructor
         * @param ID
         * @param type
         * @param base
         * @param bulb
         * @param price
         * @param manuID
         */
        public Lamp(String ID, String type, boolean base, boolean bulb, int price, String manuID) 
        {
            this.ID = ID; 
            this.type = type; 
            this.price = price; 
            this.manuID = manuID; 
            this.base = base; 
            this.bulb = bulb; 
        }
        
    }

    /**
      * subclass Chair
      * contains a constructor to initialize the object
      * designed to mimic a row of the database tabe "chair"
      */
    private class Chair{
        /**
         * data members
         */
        public String ID; 
        public String type; 
        public boolean legs; 
        public boolean arms; 
        public boolean seat; 
        public boolean cushion; 
        public int price; 
        public String manuID;

        /**
         * Chair constructor
         * @param ID
         * @param type
         * @param legs
         * @param arms
         * @param seat
         * @param cushion
         * @param price
         * @param manuID
         */
        public Chair(String ID, String type, boolean legs, boolean arms, boolean seat, boolean cushion, int price, String manuID)
        {
            this.ID = ID; 
            this.type = type; 
            this.price = price; 
            this.manuID = manuID; 
            this.legs = legs; 
            this.arms = arms; 
            this.seat = seat; 
            this.cushion = cushion;  
        }
    }

    /**
      * subclass Filing
      * contains a constructor to initialize the object
      * designed to mimic a row of the database tabe "filing"
      */
    private class Filing{

        /**
         * data members
         */
        public String ID; 
        public String type; 
        public boolean rails; 
        public boolean drawers; 
        public boolean cabinet;  
        public int price; 
        public String manuID;

        /**
         * Filing constructor
         * @param ID
         * @param type
         * @param rails
         * @param drawers
         * @param cabinet
         * @param price
         * @param manuID
         */
        public Filing(String ID, String type, boolean rails, boolean drawers, boolean cabinet, int price, String manuID) 
        {
            this.ID = ID; 
            this.type = type; 
            this.price = price; 
            this.manuID = manuID; 
            this.rails = rails; 
            this.drawers = drawers; 
            this.cabinet = cabinet; 
        }

    }
}
