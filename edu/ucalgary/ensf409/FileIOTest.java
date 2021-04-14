package edu.ucalgary.ensf409;

import static org.junit.Assert.assertTrue;
import java.io.*;
import java.util.Arrays;
import org.junit.Test;

/**
 * FileIOTest.java for ENSF409 final project W2021
 * Unit tests for FileIO.java class
 * @author Josh Vanderstoop <a href="mailto:joshua.vanderstoop@ucalgary.ca">joshua.vanderstoop@ucalgary.ca</a>
 * @author Faisal Hossain <a href="mailto:faisal.hossain1@ucalgary.ca">faisal.hossain1@ucalgary.ca</a>
 * @author Hesham Elkaliouby <a href="mailto:hesham.elkaliouby@ucalgary.ca">hesham.elkaliouby@ucalgary.ca</a>
 * @author Dagvadorj Altankhuyag <a href="mailto:dagvadorj.altankhuya@ucalgary.ca">dagvadorj.altankhuya@ucalgary.ca</a>
 * @version 1.2
 * @since 1.0
 */
public class FileIOTest
{
    /**
     * testInputFetching() method
     * Tests how inputFetching() splits user input
     */
    @Test
    public void testInputFetching () throws IOException
    {
        FileIO test = new FileIO();

        ByteArrayInputStream inputStream = new ByteArrayInputStream("Executive chair, 1".getBytes());
        InputStream original = System.in;
        System.setIn(inputStream);

        String[] recieved = test.inputFetching();
        String[] expected = {"Executive", "chair", "1"};

        System.setIn(original);
        assertTrue("Input recieved is different from expected", Arrays.equals(expected, recieved));
    }

    /**
     * testInputFetching() method
     * Tests how inputFetching() splits user input with different capitalization
     */
    @Test
    public void testInputFetching2() throws IOException
    {
        FileIO test = new FileIO();

        ByteArrayInputStream inputStream = new ByteArrayInputStream("mesh chair, 10".getBytes());
        InputStream original = System.in;
        System.setIn(inputStream);

        String[] recieved = test.inputFetching();
        String[] expected = {"mesh", "chair", "10"};

        System.setIn(original);
        assertTrue("Input recieved is different from expected", Arrays.equals(expected, recieved));
    }
    /**
     * testInputFetching() method
     * Tests how inputFetching() handles user input with a similar input to the valid input, except the category is not valid.
     */
    @Test
    public void testInputFetching3() throws IOException
    {
        FileIO test = new FileIO();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("mesh wheel, 10".getBytes());
        InputStream original = System.in;
        System.setIn(inputStream);

        String[] recieved = test.inputFetching();
        String[] expected = {"null2", "wheel"};

        System.setIn(original);
        assertTrue("Input recieved is different from expected", Arrays.equals(expected, recieved));
    }
    /**
     * testInputFetching() method
     * Tests how inputFetching() handles user input with a completely invalid input
     */
    @Test
    public void testInputFetching4() throws IOException
    {
        FileIO test = new FileIO();

        ByteArrayInputStream inputStream = new ByteArrayInputStream("mmmmmm".getBytes());
        InputStream original = System.in;
        System.setIn(inputStream);

        String[] recieved = test.inputFetching();
        String[] expected = {"null"};

        System.setIn(original);
        assertTrue("Input recieved is different from expected", Arrays.equals(expected, recieved));
    }
}

