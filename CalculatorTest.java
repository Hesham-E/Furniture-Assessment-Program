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
        Furniture[] expectedArr = {new Furniture("Kneeling", "chair", "C1320", true, false, false, false, 50, "002"),
                                   new Furniture("Kneeling", "chair", "C3819", false, false, true, false, 75, "005")};
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
    public void testCombineArray2Args ()
    {
        String[] request = {"Kneeling", "chair", "2"};
        Calculator test = new Calculator(request);
        boolean[] arr1 = {true, false, false};
        boolean[] arr2 = {true, true, true};
        boolean[] recieved = test.combineArray(arr1, arr2);
        boolean[] expected = {true, true, true};

        assertTrue("Boolean arrays compared improperly", Arrays.equals(recieved, expected));
    }

    @Test
    public void testCombineArray3Args ()
    {
        String[] request = {"Kneeling", "chair", "2"};
        Calculator test = new Calculator(request);
        boolean[] arr1 = {true, false, false, false};
        boolean[] arr2 = {true, false, true, false};
        boolean[] arr3 = {false, false, false, false};
        boolean[] recieved = test.combineArray(arr1, arr2, arr3);
        boolean[] expected = {true, false, true, false};

        assertTrue("Boolean arrays compared improperly", Arrays.equals(recieved, expected));
    }

    @Test
    public void testCombineArray4Args ()
    {
        String[] request = {"Kneeling", "chair", "2"};
        Calculator test = new Calculator(request);
        boolean[] arr1 = {true, false, false, false};
        boolean[] arr2 = {true, false, true, false};
        boolean[] arr3 = {false, false, false, false};
        boolean[] arr4 = {false, true, false, true};
        boolean[] recieved = test.combineArray(arr1, arr2, arr3, arr4);
        boolean[] expected = {true, true, true, true};

        assertTrue("Boolean arrays compared improperly", Arrays.equals(recieved, expected));
    }

    @Test
    public void testPriceCalculatorNotPossible ()
    {
        String[] request = {"Kneeling", "chair", "2"};
        Calculator test = new Calculator(request);
        boolean recieved = test.priceCalculator();
        boolean expected = false;

        assertTrue("priceCalculator found an impossible combination", (recieved == expected));
    }

    @Test
    public void testPriceCalculatorPossible ()
    {
        String[] request = {"Executive", "chair", "1"};
        Calculator test = new Calculator(request);
        boolean recieved = test.priceCalculator();
        boolean expected = true;

        assertTrue("priceCalculator found an impossible combination", (recieved == expected));
    }
}
