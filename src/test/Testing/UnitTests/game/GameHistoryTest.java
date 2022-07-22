package Testing.UnitTests.game;

import game.GameBoard.Square;
import game.Move;
import game.MoveHistory;
import game.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameHistoryTest
{
    private static final String TEST_COORD1 = "A1";
    private static final String TEST_COORD2 = "B2";
    private static final String TEST_COORD3 = "C3";
    
    private Move testMove1;
    private Move testMove2;
    private Move testMove3;
    private MoveHistory testHistory;
    
    
    @BeforeEach
    public void init()
    {
        testHistory = new MoveHistory();
        testMove1 = new Move(new Square(TEST_COORD1), Result.HIT);
        testMove2 = new Move(new Square(TEST_COORD2), Result.MISS);
        testMove3 = new Move(new Square(TEST_COORD3), Result.SUNK);
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