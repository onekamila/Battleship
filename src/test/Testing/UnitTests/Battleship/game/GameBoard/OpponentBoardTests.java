package Testing.UnitTests.Battleship.game.GameBoard;

import Battleship.game.GameBoard.Coordinate;
import Testing.TestingConstants;
import Battleship.game.GameBoard.Board;
import Battleship.game.GameBoard.OpponentBoard;
import Battleship.game.GameBoard.Square;
import Battleship.game.Result;
import Battleship.game.Ships.Battleship;
import Battleship.game.Ships.Fleet;
import Battleship.game.Ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpponentBoardTests
{
    private static final String BLANK_BOARD = "     A B C D E F G H I J \n  1  - - - - - - - - - - \n  2  - - - - - - - - - - \n  3  - - - - - - - - - - \n  4  - - - - - - - - - - \n  5  - - - - - - - - - - \n  6  - - - - - - - - - - \n  7  - - - - - - - - - - \n  8  - - - - - - - - - - \n  9  - - - - - - - - - - \n 10  - - - - - - - - - - ";
    private static final int[][][] FLEET_POSITIONS = TestingConstants.FLEET1_POSITIONS;
    private static final Coordinate TEST_COORD1 = new Coordinate(1, 1);
    private static final Coordinate TEST_COORD2 = new Coordinate(1, 4);
    
    private Random random;
    
    private Board testBoard;
    private OpponentBoard testOppBoard;
    private Ship testShip;
    private Fleet testFleet;
    private ShipSquare[] shipSquares;
    
    
    @BeforeEach
    public void init()
    {
        random = new Random();
        
        testBoard = new Board();
        testOppBoard = new OpponentBoard(testBoard);
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
        Square[][] testSquares = testOppBoard.getSquares();
        
        // No squares with any ships
        int i = 0;
        int j;
        for(Square[] testRow: testSquares)
        {
            j = 0;
            for(Square testSquare: testRow)
            {
                assertNull(testSquare.getShip());
                assertTrue(testOppBoard.checkAvailable(i, j));
                j++;
            }
            i++;
        }
        
        // available still contains all 100 cells
        assertEquals(100, testOppBoard.getAvailable().size());
    }
    
    // Place a single Ship
    @Test
    public void placeOneShipHorizontal()
    {
        testBoard.placeShip(new Coordinate(1, 1), new Coordinate(1, 4), testShip);
    
        for(int i = 1; i < 5; i++)
        {
            assertTrue(testOppBoard.checkAvailable(1, i));
        }
        
        assertEquals(100, testOppBoard.getAvailable().size());
    }
    
    @Test
    public void placeOneShipVertical()
    {
        testBoard.placeShip(new Coordinate(1, 1), new Coordinate( 4, 1), testShip);
    
        for(int i = 1; i < 5; i++)
        {
            assertEquals(testShip, testBoard.getSquare(i, 1).getShip());
            assertTrue(testOppBoard.checkAvailable(i, 1));
        }
        
        assertEquals(100, testOppBoard.getAvailable().size());
    }
    
    // Move on empty square
    @Test
    public void moveMiss()
    {
        testOppBoard.move(TEST_COORD1);
        assertEquals(Result.MISS, testOppBoard.getSquare(1, 1).getResult());
        assertEquals(99, testOppBoard.getAvailable().size());
    }
    
    // Move on square with Ship
    @Test
    public void moveHit()
    {
        testBoard.placeShip(TEST_COORD1, TEST_COORD2, testShip);
        testOppBoard.move(TEST_COORD1);
        
        assertEquals(Result.HIT, testOppBoard.getSquare(1, 1).getResult());
        assertEquals(99, testOppBoard.getAvailable().size());
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
            Coordinate coord = shipSquare.coord;
            
            assertFalse(testBoard.checkAvailable(coord.x, coord.y));
        }
        
        assertEquals(100, testOppBoard.getAvailable().size());
    }
    
    // toString Test
    @Test
    public void correctString()
    {
        assertEquals(BLANK_BOARD, testOppBoard.toString());
    }
}