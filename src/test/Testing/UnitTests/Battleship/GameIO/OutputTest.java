package Testing.UnitTests.Battleship.GameIO;

import Battleship.GameIO.Output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OutputTest
{
    private static final String TEST_OUTPUT = "Hello World!";
    private static final String EXPECTED_OUTPUT = "Hello World!\r\n";
    
    private ByteArrayOutputStream testOutStream;
    
    private Output testOutput;
    
    
    @BeforeEach
    public void init()
    {
        testOutStream = new ByteArrayOutputStream();
        PrintStream testStream = new PrintStream(testOutStream);
    
        System.setOut(testStream);
        testOutput = Output.getInstance();
    }
    
    @Test
    public void print()
    {
        testOutput.print(TEST_OUTPUT);
    
        String testOut = testOutStream.toString();
        assertEquals(TEST_OUTPUT, testOut);
    }
    
    @Test
    public void println()
    {
        testOutput.println(TEST_OUTPUT);
        
        String testOut = testOutStream.toString();
        assertEquals(EXPECTED_OUTPUT, testOut);
    }
}