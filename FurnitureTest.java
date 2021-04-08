package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class FurnitureTest
{
    @Test
    public void testConstructorOne ()
    {
        Furniture test = new Furniture("mesh", "chair", "1234", false, true, false, true, 100, "001");
        String[] expected = {"mesh", "chair", "1234", "false", "true", "false", "true", "100", "001"};
        String[] recieved = {test.getType(), test.category, test.getID(), test.getBool(0).toString(), test.getBool(1).toString(), test.getBool(2).toString(),test.getBool(3).toString(), Integer.toString(test.getPrice()), test.getManuID()};
        assertTrue("Constructor initalized object incorrectly", Arrays.equals(expected, recieved));
    }

    @Test
    public void testConstructorTwo ()
    {
        Furniture test = new Furniture("standing", "desk");
        String[] expected = {"standing", "desk", "false", "false", "false",  "-1", "null"};
        String[] recieved = {test.getType(), test.category, test.getBool(0).toString(), test.getBool(1).toString(), test.getBool(2).toString(), Integer.toString(test.getPrice()), test.getManuID()};
        assertTrue("Constructor initalized object incorrectly", Arrays.equals(expected, recieved));
    }

    @Test
    public void testIsFilled ()
    {
        Furniture test = new Furniture("mesh", "chair", "1234", false, true, false, true, 100, "001");
        boolean recieved = test.isFilled();
        boolean expected = false;
        assertTrue("isFilled() incorrectly detected a filled object.", expected == recieved);
    }
}
