import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException
    {
        FileIO inputOutput = new FileIO();
        String [] clientOrder = new String[3];
        try 
        { 
            clientOrder = inputOutput.inputFetching();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Database myDB = new Database("jdbc:mysql://localhost/inventory", "your_userName", "your_password");
        myDB.initializeConnection();
        Furniture[] inInventory = myDB.findUsedFurniture(clientOrder[0], clientOrder[1], Integer.parseInt(clientOrder[2]));
        Furniture[] fillOrder = new Furniture[Integer.parseInt(clientOrder[2])];
        Calculator calculating = new Calculator(inInventory, fillOrder); 
        calculating.priceCalculator();
        inputOutput.FormattedFormOutput(calculating.LowestPrice, clientOrder);
        
        
    }


}
