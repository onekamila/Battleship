package Testing.UnitTests.GameIO.Validators;


import GameIO.Validators.PlacementValidator;
import Testing.TestingConstants;
import game.GameBoard.Board;
import game.Ships.AircraftCarrier;
import game.Ships.Ship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PlacementValidatorTest
{
    /* Question for Andrew:
    Would I need to include the tests from InputValidatorTest here as well?
    Or can I just leave them there and get away with just this these?
    (Either text me, or leave a follow-up comment. Whichever you decide)
     */
    
    private static final int[][][] PLACED_SHIP_POSITIONS = TestingConstants.PV_PLACED_SHIP_POSITIONS;
    private static final String[][] TEST_SHIP_POSITIONS = TestingConstants.PV_TEST_SHIP_POSITIONS;
    /**
     * 8 Initial ship positions
     *
     *
     * 1: A5-E5
     * 2:
     */
    
    private PlacementValidator validator;
    private Board testBoard;
    private Ship placedShip;
    private Ship testShip;
    
    
    @BeforeEach
    public void init()
    {
        testBoard = new Board();
        validator = new PlacementValidator(testBoard);
        placedShip = new AircraftCarrier();
        testShip = new AircraftCarrier();
    }
    
    private void setup(int position)
    {
        int[][] coords = PLACED_SHIP_POSITIONS[position];
        int[] start = coords[0];
        int[] end = coords[1];
        
        testBoard.placeShip(start[0], start[1], end[0], end[1], placedShip);
    }
    
    
    // Valid open (h)
    @Test
    public void validOpenHorizontalTopLeft()
    {
        assertValid(0);
    }
    
    @Test
    public void validOpenHorizontalTopRight()
    {
        assertValid(1);
    }
    
    @Test
    public void validOpenHorizontalBottomLeft()
    {
        assertValid(2);
    }
    
    @Test
    public void validOpenHorizontalBottomRight()
    {
        assertValid(3);
    }
    
    // Valid open (v)
    @Test
    public void validOpenVerticalTopLeft()
    {
        assertValid(4);
    }
    
    @Test
    public void validOpenVerticalTopRight()
    {
        assertValid(5);
    }
    
    @Test
    public void validOpenVerticalBottomLeft()
    {
        assertValid(6);
    }
    
    @Test
    public void validOpenVerticalBottomRight()
    {
        assertValid(7);
    }
    
    // Valid wrong order
    @Test
    public void validWrongOrder()
    {
        assertValid(8);
    }
    
    
    // Invalid too short (less than ship length)
    @Test
    public void invalidShortHorizontal()
    {
        assertInvalid(9);
    }
    
    @Test
    public void invalidShortVertical()
    {
        assertInvalid(10);
    }
    
    // Invalid too long (more than ship length)
    @Test
    public void invalidLongHorizontal()
    {
        assertInvalid(11);
    }
    
    @Test
    public void invalidLongVertical()
    {
        assertInvalid(12);
    }
    
    // Invalid diagonal
    @Test
    public void invalidDiagonal()
    {
        assertInvalid(13);
    }
    
    
    // Valid above (v, h)
    @Test
    public void ValidAportVH()
    {
        setup(3);
        
        for(int i = 0; i < placedShip.getLength(); i++)
        {
            char col = (char) ('F' + i);
            String start = col + "1";
            String end = col + "5";
            String[] coord = {start, end};
        
            assertValid(coord);
        }
    }
    
    // Valid in front of bow (v, h)
    @Test
    public void validAheadVH()
    {
        setup(1);
        
        for(int i = 0; i < testShip.getLength(); i++)
        {
            int startRow = 1 + i;
            int endRow = startRow + testShip.getLength() - 1;
            
            String start = "E" + startRow;
            String end = "E" + endRow;
            String[] coord = {start, end};
            
            assertValid(coord);
        }
    }
    
    // Valid behind stern (v, h)
    @Test
    public void ValidAsternVH()
    {
        setup(2);
        
        for(int i = 0; i < testShip.getLength(); i++)
        {
            int startRow = 1 + i;
            int endRow = startRow + testShip.getLength() - 1;
        
            String start = "F" + startRow;
            String end = "F" + endRow;
            String[] coord = {start, end};
        
            assertValid(coord);
        }
    }
    
    // Valid below (v, h)
    @Test
    public void validAstarboardVH()
    {
        setup(3);
        
        for(int i = 0; i < placedShip.getLength(); i++)
        {
            char col = (char) ('F' + i);
            String start = col + "1";
            String end = col + "5";
            String[] coord = {start, end};
        
            assertValid(coord);
        }
    }
    
    // Valid above (h, v)
    @Test
    public void validAportHV()
    {
        setup(6);
        
        for(int i = 0; i < placedShip.getLength(); i++)
        {
            int row = i + 1;
            
            String start = "A" + row;
            String end = "E" + row;
            String[] coord = {start, end};
            
            assertValid(coord);
        }
    }
    
    // Valid in front of bow (h, v)
    @Test
    public void validAheadHV()
    {
        setup(5);
        for(int i = 0; i < testShip.getLength(); i++)
        {
            int row = 1 + i;
            
            String start = "A" + row;
            String end = "E" + row;
            String[] coord = {start, end};
        
            assertValid(coord);
        }
    }
    
    // Valid behind stern (h, v)
    @Test
    public void validAsternHV()
    {
        setup(4);
        for(int i = 0; i < testShip.getLength(); i++)
        {
            int row = 6 + i;
        
            String start = "A" + row;
            String end = "E" + row;
            String[] coord = {start, end};
        
            assertValid(coord);
        }
    }
    
    // Valid below (h, v)
    @Test
    public void validAstarboard()
    {
        setup(4);
    
        for(int i = 0; i < placedShip.getLength(); i++)
        {
            int row = i + 1;
        
            String start = "F" + row;
            String end = "J" + row;
            String[] coord = {start, end};
        
            assertValid(coord);
        }
    }
    
    
    // Invalid overlap (v, h)
    @Test
    public void invalidOverlapHV()
    {
        setup(8);
        
        String start;
        String end;
        int endRow;
        for(int startRow = 1; startRow < 6; startRow++)
        {
            endRow = (startRow + testShip.getLength()) - 1;
            for(char col = 'C'; col < 'H'; col++)
            {
                start = col + Integer.toString(startRow);
                end = col + Integer.toString(endRow);
                String[] coords = {start, end};
                assertInvalid(coords);
            }
        }
    }
    
    // Invalid overlap (v, h)
    @Test
    public void invalidOverlapVH()
    {
        setup(9);
    
        String start;
        String end;
        char endCol;
        for(int row = 3; row < 8; row++)
        {
            for(char startCol = 'A'; startCol < 'E'; startCol++)
            {
                endCol = (char) (startCol + (testShip.getLength() - 1));
                start = startCol + Integer.toString(row);
                end = endCol + Integer.toString(row);
                String[] coords = {start, end};
                assertInvalid(coords);
            }
        }
    }
    
    // Invalid bow-stern overlap (h, h)
    @Test
    public void invalidBowSternOverlapHH()
    {
        setup(1);
        
        int row = 5;
        String start;
        String end;
        for(char startCol = 'B'; startCol < 'H'; startCol++)
        {
            char endCol = (char) (startCol + (testShip.getLength() - 1));
            start = startCol + Integer.toString(row);
            end = endCol + Integer.toString(row);
            String[] coords = {start, end};
            
            assertInvalid(coords);
        }
    }
    
    // Invalid stern-bow overlap (h, h)
    @Test
    public void invalidSternBowOverlapHH()
    {
        setup(0);
    
        int row = 5;
        String start;
        String end;
        for(char startCol = 'A'; startCol < 'E'; startCol++)
        {
            char endCol = (char) (startCol + (testShip.getLength() - 1));
            start = startCol + Integer.toString(row);
            end = endCol + Integer.toString(row);
            String[] coords = {start, end};
        
            assertInvalid(coords);
        }
    }
    
    // Invalid bow-stern overlap (v, v)
    @Test
    public void invalidBowSternOverlapVV()
    {
        setup(5);
        
        char col = 'E';
        int endRow;
        String start;
        String end;
        for(int startRow = 2; startRow < 7; startRow++)
        {
            endRow = startRow + (testShip.getLength() - 1);
            start = col + Integer.toString(startRow);
            end = col + Integer.toString(endRow);
            String[] coords = {start, end};
            
            assertInvalid(coords);
        }
    }
    
    // Invalid stern-bow overlap (v, v)
    @Test
    public void invalidSternBowOverlapVV()
    {
        setup(0);
    
        char col = 'E';
        int endRow;
        String start;
        String end;
        for(int startRow = 1; startRow < 6; startRow++)
        {
            endRow = startRow + (testShip.getLength() - 1);
            start = col + Integer.toString(startRow);
            end = col + Integer.toString(endRow);
            String[] coords = {start, end};
        
            assertInvalid(coords);
        }
    }
    
    
    // Helper functions
    
    private void assertValid(String[] coord)
    {
        assertTrue(validator.validate(coord[0], coord[1], testShip));
    }
    
    private void assertValid(int index)
    {
        assertValid(TEST_SHIP_POSITIONS[index]);
    }
    
    
    private void assertInvalid(String coord[])
    {
        assertFalse(validator.validate(coord[0], coord[1], testShip));
    }
    
    private void assertInvalid(int index)
    {
        assertInvalid(TEST_SHIP_POSITIONS[index]);
    }
}