package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * FurnitureTest.java for ENSF409 final project W2021
 * Unit tests for Furniture.java class
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @author Faisal Hossain <a href="mailto:faisal.hossain1@ucalgary.ca">faisal.hossain1@ucalgary.ca</a>
 * @author Hesham Elkaliouby <a href="mailto:hesham.elkaliouby@ucalgary.ca">hesham.elkaliouby@ucalgary.ca</a>
 * @author Dagvadorj Altankhuyag <a href="mailto:dagvadorj.altankhuya@ucalgary.ca">dagvadorj.altankhuya@ucalgary.ca</a>
 * @version 1.2
 * @since 1.0
 */
public class FurnitureTest
{
    /**
     * testConstructorOne() method
     * Tests the first constructor to see if members are initalized correctly
     */
    @Test
    public void testConstructorOne ()
    {
        Furniture test = new Furniture("mesh", "chair", "1234", false, true, false, true, 100, "001");
        String[] expected = {"mesh", "chair", "1234", "false", "true", "false", "true", "100", "001"};
        String[] recieved = {test.getType(), test.category, test.getID(), test.getBool(0).toString(), test.getBool(1).toString(), test.getBool(2).toString(),test.getBool(3).toString(), Integer.toString(test.getPrice()), test.getManuID()};
        assertTrue("Constructor initalized object incorrectly", Arrays.equals(expected, recieved));
    }

    /**
     * testConstructorTwo() method
     * Tests the second constructor to see if members are initalized correctly
     */
    @Test
    public void testConstructorTwo ()
    {
        Furniture test = new Furniture("standing", "desk");
        String[] expected = {"standing", "desk", "false", "false", "false",  "-1", "null"};
        String[] recieved = {test.getType(), test.category, test.getBool(0).toString(), test.getBool(1).toString(), test.getBool(2).toString(), Integer.toString(test.getPrice()), test.getManuID()};
        assertTrue("Constructor initalized object incorrectly", Arrays.equals(expected, recieved));
    }

    /**
     * isFilled() method
     * Tests isFilled() to see how it recognizes a furniture item with missing parts
     */
    @Test
    public void testIsFilled ()
    {
        Furniture test = new Furniture("traditional", "desk", "1234", false, true, false, true, 100, "001");
        boolean recieved = test.isFilled();
        boolean expected = false;
        assertTrue("isFilled() incorrectly detected a filled object.", expected == recieved);
    }

    /**
     * testgetBool0() method
     * Tests testgetBool0() to see if it returns the correct part status
     */
    @Test
    public void testgetBool0()
    {
        Furniture test = new Furniture("mesh", "chair", "999", true, true, false, true, 100, "001");
        boolean recieved = test.getBool(0);
        boolean expected = true;
        assertTrue("getBool() incorrectly detected a non-filled object.", expected == recieved);
    }

     /**
     * testgetBool1() method
     * Tests testgetBool1() to see if it returns the correct part status
     */
    @Test
    public void testgetBool1()
    {
        Furniture test = new Furniture("mesh", "chair", "999", true, true, false, true, 100, "001");
        boolean recieved = test.getBool(1);
        boolean expected = true;
        assertTrue("getBool() incorrectly detected a non-filled object.", expected == recieved);
    }

     /**
     * testgetBool2() method
     * Tests testgetBool2() to see if it returns the correct part status
     */
    @Test
    public void testgetBool2()
    {
        Furniture test = new Furniture("mesh", "chair", "999", true, true, false, true, 100, "001");
        boolean recieved = test.getBool(2);
        boolean expected = false;
        assertTrue("getBool() incorrectly detected a filled object.", expected == recieved);
    }

     /**
     * testgetBool3() method
     * Tests testgetBool3() to see if it returns the correct part status
     */
    @Test
    public void testgetBool3()
    {
        Furniture test = new Furniture("mesh", "chair", "999", true, true, false, true, 100, "001");
        boolean recieved = test.getBool(3);
        boolean expected = true;
        assertTrue("getBool() incorrectly detected a non-filled object.", expected == recieved);
    }
}
