package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.util.ArrayList;

/**
 * CalculatorTest.java for ENSF409 final project W2021
 * Unit tests for Calculator.java class
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @author Faisal Hossain <a href="mailto:faisal.hossain1@ucalgary.ca">faisal.hossain1@ucalgary.ca</a>
 * @author Hesham Elkaliouby <a href="mailto:hesham.elkaliouby@ucalgary.ca">hesham.elkaliouby@ucalgary.ca</a>
 * @author Dagvadorj Altankhuyag <a href="mailto:dagvadorj.altankhuya@ucalgary.ca">dagvadorj.altankhuya@ucalgary.ca</a>
 * @version 1.2
 * @since 1.0
 */
public class CalculatorTest
{
    /**
     * testConstructorCheckInventory() method
     * A constructor test to see if object initalized correctly
     * and found the only two kneeling chairs in the system
     */
    @Test
    public void testConstructorCheckInventory ()
    {
        String[] request = {"Kneeling", "chair", "2"};
        Calculator test = new Calculator(request);
        String[] expectedID = {"C1320", "C3819"};
        ArrayList<String> recieved = new ArrayList<String>();
        for (int i = 0; i < test.inInventory.length; i++)
        {
            recieved.add(test.inInventory[i].getID());
        }
        assertTrue("Kneeling chairs were not found in inventory", 
                    Arrays.equals(recieved.toArray(), expectedID));
    }

    /**
     * testPriceCalculatorNotPossible() method
     * Tests how priceCalculator reacts to an impossible combination of items
     */
    @Test
    public void testPriceCalculatorNotPossible ()
    {
        //THIS ASSUMES DATABASE GIVEN IN ASSIGNMENT. If choosing another database, ensure that the values are not able to be calculated
        String[] request = {"Kneeling", "chair", "2"};
        Calculator test = new Calculator(request);
        boolean recieved = test.priceCalculator();
        boolean expected = false;

        assertTrue("priceCalculator found an impossible combination", (recieved == expected));
    }

    /**
     * testPriceCalculatorPossible() method
     * Tests how priceCalculator reacts to a possible combination of items
     */
    @Test
    public void testPriceCalculatorPossible ()
    {
        String[] request = {"Ergonomic", "chair", "1"};
        Calculator test = new Calculator(request);
        boolean recieved = test.priceCalculator();
        boolean expected = true;

        assertTrue("priceCalculator failed to find a possible combination", (recieved == expected));
    }

    /**
     * testPriceCalculatorPossible2() method
     * Tests how priceCalculator reacts to a possible combination of items
     * This time the test requests multiple items
     */
    @Test
    public void testPriceCalculatorPossible2 ()
    {
        String[] request = {"Desk", "lamp", "3"};
        Calculator test = new Calculator(request);
        boolean recieved = test.priceCalculator();
        boolean expected = true;

        assertTrue("priceCalculator failed to find a possible combination", (recieved == expected));
    }

     /**
     * testPriceCalculatorPossible3() method
     * Tests how priceCalculator reacts to a possible combination of items
     * This time the test requests multiple items and 'strange' capitalizations
     */
    public void testPriceCalculatorPossible3 ()
    {
        String[] request = {"StUdY", "LAMP", "2"};
        Calculator test = new Calculator(request);
        boolean recieved = test.priceCalculator();
        boolean expected = true;

        assertTrue("priceCalculator failed to find a possible combination", (recieved == expected));
    }


    @Test
    public void testPriceCalculatorPossibleCheckCases ()
    {
        String[] request = {"Large", "filing", "1"};
        Calculator test = new Calculator(request);
        boolean recieved = test.priceCalculator();
        boolean expected = true;

        assertTrue("priceCalculator failed to find a possible combination", (recieved == expected));
    }
}
