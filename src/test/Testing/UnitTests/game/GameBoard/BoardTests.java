package Testing.UnitTests.game.GameBoard;


import game.GameBoard.Board;
import game.GameBoard.OpponentBoard;
import game.GameBoard.Square;
import game.Result;
import game.Ships.*;
import Testing.TestingConstants;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTests
{
    private static final String BLANK_BOARD = "     A B C D E F G H I J \n  1  - - - - - - - - - - \n  2  - - - - - - - - - - \n  3  - - - - - - - - - - \n  4  - - - - - - - - - - \n  5  - - - - - - - - - - \n  6  - - - - - - - - - - \n  7  - - - - - - - - - - \n  8  - - - - - - - - - - \n  9  - - - - - - - - - - \n 10  - - - - - - - - - - \n";
    private static final int[][][] FLEET_POSITIONS = TestingConstants.FLEET1_POSITIONS;
    
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
            int[] start = FLEET_POSITIONS[i][0];
            int[] end = FLEET_POSITIONS[i][1];
            
            if(start[0] == end[0])
                for(int col = start[1]; col <= end[1]; col++)
                {
                    int[] pos = {start[0], col};
                    shipSquares[j] = new ShipSquare(currentShip, pos);
                    j++;
                }
            else
                for(int row = start[0]; row <= end[0]; row++)
                {
                    int[] pos = {row, start[1]};
                    shipSquares[j] = new ShipSquare(currentShip, pos);
                    j++;
                }
            
            testBoard.placeShip(start[0], start[1], end[0], end[1], currentShip);
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
        testBoard.placeShip(1, 1, 1, 4, testShip);
        
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
        testBoard.placeShip(1, 1, 4, 1, testShip);
    
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
        assertEquals(Result.MISS, testBoard.move(1, 1).getResult());
    }
    
    // Move on square with Ship
    @Test
    public void moveHit()
    {
        testBoard.placeShip(1, 1, 1, 4, testShip);
        
        assertEquals(Result.HIT, testBoard.move(1, 1).getResult());
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
            int[] pos = shipSquare.position;
            
            assertEquals(expectedShip, testBoard.getSquare(pos[0], pos[1]).getShip());
            assertFalse(testBoard.checkAvailable(pos[0], pos[1]));
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
}