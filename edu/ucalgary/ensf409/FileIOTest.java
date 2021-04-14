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
     * testFormattedFormOutput() method
     * Tests how formattedFormOutput() prints to the console after user input
     * In this test, the output is known based on the program's algorithim
     * THIS TEST DEPENDS ON THE SPECIFIC CALCULATOR ALGORITHM AND SPECIFIC DATABASE
     * PROVIDED. IF THESE TWO THINGS CHANGE THE TEST WILL FAIL
     */
    @Test
    public void testFormattedFormOutput() throws IOException
    {
        FileIO test = new FileIO();
        String[] request = {"Executive", "chair", "1"};
        Calculator calc = new Calculator(request);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(outputStream));

        test.formattedFormOutput(calc, request);
        String expected = "yourorderhasbeenfilled,belowarethefurnitureitemsthathavebeenordered.ID:C2483,Price:175,ManufacuturerID:002ID:C5784,Price:150,ManufacuturerID:004ID:C7268,Price:75,ManufacuturerID:004";
        expected = expected.replaceAll(" ", "").replaceAll("[\n\r]", "");
        String recieved = outputStream.toString().replaceAll(" ", "").replaceAll("[\n\r]", "");
        
        System.out.flush();
        System.setOut(original);

        assertTrue("Output printed to console was that not of a successful order", expected.equals(recieved));
    }

     /**
     * testFormattedFormOutput2() method
     * Tests how formattedFormOutput() prints to the console after user input
     * This time the test requests mutliple items and weird capitalization
     * Since the specific form was checked in the last test, this time
     * we only look for the IDs
     */
    @Test
    public void testFormattedFormOutput2() throws IOException
    {
        FileIO test = new FileIO();
        String[] request = {"tRaDiTiOnAl", "DESK", "2"};
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
     * testFormattedFormOutput3() method
     * Tests if user recieved the lowest price
     */
    @Test
    public void testFormattedFormOutput3() throws IOException
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

