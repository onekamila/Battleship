package Testing.UnitTests.GameIO;


import GameIO.Input;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class InputTest
{
    private static final String TEST_DATA_PATH = "src/test/resources/TestData/InputTestData.txt";
    private static final String TEST_INPUT_LINE1 = "Hello World!";
    private static final String TEST_INPUT_LINE2 = "This is a test!";
    private static final String TEST_INPUT_LINE3 = "Hello from InputTestData.txt!";
    
    private static FileInputStream testFile;
    
    private Input testInput;
    
    
    @BeforeEach
    public void init() throws FileNotFoundException
    {
        testFile = new FileInputStream(TEST_DATA_PATH);
        System.setIn(testFile);
        
        testInput = Input.getInstance();
    }
    
    @Test
    public void testReadFileLine()
    {
        String testData = testInput.readLine();
        assertEquals(TEST_INPUT_LINE1, testData);
    }
    
    @Test
    public void testReadFileLines()
    {
        String testData1 = testInput.readLine();
        String testData2 = testInput.readLine();
        String testData3 = testInput.readLine();
        
        assertEquals(TEST_INPUT_LINE1, testData1);
        assertEquals(TEST_INPUT_LINE2, testData2);
        assertEquals(TEST_INPUT_LINE3, testData3);
    }
    
    @AfterEach
    public void shutdown() throws IOException
    {
        testFile.close();
        testInput.close();
    }
}