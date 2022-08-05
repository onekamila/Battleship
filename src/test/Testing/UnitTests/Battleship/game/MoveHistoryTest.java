package Testing.UnitTests.Battleship.game;


import Battleship.game.GameBoard.Coordinate;
import Battleship.game.GameBoard.Square;
import Battleship.game.Move;
import Battleship.game.MoveHistory;
import Battleship.game.Ships.Battleship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MoveHistoryTest
{
    private static final Coordinate TEST_COORD1 = new Coordinate("A1");
    private static final Coordinate TEST_COORD2 = new Coordinate("B2");
    private static final Coordinate TEST_COORD3 = new Coordinate("C3");
    
    
    private Square testSquare1;
    private Square testSquare2;
    private Square testSquare3;
    
    private Move testMove1;
    private Move testMove2;
    private Move testMove3;
    private MoveHistory testHistory;
    
    
    @BeforeEach
    public void init()
    {
        testHistory = new MoveHistory();
        
        testSquare1 = new Square(TEST_COORD1);
        testSquare2 = new Square(TEST_COORD2);
        testSquare3 = new Square(TEST_COORD3);
        
        testSquare1.setShip(new Battleship());
        testSquare3.setShip(new Battleship());
        
        testSquare1.move();
        testSquare2.move();
        testSquare3.getShip().hit();
        testSquare3.getShip().hit();
        testSquare3.getShip().hit();
        testSquare3.move();
        
        testMove1 = new Move(testSquare1);
        testMove2 = new Move(testSquare2);
        testMove3 = new Move(testSquare3);
    }
    
    
    @Test
    public void initiallyEmpty()
    {
        assertTrue(testHistory.isEmpty());
    }
    
    @Test
    public void addOneMove()
    {
        testHistory.add(testMove1);
        
        assertFalse(testHistory.isEmpty());
        assertEquals(1, testHistory.size());
        assertEquals(testMove1, testHistory.get(0));
    }
    
    @Test
    public void addAllMoves()
    {
        addMoves();
    
        assertFalse(testHistory.isEmpty());
        assertEquals(3, testHistory.size());
        assertEquals(testMove1, testHistory.get(0));
        assertEquals(testMove2, testHistory.get(1));
        assertEquals(testMove3, testHistory.get(2));
    }
    
    
    private void addMoves()
    {
        testHistory.add(testMove1);
        testHistory.add(testMove2);
        testHistory.add(testMove3);
    }
}