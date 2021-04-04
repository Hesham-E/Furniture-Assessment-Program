package edu.ucalgary.ensf409;

import java.sql.*; 
 

/**
 * Database.java for ENSF409 Final Project W2021
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 * contains methods which connect to the specified database
 * no methods add data, only access or remove data
 */
public class Database {
    /**
     * Data members:
     * DBURL: String        - URL for the database
     * USERNAME: String     - username to access the given database 
     * PASSWORD: String     - password associated with the username above
     * connect: Connection  - connects to the database using the above parameters 
     */
    public final String DBURL;
    public final String USERNAME; 
    public final String PASSWORD; 
    private Connection connect; 

    /**
     * Database constructor 
     * @param DBURL URL for the database
     * @param USERNAME username to access the given database
     * @param PASSWORD password associated with the username above
     * called by super() in the Calculator constructor
     */
    public Database( String DBURL, String USERNAME, String PASSWORD)
    {
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    /**
     * initializeConnection method
     * uses connect and driverManager to obtain a connection to the SQL Database using the members of the current
     * object. 
     * will detect if the connection is not successful and print an error message
     * returns nothing 
     */
    public void initializeConnection()
    {
        try 
        { 
            connect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
        } catch (SQLException e) 
        {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    /**
     * findUsedFurniture method
     * @param type user input from fileIO.inputFetching for the furniture type
     * @param category user input from fileIO.inputFetching for the furniture category 
     * @param quantity user input from fileIO.inputFetching for the quantity of furniture
     * @return array of Funriture pieces that match the type and category 
     */
    public Furniture[]  findUsedFurniture(String type, String category, int quantity)
    {
        Statement statement; 
        ResultSet set; 
        Furniture[] combinations = new Furniture[100]; 
        String query = "SELECT * FROM inventory." + category + " WHERE type='" + type + "'";//follows SQL syntax and will select every item from the category table that has the specified type
        try 
        {
            statement = connect.createStatement();
            set = statement.executeQuery(query);
            int i=0; // will be used to iterate through combinations to initialize its indecies
            while (set.next())
            {   // the category will decide which member of furniture to initialize, the other members are pulled from the table to build the furniture requested by the user
                // some booleans are automatically set to false, since the category or type does not contain that number of members 
                // lamp has 2 booleans but chair has 4
                if (category.equals("chair"))
                {
                    combinations[i] = new Furniture(set.getString("type"), "chair", set.getString("ID"), set.getBoolean("Legs"), set.getBoolean("Arms"), set.getBoolean("Seat"), set.getBoolean("Cushion"), set.getInt("Price"), set.getString("ManuID"));
                }
                else if (category.equals("desk") )
                {
                    combinations[i] = new Furniture(set.getString("type"), "desk", set.getString("ID"), set.getBoolean("Legs"), set.getBoolean("Top"), set.getBoolean("Drawer"), false, set.getInt("Price"), set.getString("ManuID"));
                }
                else if (category.equals("filing") )
                {
                    combinations[i] = new Furniture(set.getString("type"), "filing", set.getString("ID"), set.getBoolean("Rails"), set.getBoolean("Drawers"), set.getBoolean("Cabinet"), false, set.getInt("Price"), set.getString("ManuID"));
                }
                else if (category.equals("lamp") )
                {
                    combinations[i] = new Furniture(set.getString("type"), "lamp", set.getString("ID"), set.getBoolean("Price"), set.getBoolean("Bulb"), false, false, set.getInt("Price"), set.getString("ManuID"));
                }
                i++; 
            }
            statement.close();
            set.close();
        }
        catch (SQLException e) // if type or category is not possible
        {
            System.out.println("Names cannot be shown.");
            e.printStackTrace();
        }
        return combinations;
    }

    /**
     * removeFurniture method
     * called after an order has been fully filled
     * @param Category the table to look for the ID in
     * @param ID    the specific ID of a piece of furniture that has been used to fill an order
     * is called in a loop, so the action is only applied once per ID 
     * returns nothing
     */
    public void removeFurniture(String Category, String ID)
    {
        Statement statement;
        try {
            statement = connect.createStatement();
            statement.executeUpdate("DELETE FROM INVENTORY." + Category + " WHERE ID=" + ID); //SQL syntax to delete the member of category with the correct ID
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * printManufacturers method 
     * called in the case that the order cannot be filled
     * @param manuID String[] of all of the manufactuerer ID's that make the furniutre items of the specified type and category. 
     *       taken form the inInventory member of Calculator, since it contains every member of that type and category, as well as their ManuID
     * returns nothing
     */
    public void printManufacturers(String [] manuID)
    {
        Statement statement; 
        ResultSet set;  
        for (int i=0; i < manuID.length; i++)
        {   //iteratively finds the manufacturer in SQL with the manuID = manuID[i]
            String query = "SELECT * FROM inventory.manufacturer WHERE ManuID='" + manuID[i] + "'"; // follows SQL syntax, will get the info of every manufacturer 
            try
            {
                statement = connect.createStatement();
                set = statement.executeQuery(query);
                while (set.next())
                {   //prints a statement to the termnial that will list the manufacturer that has the manuID[i] 
                    System.out.println("     - For " + set.getString("Name") + " in " + set.getString("Province") +  ", call: " + set.getString("Phone"));
                }   // has the form:         - For companyName in province, call: companyNumber
                statement.close();
                set.close();
            } catch (SQLException e) // if that manufacturer no longer exists in the database, do the following
            {
                System.out.println("The manufacuturer listed under: " + manuID[i] + " is no longer a supplier to the Univeristy of Calgary");
                e.printStackTrace();
            }
            System.out.println();
            System.out.println();
        }
    }
}  // end of class declaration
