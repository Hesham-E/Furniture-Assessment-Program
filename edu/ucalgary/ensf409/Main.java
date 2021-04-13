package edu.ucalgary.ensf409;

/**
 * Main.java for ENSF409 final project W2021
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @author Faisal Hossain <a href="mailto:faisal.hossain1@ucalgary.ca">faisal.hossain1@ucalgary.ca</a>
 * @author Hesham Elkaliouby <a href="mailto:hesham.elkaliouby@ucalgary.ca">hesham.elkaliouby@ucalgary.ca</a>
 * @author Dagvadorj Altankhuyag <a href="mailto:dagvadorj.altankhuya@ucalgary.ca">dagvadorj.altankhuya@ucalgary.ca</a>
 * @version 1.2
 * @since 1.0
 */

/**
 * to run the program: 
 * Move to the directory of the edu/ucalgary/ensf409 package
 * Use the following two commands in the terminal of your choice
 * to compile (windows):
 *           javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Main.java
 * to compile (linux):
 *           javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Main.java
 * to run (windows):
 *           java -cp .;lib/mysql-connector-java-8.0.23.jar edu.ucalgary.ensf409.Main
 * to run (Linux):
 *           java -cp .:lib/mysql-connector-java-8.0.23.jar edu.ucalgary.ensf409.Main
 * 
 * you will be prompted with instructions through the terminal once the program has started. 
 */

 /**
  * to use the tests:
  * Move to the directly local with edu
  * to compile (windows):
  *     javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/[TESTNAMEHERE].java
  * to compile (Linux):
  *     javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/[TESTNAMEHERE].java
  * to run(windows):
  *     java -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.[TESTNAMEHERE]
  * to run(Linux):
  *     java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:lib/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.[TESTNAMEHERE]
  * TESTNAMEHERE corresponds to the test name you wish to run
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
            /* creating the calculator object, which by the constructor also generates
            the database the calculator will pull information from */
            Calculator calculating = new Calculator(clientOrder);
            inputOutput.formattedFormOutput(calculating, clientOrder); // see FileIO documentation
        }
        catch (Exception e)
        {
            // if the exeption is thrown and the inputs are not correct 
            System.out.println("One of your inputs was not correct, please restart the application and try again.");
            System.exit(0); 
        }
        
    }
}