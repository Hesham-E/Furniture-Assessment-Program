package edu.ucalgary.ensf409;

import java.io.*;
/**
 * Calculator.java for ENSF409 final project W2021
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @author Faisal Hossain <a href="mailto:faisal.hossain1@ucalgary.ca">faisal.hossain1@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 * contains two methods:
 *      inputFetching       - begins the program and prompts the user for inputs 
 *      FormattedFormOutput - decides if the request has been filled
 *                              if true, send to the output file in the format 
 *                              if false, display failure message to the terminal
 */
public class FileIO {

        /**
         * inputFetching method
         * prints instructions and examples to the terminal
         * generates a String[] for the user input from the termnial
         * prints more information and instructions
         * @return String[] of user input, contains type, category and quantity
         * @throws IOException
         */
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


        /**
         * formattedFormOutput method
         * 
         * @param calc contains the whole Calculator object created in main to grant easy access to datamembers
         * @param clientOrder String[] of user input, contains type, category and quantity
         */
        public void formattedFormOutput(Calculator calc, String[] clientOrder)
        {
            boolean orderFilled = false; // assume the order has not been filled
            orderFilled = calc.priceCalculator();

            if (orderFilled)
            {
                try
                {
                    FileWriter fw = new FileWriter("OrderForm.txt", false); // create the file in the working directory
                    BufferedWriter writer = new BufferedWriter(fw);
                    // general formatting subject to change however seen fit, this simply follows the example provided 
                    // in the project handout
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
                // once the order has been confirmed and outputted, the used furniture peices must be deleted form the database
                for (int i = 0; i < calc.lowestPrice.length; i++)
                {
                    calc.removeFurniture(calc.lowestPrice[i].category, calc.lowestPrice[i].getID());
                }
            }
            else
            {
                // if the order cannot be filled, the following will be printed to the terminal
                System.out.println("Sadly, we are unable to fill your request at this time, given our limited inventory.");
                System.out.println("Below, you may find a list of manufacturers that make your requested furniture item:");
                String manuID = "";
                /* this creates a String[] that contains the manufacturers of the peices of furniture 
                    in the database under the specified table and type */ 
                    for(int i = 0; i<calc.inInventory.length;i++){
                        if (!manuID.contains(calc.inInventory[i].getManuID())) //ensures there are no duplicates
                        {
                            manuID = manuID + calc.inInventory[i].getManuID() + " "; 
                        }
                    }
                String [] manuIDSplit = manuID.split("\\s"); // splits the string into the "words" for the completed array
                calc.printManufacturers(manuIDSplit); // calls for the manufacturers to be printed to the termninal
            } 
        }
}