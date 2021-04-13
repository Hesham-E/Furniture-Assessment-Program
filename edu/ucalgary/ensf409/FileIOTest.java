package edu.ucalgary.ensf409;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import javax.print.DocFlavor.READER;

import org.junit.Test;

//NOT COMPLETED!!!
public class FileIOTest
{
    @Test
    public void testInputFetching () throws IOException
    {
        FileIO test = new FileIO();
        String[] expected = {"Executive", "chair", "1"};
        System.out.println("You must type in this input to pass the test: " + expected[0] + " " + expected[1] + ", " + expected[2]);
        String[] recieved = test.inputFetching();
        assertTrue("Input recieved is different from expected -- You must type in: " + expected[0] + " " + expected[1] + ", " + expected[2] , Arrays.equals(expected, recieved));
    }
    @Test
    public void testInputFetchingInvalid() throws IOException
    {
        FileIO test = new FileIO();
        System.out.println("You must type in an invalid input to pass this test");
        String[] recieved = test.inputFetching();
        String[] expected = {"null"};
        assertTrue("Input recieved is different from expected -- You must type in a invalid input", Arrays.equals(expected, recieved));
    }
}

