package Testing.UnitTests.GameIO;

import GameIO.CoordinateParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinateParserTest
{
    private static final String TEST_COORDINATE1 = "A1";
    private static final String TEST_COORDINATE2 = "D5";
    private static final int[] EXPECTED_COORDINATE1 = {0, 0};
    private static final int[] EXPECTED_COORDINATE2 = {4, 3};
    
    
    @Test
    public void parseCoordinate1()
    {
        int[] testCoord = CoordinateParser.parse(TEST_COORDINATE1);
        assertEquals(EXPECTED_COORDINATE1[0], testCoord[0]);
        assertEquals(EXPECTED_COORDINATE1[1], testCoord[1]);
    }
    
    @Test
    public void parseCoordinate2()
    {
        int[] testCoord = CoordinateParser.parse(TEST_COORDINATE2);
        assertEquals(EXPECTED_COORDINATE2[0], testCoord[0]);
        assertEquals(EXPECTED_COORDINATE2[1], testCoord[1]);
    }
}