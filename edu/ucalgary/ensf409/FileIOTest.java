package edu.ucalgary.ensf409;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
}
