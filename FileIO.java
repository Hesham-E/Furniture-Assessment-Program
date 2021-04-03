import java.io.*;
import java.util.Arrays; 

public class FileIO {

        private File outputFile = new File("OrderForm.txt"); 


        public String[] inputFetching() throws IOException
        {
            System.out.println();
            System.out.println();
            System.out.println("Hello, welcome to the Supply Chain Management (SCM) application");
            System.out.println("To begin the program, please enter your order request in the following format:");
            System.out.println("         Furniture type Furniture Category, quantity");
            System.out.println("eg:      Executive chair, 1       or        Standing desk, 3");
            System.out.println();
            System.out.println("Please ensure that the format is followed strictly in the specified order, to ensure"); 
            System.out.println("that your request is processed correctly. When you are satisfied with your input,");
            System.out.println("please press enter.");
            System.out.println();
            System.out.println();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
            String input = reader.readLine(); 
            String [] order = input.split("\\s");
            order[1] = order[1].substring(0, order[1].length()-1) ; 
            System.out.println();
            System.out.println();
            System.out.println("If your order is successful, there will be an order form created in the filePath where");
            System.out.println("this program is saved, please refer to the file for a completed order when prompted by");
            System.out.println("the terminal.");
            System.out.println("If your order cannot be fulfilled with the current inventory, you will be notified by the terminal");
            System.out.println("with a list of manufacturers that make the furniture item that you have requested.");
            System.out.println();
            System.out.println();
            return order;
        }

        public void FormattedFormOutput(Calculator calc, String[] clientOrder)
        {
            boolean orderFilled = true; 
            for (int i=0; i < calc.lowestPrice.length; i++)
            {
               orderFilled = (!calc.lowestPrice[i].isFilled())? false : true;
            }
            if (orderFilled)
            {
                try( FileWriter fw = new FileWriter(this.outputFile, true))
                {
                    BufferedWriter writer = new BufferedWriter(fw);
                    writer.write("Furniture Order Form\n\nFaculty Name:\nContact:\nDate\n\nOrigional Request: " 
                                    + clientOrder[0] + " " + clientOrder[1] + ", " 
                                            + clientOrder[2] + "\n\nItems Ordered\n" );
                    int totalPrice=0; 
                    for (int i=0; i < calc.lowestPrice.length; i++)
                    {
                        writer.write("ID: " + calc.lowestPrice[i].getID() + "\n");
                        totalPrice += calc.lowestPrice[i].getPrice(); 
                    }
                    writer.write("\n");
                    writer.write("Total Price: " + totalPrice);
                    writer.flush();
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                for (int i = 0; i < calc.lowestPrice.length; i++)
                {
                    calc.removeFurniture(calc.lowestPrice[i].category, calc.lowestPrice[i].getID());
                }
            }
            else
            {
                System.out.println("Sadly, we are unable to fill your request at this time, given our limited inventory.");
                System.out.println("Below, you may find a list of manufacturers that make your requested furniture item:");
                String manuID = "";
                for (int i=0; i < calc.inInventory.length; i++)
                {
                    if (!manuID.contains(calc.inInventory[i].getManuID()))
                    {
                        manuID = manuID + calc.inInventory[i].getManuID(); 
                    }
                }
                calc.printManufacturers(manuID);
            }
        }
}