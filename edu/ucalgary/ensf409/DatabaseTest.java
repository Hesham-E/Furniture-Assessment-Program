package edu.ucalgary.ensf409;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.Arrays;

import org.junit.Test;


public class DatabaseTest
{
    @Test
    public void testConstructor ()
    {
        Database test = new Database("DBURL", "USERNAME", "PASSWORD");
        String[] recieved = {test.DBURL, test.USERNAME, test.PASSWORD};
        String[] expected = {"DBURL", "USERNAME", "PASSWORD"};

        assertTrue("Database members initialized incorrectly", Arrays.equals(recieved, expected));
    }

    @Test
    public void testConnection () throws SQLException
    {
        Database test = new Database("jdbc:mysql://localhost/INVENTORY", "scm", "ensf409");
        test.initializeConnection();
        assertTrue("Connection not properly initialized",  test.getConnect().isValid(1) == true);
    }

    @Test
    public void testFindUsedFurniture ()
    {
        Database test = new Database("jdbc:mysql://localhost/INVENTORY", "scm", "ensf409");
        test.initializeConnection();
        Furniture[] recieved = test.findUsedFurniture("kneeling", "chair", 2);
        Furniture[] expectedArr = {new Furniture("Kneeling", "chair", "C1320", true, false, false, false, 50, "002"),
                                   new Furniture("Kneeling", "chair", "C3819", false, false, true, false, 75, "005")};
        String[] expectedIDs = {"C1320", "C3819"};
        boolean status = true;
        for (int i = 0; i < expectedArr.length; i++)
        {
            if (!recieved[i].getID().equals(expectedIDs[i]))
            {
                status = false;
            }
        }
        assertTrue("Unexpected furniture items or information recieved", status);
    }

    @Test
    public void testRemoveFurniture () throws SQLException
    {
        Database test = new Database("jdbc:mysql://localhost/INVENTORY", "scm", "ensf409");
        test.initializeConnection();
        test.removeFurniture("chair", "C0914");

        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/INVENTORY", "scm", "ensf409");
        PreparedStatement statment = connect.prepareStatement("SELECT * FROM chair WHERE ID = 'C0914'");

        assertFalse("Delete operation unsuccessful", statment.executeQuery().next());
        statment.close();
    }

    @Test
    public void testPrintManufacturers ()
    {
        Database test = new Database("jdbc:mysql://localhost/INVENTORY", "scm", "ensf409");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(outputStream));

        test.initializeConnection();
        String[] manufacturer = {"005"};
        test.printManufacturers(manufacturer);

        System.out.flush();
        System.setOut(original);
        
        String expected = "     - For Fine Office Supplies in AB, call: 403-980-9876";
        assertTrue("Manufacturer not found and printed, or printed incorrectly", outputStream.toString().trim().equals(expected.trim()));
    }
}
