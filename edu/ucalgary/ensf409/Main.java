package edu.ucalgary.ensf409;

import java.io.IOException;
/**
 * Main.java for ENSF409 final project W2021
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @author Faisal Hossain <a href="mailto:faisal.hossain1@ucalgary.ca">faisal.hossain1@ucalgary.ca</a>\
 * @author Hesham Elkaliouby <a href="mailto:hesham.elkaliouby@ucalgary.ca">hesham.elkaliouby@ucalgary.ca</a>
 * @author Dagvadorj Altankhuyag <a href="mailto:dagvadorj.altankhuya@ucalgary.ca">dagvadorj.altankhuya@ucalgary.ca</a>
 * @version 1.2
 * @since 1.0
 */

/**
 * to run the program:
 * Compile the program by entering this to your terminal (if you are on linux, replace ; with :)
 * javac -cp .;mysql-connector-java-8.0.23.jar;. ./edu/ucalgary/ensf409/Main.java
 * Run the program by entering this to your terminal
 * java -cp .;mysql-connector-java-8.0.23.jar;. edu.ucalgary.ensf409.Main
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
            System.exit(0); 
        }
        /* creating the calculator object, which by the constructor also generates
         the database the calculator will pull information from */
        Calculator calculating = new Calculator(clientOrder); 
        // System.out.println(calculating.priceCalculator());// see Calculator documentation
        inputOutput.formattedFormOutput(calculating, clientOrder); // see FileIO documentation
    }
}