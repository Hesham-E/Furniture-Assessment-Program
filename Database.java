import java.sql.*;
import edu.ucalgary.ensf409.Furniture; 
public class Database {

    public final String DBURL;
    public final String USERNAME; 
    public final String PASSWORD; 
    private Connection connect; 

    public Database( String DBURL, String USERNAME, String PASSWORD)
    {
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public void initializeConnection()
    {
        try 
        { 
            connect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
            System.out.println("Connection successful");
        } catch (SQLException e) 
        {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    public Furniture[]  findUsedFurniture(String type, String category, int quantity)
    {
        Statement statement; 
        ResultSet set; 
        Furniture[] combinations = new Furniture[100]; 
        String query = "SELECT * FROM inventory." + category + " WHERE type='" + type + "'";
        try 
        {
            statement = connect.createStatement();
            set = statement.executeQuery(query);
            int i=0; 
            while (set.next())
            {
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
        catch (SQLException e) 
        {
            System.out.println("Names cannot be shown.");
            e.printStackTrace();
        }
        return combinations; 
    }

    public void removeFurniture(String Category, String ID)
    {
        Statement statement;
        try {
            statement = connect.createStatement();
            statement.executeUpdate("DELETE FROM INVENTORY." + Category + " WHERE ID=" + ID);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printManufacturers(String manuID)
    {
        Statement statement; 
        ResultSet set;  
        for (int i=0, j=3; j < manuID.length(); i+=3, j+=3)
        {
            //String query = "SELECT * FROM inventory.manufacturer WHERE ManuID='" + 
            System.out.println(manuID.substring(i, j));
        }
    }


    
}
