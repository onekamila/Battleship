package Testing.UnitTests.Battleship.game;


import Battleship.game.GameBoard.Coordinate;
import Battleship.game.GameBoard.Square;
import Battleship.game.Move;
import Battleship.game.Ships.Battleship;
import Battleship.game.Ships.Ship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoveTest
{
    private static final Coordinate TEST_COORD = new Coordinate("A1");
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
        testSquare.move();
        testMove = new Move(testSquare);
        
        assertEquals(testSquare, testMove.getSquare());
        assertEquals(TEST_COORD.toString(), testMove.getSquare().getPosition());
        assertEquals(TEST_HIT, testMove.toString());
    }
    
    // Miss
    @Test
    public void miss()
    {
        testSquare.setShip(null);
        testSquare.move();
        testMove = new Move(testSquare);
    
        assertEquals(testSquare, testMove.getSquare());
        assertEquals(TEST_COORD.toString(), testMove.getSquare().getPosition());
        assertEquals(TEST_MISS, testMove.toString());
    }
    
    // Sunk
    @Test
    public void sunk()
    {
        testShip.hit();
        testShip.hit();
        testShip.hit();
        testSquare.move();
        testMove = new Move(testSquare);
    
        assertEquals(testSquare, testMove.getSquare());
        assertEquals(TEST_COORD.toString(), testMove.getSquare().getPosition());
        assertEquals(TEST_SUNK, testMove.toString());
    }
    
    
    // Winning Move
    @Test
    public void win()
    {
        testShip.hit();
        testShip.hit();
        testShip.hit();
        testSquare.move();
        testMove = new Move(testSquare);
        testMove.setWin();
    
        assertEquals(testSquare, testMove.getSquare());
        assertEquals(TEST_COORD.toString(), testMove.getSquare().getPosition());
        assertEquals(TEST_WIN, testMove.toString());
    }
}