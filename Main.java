import java.io.IOException;
import edu.ucalgary.ensf409.*; 


public class Main {

    public static void main(String [] args)
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
        Calculator calculating = new Calculator(clientOrder); 
        calculating.priceCalculator();
        inputOutput.FormattedFormOutput(calculating, clientOrder);

        
        
    }


}