package edu.ucalgary.ensf409;

import static org.junit.Assert.assertTrue;
import java.io.*;
import java.util.Arrays;
import org.junit.Test;

public class FileIOTest
{
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
    @Test
    public void testInputFetchingInvalid() throws IOException
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
        String expected = "your order has been filled, below are the furniture items that have been ordered. ID: C7268, Price: 75, Manufacuturer ID: 004ID: C5784, Price: 150, Manufacuturer ID: 004ID: C2483, Price: 175, Manufacuturer ID: 002";
        expected = expected.replaceAll(" ", "").replaceAll("[\n\r]", "");
        String recieved = outputStream.toString().replaceAll(" ", "").replaceAll("[\n\r]", "");
        
        System.out.flush();
        System.setOut(original);

        assertTrue("Output printed to console was that not of a successful order", expected.equals(recieved));
    }
}

