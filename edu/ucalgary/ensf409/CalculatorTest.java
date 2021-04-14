package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.util.ArrayList;


public class CalculatorTest
{
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

    @Test
    public void testPriceCalculatorPossible ()
    {
        //THIS ASSUMES DATABASE GIVEN IN ASSIGNMENT. If choosing another database, ensure that the values are able to be calculated
        String[] request = {"Executive", "chair", "1"};
        Calculator test = new Calculator(request);
        boolean recieved = test.priceCalculator();
        boolean expected = true;

        assertTrue("priceCalculator failed to find a possible combination", (recieved == expected));
    }
}
