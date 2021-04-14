package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.util.ArrayList;


public class CalculatorTest
{
    /**
     * Tests if the Constructor and inInventory data member was properly applied
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
     * Tests the ifFilled function
     * Tests the ifFilled function with a combination that should be true, where the currentFurntiure combination does meet the client order requirements
     * In this example, the furntiure combination of furniture1, furniture2 does meet the client order requirements because
     * there is atleast 1 booleans. (Boolean 1 has 1, Boolean 2 has 1 and Boolean 3 has 1 and Boolean 4 has 2)
     */
    @Test
    public void testifFilled()
    {
        //Needs to have atleast one in inventory
        String[] request = {"Kneeling", "chair", "1"};
        Calculator test = new Calculator(request);
        ArrayList<Furniture> input = new ArrayList<Furniture>();
        Furniture furniture1 = new Furniture(request[0], request[1], "C1202", true, false, true, true, 123, "001");
        Furniture furniture2 = new Furniture(request[0], request[1], "C1205", false, true, false, true, 123, "001");
        input.add(furniture1);
        input.add(furniture2);
        test.currentFurniture = input;
        boolean expected = true;
        assertTrue("ifFilled Test failed", test.ifFilled() == expected);
    }
    /**
     * Tests the ifFilled function
     * Tests the ifFilled function with a combination that should be true, where the currentFurntiure combination does meet the client order requirements
     * In this example, the furntiure combination of furniture1, furniture2, furniture3 does meet the client order requirements because
     * there is atleast 2 booleans. (Boolean 1 has 2, Boolean 2 has 2 and Boolean 3 has 2 (boolean 4 is not needed, since this is a desk))
     */
    @Test
    public void testifFilled2()
    {
        //Needs to have atleast one in inventory
        String[] request = {"Standing", "desk", "2"};
        Calculator test = new Calculator(request);
        ArrayList<Furniture> input = new ArrayList<Furniture>();
        Furniture furniture1 = new Furniture(request[0], request[1], "F003", true, false, true, false, 123, "001");
        Furniture furniture2 = new Furniture(request[0], request[1], "F004", false, true, true, false, 123, "002");
        Furniture furniture3 = new Furniture(request[0], request[1], "F005", true, true, false, false, 123, "003");
        input.add(furniture1);
        input.add(furniture2);
        input.add(furniture3);
        test.currentFurniture = input;
        boolean expected = true;
        assertTrue("ifFilled2 Test failed", test.ifFilled() == expected);
    }
   /**
     * Tests the ifFilled function
     * Tests the ifFilled function with a combination that should be false, where the currentFurntiure combination does not meet the client order requirements
     * In this example, the furntiure combination of furniture1, furniture2, furniture3 does not meet the client order requirements because
     * there is no atleast 2 booleans. (Boolean 1 has 1, Boolean 2 has 2 and Boolean 3 has 2 (boolean 4 is not needed, since this is a desk))
     */
    @Test
    public void testifFilled3()
    {
        //Needs to have atleast one in inventory
        String[] request = {"Standing", "desk", "2"};
        Calculator test = new Calculator(request);
        ArrayList<Furniture> input = new ArrayList<Furniture>();
        Furniture furniture1 = new Furniture(request[0], request[1], "F003", true, false, true, false, 123, "001");
        Furniture furniture2 = new Furniture(request[0], request[1], "F004", false, true, true, false, 123, "002");
        Furniture furniture3 = new Furniture(request[0], request[1], "F005", false, true, false, false, 123, "003");
        input.add(furniture1);
        input.add(furniture2);
        input.add(furniture3);
        test.currentFurniture = input;
        boolean expected = false;
        assertTrue("ifFilled2 Test failed", test.ifFilled() == expected);
    }
    /**
     * Tests the ifFilled function
     * Tests the ifFilled function with a combination that should be false, where the currentFurntiure combination does not meet the client order requirements
     * In this example, the furntiure combination of furniture1, furniture2, furniture3 does not meet the client order requirements because
     * there is no atleast 10 booleans. (Boolean 1 has 1, Boolean 2 has 2 and Boolean 3 has 2 (boolean 4 is not needed, since this is a desk))
     */
    @Test
    public void testifFilled4()
    {
        //Needs to have atleast one in inventory
        String[] request = {"Standing", "desk", "10"};
        Calculator test = new Calculator(request);
        ArrayList<Furniture> input = new ArrayList<Furniture>();
        Furniture furniture1 = new Furniture(request[0], request[1], "F003", true, false, true, false, 123, "001");
        Furniture furniture2 = new Furniture(request[0], request[1], "F004", false, true, true, false, 123, "002");
        Furniture furniture3 = new Furniture(request[0], request[1], "F005", false, true, false, false, 123, "003");
        input.add(furniture1);
        input.add(furniture2);
        input.add(furniture3);
        test.currentFurniture = input;
        boolean expected = false;
        assertTrue("ifFilled3 Test failed", test.ifFilled() == expected);
    }
    /**
     * Tests the priceCalculator function and to see if it false, given that the parts in the inventory meet the client order.
     * In this example and with the sql file given to us, 2 Kneeling chairs was not able to be built 
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
     * Tests the priceCalculator function and to see if it true, given that the parts in the inventory meet the client order.
     * In this example and with the sql file given to us, 1 Exective chair was able to be built 
     */
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
