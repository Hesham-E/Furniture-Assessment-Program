package edu.ucalgary.ensf409;

import java.io.IOException;
/**
 * Main.java for ENSF409 final project W2021
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @author Faisal Hossain <a href="mailto:faisal.hossain1@ucalgary.ca">faisal.hossain1@ucalgary.ca</a>
 * @version 1.2
 * @since 1.0
 */

/**
 * to run the program: NOT FINISHED
 */

public class Main {

    /**
     * main method
     * creates and uses objects from the FileIO and Calculator
     * classes. 
     */
    public static void main(String [] args)
    {
        FileIO inputOutput = new FileIO();
        String [] clientOrder = new String[3];
        try 
        { 
            // see FileIO documentation
            clientOrder = inputOutput.inputFetching();
        }
        catch (IOException e)
        {
            // if the exeption is thrown and the inputs are not correct 
            System.out.println("One of your inputs was not correct, please restart the application and try again.");
            e.printStackTrace();
        }
        /* creating the calculator object, which by the constructor also generates
         the database the calculator will pull information from */
        Calculator calculating = new Calculator(clientOrder); 
        // System.out.println(calculating.priceCalculator());// see Calculator documentation
        inputOutput.formattedFormOutput(calculating, clientOrder); // see FileIO documentation
    }
}