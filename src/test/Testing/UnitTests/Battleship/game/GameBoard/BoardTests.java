package Testing.UnitTests.Battleship.game.GameBoard;


import Battleship.game.GameBoard.Board;
import Battleship.game.GameBoard.Coordinate;
import Battleship.game.GameBoard.OpponentBoard;
import Battleship.game.GameBoard.Square;
import Battleship.game.Result;
import Battleship.game.Ships.*;
import Testing.TestingConstants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTests
{
    private static final String BLANK_BOARD = "     A B C D E F G H I J \n  1  - - - - - - - - - - \n  2  - - - - - - - - - - \n  3  - - - - - - - - - - \n  4  - - - - - - - - - - \n  5  - - - - - - - - - - \n  6  - - - - - - - - - - \n  7  - - - - - - - - - - \n  8  - - - - - - - - - - \n  9  - - - - - - - - - - \n 10  - - - - - - - - - - \n";
    private static final int[][][] FLEET_POSITIONS = TestingConstants.FLEET1_POSITIONS;
    private static final Coordinate TEST_COORD1 = new Coordinate(1, 1);
    private static final Coordinate TEST_COORD2 = new Coordinate(1, 4);
    
    private Random random;
    
    private Board testBoard;
    private Ship testShip;
    private Fleet testFleet;
    private ShipSquare[] shipSquares;
    
    
    @BeforeEach
    public void init()
    {
        random = new Random();
        
        testBoard = new Board();
        testShip = new Battleship();
        testFleet = new Fleet();
    }
    
    
    private void placeFleet()
    {
        shipSquares = new ShipSquare[27];
        
        int j = 0;
        for(int i = 0; i < 9; i++)
        {
            Ship currentShip = testFleet.get(i);
            Coordinate start = new Coordinate(FLEET_POSITIONS[i][0][0], FLEET_POSITIONS[i][0][1]);
            Coordinate end = new Coordinate(FLEET_POSITIONS[i][1][0], FLEET_POSITIONS[i][1][1]);
            
            if(start.x == end.x)
            {
                for (int col = start.y; col <= end.y; col++)
                {
                    Coordinate coord = new Coordinate(start.x, col);
                    shipSquares[j] = new ShipSquare(currentShip, coord);
                    j++;
                }
            }
            else
            {
                for (int row = start.x; row <= end.x; row++)
                {
                    Coordinate coord = new Coordinate(row, start.y);
                    shipSquares[j] = new ShipSquare(currentShip, coord);
                    j++;
                }
            }
            
            testBoard.placeShip(start, end, currentShip);
        }
    }
    
    // Empty Initial Board
    @Test
    public void initiallyEmpty()
    {
        Square[][] testSquares = testBoard.getSquares();
        
        // No squares with any ships
        int i = 0;
        int j;
        for(Square[] testRow: testSquares)
        {
            j = 0;
            for(Square testSquare: testRow)
            {
                assertNull(testSquare.getShip());
                assertTrue(testBoard.checkAvailable(i, j));
                j++;
            }
            i++;
        }
        
        // available still contains all 100 cells
        assertEquals(100, testBoard.getAvailable().size());
    }
    
    // Place a single Ship
    @Test
    public void placeOneShipHorizontal()
    {
        testBoard.placeShip(new Coordinate(1, 1), new Coordinate(1, 4), testShip);
        
        for(int i = 1; i < 5; i++)
        {
            assertEquals(testShip, testBoard.getSquare(1, i).getShip());
            assertFalse(testBoard.checkAvailable(1, i));
        }
        
        assertEquals(96, testBoard.getAvailable().size());
    }
    
    @Test
    public void placeOneShipVertical()
    {
        testBoard.placeShip(new Coordinate(1, 1), new Coordinate(4, 1), testShip);
    
        for(int i = 1; i < 5; i++)
        {
            assertEquals(testShip, testBoard.getSquare(i, 1).getShip());
            assertFalse(testBoard.checkAvailable(i, 1));
        }
    
        assertEquals(96, testBoard.getAvailable().size());
    }
    
    // Move on empty square
    @Test
    public void moveMiss()
    {
        assertEquals(Result.MISS, testBoard.move(TEST_COORD1).getResult());
    }
    
    // Move on square with Ship
    @Test
    public void moveHit()
    {
        testBoard.placeShip(TEST_COORD1, TEST_COORD2, testShip);
    
        assertEquals(Result.HIT, testBoard.move(TEST_COORD1).getResult());
    }
    
    // Place entire Fleet
    @Test
    public void placeEntireFleet()
    {
        placeFleet();
        
        for(int i = 0; i < 27; i++)
        {
            int index = random.nextInt(27);
            ShipSquare shipSquare = shipSquares[index];
            Ship expectedShip = shipSquare.ship;
            Coordinate coord = shipSquare.coord;
            
            assertEquals(expectedShip, testBoard.getSquare(coord.x, coord.y).getShip());
            assertFalse(testBoard.checkAvailable(coord.x, coord.y));
        }
        
        assertEquals(73, testBoard.getAvailable().size());
    }
    
    // toString Test
    @Test
    public void correctString()
    {
        assertEquals(BLANK_BOARD, testBoard.toString());
    }
    
    // equals Test
    @Test
    public void validEquals()
    {
        Board newBoard = new OpponentBoard(testBoard);
        assertTrue(newBoard.equals(testBoard));
    }
    
    @Test
    public void invalidEquals()
    {
        Board newBoard = new Board();
        assertFalse(newBoard.equals(testBoard));
    }
    
    
    @Test
    public void resetSquare()
    {
        assertNull(testBoard.getSquare(0, 0).getResult());
        testBoard.move(new Coordinate(0, 0));
        assertEquals(Result.MISS, testBoard.getSquare(0, 0).getResult());
        assertEquals(99, testBoard.getAvailable().size());
        testBoard.resetSquare(new Coordinate(0, 0));
        assertNull(testBoard.getSquare(0, 0).getResult());
        assertEquals(100, testBoard.getAvailable().size());
    }
}