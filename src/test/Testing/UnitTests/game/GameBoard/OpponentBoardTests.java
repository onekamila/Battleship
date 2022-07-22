package Testing.UnitTests.game.GameBoard;

import Testing.TestingConstants;
import game.GameBoard.Board;
import game.GameBoard.OpponentBoard;
import game.GameBoard.Square;
import game.Result;
import game.Ships.Battleship;
import game.Ships.Fleet;
import game.Ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpponentBoardTests
{
    private static final String BLANK_BOARD = "     A B C D E F G H I J \n  1  - - - - - - - - - - \n  2  - - - - - - - - - - \n  3  - - - - - - - - - - \n  4  - - - - - - - - - - \n  5  - - - - - - - - - - \n  6  - - - - - - - - - - \n  7  - - - - - - - - - - \n  8  - - - - - - - - - - \n  9  - - - - - - - - - - \n 10  - - - - - - - - - - ";
    private static final int[][][] FLEET_POSITIONS = TestingConstants.FLEET1_POSITIONS;
    
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
        testBoard.placeShip(1, 1, 1, 4, testShip);
        
        for(int i = 1; i < 5; i++)
        {
            //assertEquals(testShip, testBoard.getSquare(1, i).getShip());
            assertTrue(testOppBoard.checkAvailable(1, i));
        }
        
        assertEquals(100, testOppBoard.getAvailable().size());
    }
    
    @Test
    public void placeOneShipVertical()
    {
        testBoard.placeShip(1, 1, 4, 1, testShip);
        
        for(int i = 1; i < 5; i++)
        {
            //assertEquals(testShip, testBoard.getSquare(i, 1).getShip());
            assertTrue(testOppBoard.checkAvailable(i, 1));
        }
        
        assertEquals(100, testOppBoard.getAvailable().size());
    }
    
    // Move on empty square
    @Test
    public void moveMiss()
    {
        testOppBoard.move(1, 1);
        assertEquals(Result.MISS, testOppBoard.getSquare(1, 1).getResult());
        assertEquals(99, testOppBoard.getAvailable().size());
    }
    
    // Move on square with Ship
    @Test
    public void moveHit()
    {
        testBoard.placeShip(1, 1, 1, 4, testShip);
        testOppBoard.move(1, 1);
        
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
            int[] pos = shipSquare.position;
            
            assertFalse(testBoard.checkAvailable(pos[0], pos[1]));
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