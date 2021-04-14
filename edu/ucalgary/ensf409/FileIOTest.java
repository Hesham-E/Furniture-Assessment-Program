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
     * testFormattedFormOutput1() method
     * Tests how formattedFormOutput() prints to the console after user input
     * The specific form of the output changes 
     */
    @Test
    public void testFormattedFormOutput1() throws IOException
    {
        FileIO test = new FileIO();
        String[] request = {"traditional", "DESK", "2"};
        Calculator calc = new Calculator(request);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(outputStream));

        test.formattedFormOutput(calc, request);
        String recieved = outputStream.toString();
        
        System.out.flush();
        System.setOut(original);

        assertTrue("Output printed to console was that not of a successful order", recieved.contains("D8675") && recieved.contains("D4231") && recieved.contains("D9352"));
    }

     /**
     * testFormattedFormOutput2() method
     * Tests if user recieved the lowest price
     */
    @Test
    public void testFormattedFormOutput2() throws IOException
    {
        FileIO test = new FileIO();
        String[] request = {"Adjustable", "DESK", "2"};
        Calculator calc = new Calculator(request);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(outputStream));

        test.formattedFormOutput(calc, request);
        
        System.out.flush();
        System.setOut(original);

        assertTrue("Output printed to console was that not of a successful order", calc.pricesTotal.get(calc.pricesTotal.size() - 1) == 800);
    }
}

