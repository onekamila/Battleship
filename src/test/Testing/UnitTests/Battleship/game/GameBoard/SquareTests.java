package Testing.UnitTests.Battleship.game.GameBoard;


import Battleship.game.GameBoard.Square;
import Battleship.game.Result;
import Battleship.game.Ships.Battleship;
import Battleship.game.Ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class SquareTests
{
    private static final String TEST_POSITION = "A1";
    private Square testSquare;
    private Ship testShip;
    
    
    @BeforeEach
    public void init()
    {
        testSquare = new Square(TEST_POSITION);
        testShip = new Battleship();
    }
    
    
    // Correct position
    @Test
    public void correctPosition()
    {
        assertEquals(TEST_POSITION, testSquare.getPosition());
    }
    
    // Initially empty
    @Test
    public void squareInitiallyEmpty()
    {
        assertNull(testSquare.getShip());
    }
    
    // Initially open
    @Test
    public void squareInitiallyOpen()
    {
        assertNull(testSquare.getResult());
    }
    
    // Move without Ship
    @Test
    public void moveNoShip()
    {
        assertEquals(Result.MISS, testSquare.move());
    }
    
    // Place a ship
    @Test
    public void placeShip()
    {
        testSquare.setShip(testShip);
        assertEquals(testShip, testSquare.getShip());
    }
    
    // Move with Ship (hit)
    @Test
    public void moveWithShipHit()
    {
        testSquare.setShip(testShip);
        assertEquals(Result.HIT, testSquare.move());
    }
    
    // Move with Ship (sunk)
    @Test
    public void moveWithShipSunk()
    {
        testShip.hit();
        testShip.hit();
        testShip.hit();
        
        testSquare.setShip(testShip);
        assertEquals(Result.SUNK, testSquare.move());
    }
    
    // Valid open string
    @Test
    public void validOpenString()
    {
        assertEquals("-", testSquare.toString());
    }
    
    // Valid miss string
    @Test
    public void validMissString()
    {
        testSquare.move();
        assertEquals(Result.MISS.toString(), testSquare.toString());
    }
    
    // Valid hit string
    @Test
    public void validHitString()
    {
        testSquare.setShip(testShip);
        testSquare.move();
        assertEquals(Result.HIT.toString(), testSquare.toString());
    }
    
    // Valid ship string
    @Test
    public void validShipString()
    {
        testSquare.setShip(testShip);
        assertEquals(testShip.toString(), testSquare.toString());
    }
    
    // Valid reset empty
    @Test
    public void validEmptyReset()
    {
        assertNull(testSquare.getResult());
        testSquare.move();
        assertEquals(Result.MISS, testSquare.getResult());
        testSquare.reset();
        assertNull(testSquare.getResult());
    }
    
    // Valid reset with ship
    @Test
    public void validShipReset()
    {
        testSquare.setShip(testShip);
        assertEquals(testSquare.toString(), testShip.toString());
        testSquare.move();
        assertEquals(testSquare.getResult(), Result.HIT);
        testSquare.reset();
        assertEquals(testSquare.toString(), testShip.toString());
    }
}