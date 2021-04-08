package edu.ucalgary.ensf409;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

//NOT COMPLETED!!!
public class FileIOTest
{
    @Test
    public void testInputFetching () throws IOException
    {
        FileIO test = new FileIO();
        String[] recieved = test.inputFetching();
        String[] expected = {"Executive", "chair", "1"};

        assertTrue("Input recieved is different from expected", Arrays.equals(expected, recieved));
    }

    @Test
    public void testFormattedFormOutput () throws IOException
    {

    }
}
