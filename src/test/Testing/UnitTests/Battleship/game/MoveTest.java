package Testing.UnitTests.Battleship.game;


import Battleship.game.GameBoard.Square;
import Battleship.game.Move;
import Battleship.game.Result;
import Battleship.game.Ships.Battleship;
import Battleship.game.Ships.Ship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoveTest
{
    private static final String TEST_COORD = "A1";
    private static final String TEST_HIT = "A1-HB";
    private static final String TEST_MISS = "A1-M";
    private static final String TEST_SUNK = "A1-SB";
    private static final String TEST_WIN = "A1-SB#";
    
    private Square testSquare;
    private Move testMove;
    private Ship testShip;
    
    
    @BeforeEach
    public void init()
    {
        testShip = new Battleship();
        testSquare = new Square(TEST_COORD);
        testSquare.setShip(testShip);
    }
    
    
    // Hit
    @Test
    public void hit()
    {
        testMove = new Move(testSquare, Result.HIT);
        
        assertEquals(testSquare, testMove.getSquare());
        assertEquals(TEST_COORD, testMove.getSquare().getPosition());
        assertEquals(TEST_HIT, testMove.toString());
    }
    
    // Miss
    @Test
    public void miss()
    {
        testMove = new Move(testSquare, Result.MISS);
    
        assertEquals(testSquare, testMove.getSquare());
        assertEquals(TEST_COORD, testMove.getSquare().getPosition());
        assertEquals(TEST_MISS, testMove.toString());
    }
    
    // Sunk
    @Test
    public void sunk()
    {
        testMove = new Move(testSquare, Result.SUNK);
    
        assertEquals(testSquare, testMove.getSquare());
        assertEquals(TEST_COORD, testMove.getSquare().getPosition());
        assertEquals(TEST_SUNK, testMove.toString());
    }
    
    
    // Winning Move
    @Test
    public void win()
    {
        testMove = new Move(testSquare, Result.SUNK);
        testMove.setWin();
    
        assertEquals(testSquare, testMove.getSquare());
        assertEquals(TEST_COORD, testMove.getSquare().getPosition());
        assertEquals(TEST_WIN, testMove.toString());
    }
}