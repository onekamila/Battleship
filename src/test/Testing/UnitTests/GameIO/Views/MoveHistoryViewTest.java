package Testing.UnitTests.GameIO.Views;


import GameIO.Views.MoveHistoryView;
import Testing.TestingConstants;
import game.GameBoard.Square;
import game.Move;
import game.MoveHistory;
import game.Result;
import game.Ships.Battleship;
import game.Ships.Ship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoveHistoryViewTest
{
    private static final String FIRST_MOVE_SQUARE = "A1";
    private static final String SECOND_MOVE_SQUARE = "A2";
    
    private static final String EMPTY_HISTORY = TestingConstants.EXPECTED_EMPTY_HISTORY_VIEW;
    private static final String FIRST_MOVE_MISS = TestingConstants.EXPECTED_FIRST_MOVE_MISS_HISTORY_VIEW;
    private static final String FIRST_MOVE_HIT = TestingConstants.EXPECTED_FIRST_MOVE_HIT_HISTORY_VIEW;
    private static final String FIRST_MOVE_SUNK = TestingConstants.EXPECTED_FIRST_MOVE_SUNK_HISTORY_VIEW;
    private static final String SECOND_MOVE_MISS = TestingConstants.EXPECTED_SECOND_MOVE_MISS_HISTORY_VIEW;
    private static final String SECOND_MOVE_HIT = TestingConstants.EXPECTED_SECOND_MOVE_HIT_HISTORY_VIEW;
    private static final String SECOND_MOVE_SUNK = TestingConstants.EXPECTED_SECOND_MOVE_SUNK_HISTORY_VIEW;
    private static final String THIRD_MOVE_MISS = TestingConstants.EXPECTED_THIRD_MOVE_MISS_HISTORY_VIEW;
    private static final String THIRD_MOVE_HIT = TestingConstants.EXPECTED_THIRD_MOVE_HIT_HISTORY_VIEW;
    private static final String THIRD_MOVE_SUNK = TestingConstants.EXPECTED_THIRD_MOVE_SUNK_HISTORY_VIEW;
    private static final String TWENTY_TWO_MOVES = TestingConstants.EXPECTED_TWENTY_TWO_MOVES_HISTORY_VIEW;
    private static final String TWENTY_THREE_MOVES = TestingConstants.EXPECTED_TWENTY_THREE_MOVES_HISTORY_VIEW;
    private static final String TWENTY_SEVEN_MOVES = TestingConstants.EXPECTED_TWENTY_SEVEN_MOVES_HISTORY_VIEW;
    
    
    private MoveHistory testHistory;
    private MoveHistoryView testView;
    private Square testSquare1;
    private Square testSquare2;
    private Ship testShip1;
    private Ship testShip2;
    
    
    @BeforeEach
    public void init()
    {
        testShip1 = new Battleship();
        testShip2 = new Battleship();
        testHistory = new MoveHistory();
        testView = new MoveHistoryView(testHistory);
        testSquare1 = new Square(FIRST_MOVE_SQUARE);
        testSquare1.setShip(testShip1);
        testSquare2 = new Square(SECOND_MOVE_SQUARE);
        testSquare2.setShip(testShip2);
    }
    
    // Initially empty
    @Test
    public void initiallyEmpty()
    {
        assertEquals(EMPTY_HISTORY, testView.toString());
    }
    
    // One move
    @Test
    public void firstMoveMiss()
    {
        newMove(testSquare1, Result.MISS);
        assertEquals(FIRST_MOVE_MISS, testView.toString());
    }
    
    @Test
    public void firstMoveHit()
    {
        newMove(testSquare1, Result.HIT);
        assertEquals(FIRST_MOVE_HIT, testView.toString());
    }
    
    @Test
    public void firstMoveSunk()
    {
        newMove(testSquare1, Result.SUNK);
        assertEquals(FIRST_MOVE_SUNK, testView.toString());
    }
    
    // Two moves
    @Test
    public void secondMoveMiss()
    {
        newMove(testSquare1, Result.MISS);
        newMove(testSquare1, Result.MISS);
        assertEquals(SECOND_MOVE_MISS, testView.toString());
    }
    
    @Test
    public void secondMoveHit()
    {
        newMove(testSquare1, Result.MISS);
        newMove(testSquare1, Result.HIT);
        assertEquals(SECOND_MOVE_HIT, testView.toString());
    }
    
    @Test
    public void secondMoveSunk()
    {
        newMove(testSquare1, Result.MISS);
        newMove(testSquare1, Result.SUNK);
        assertEquals(SECOND_MOVE_SUNK, testView.toString());
    }
    
    // Third move (new line)
    @Test
    public void thirdMoveMiss()
    {
        newMove(testSquare1, Result.MISS);
        newMove(testSquare1, Result.MISS);
        newMove(testSquare2, Result.MISS);
        assertEquals(THIRD_MOVE_MISS, testView.toString());
    }
    
    @Test
    public void thirdMoveHit()
    {
        newMove(testSquare1, Result.MISS);
        newMove(testSquare1, Result.MISS);
        newMove(testSquare2, Result.HIT);
        assertEquals(THIRD_MOVE_HIT, testView.toString());
    }
    
    @Test
    public void thirdMoveSunk()
    {
        newMove(testSquare1, Result.MISS);
        newMove(testSquare1, Result.MISS);
        newMove(testSquare2, Result.SUNK);
        assertEquals(THIRD_MOVE_SUNK, testView.toString());
    }
    
    // 22 moves
    @Test
    public void twentyTwoMoves()
    {
        generateMoves(22);
        assertEquals(TWENTY_TWO_MOVES, testView.toString());
    }
    
    // 23 Moves
    @Test
    public void twentyThreeMoves()
    {
        generateMoves(23);
        assertEquals(TWENTY_THREE_MOVES, testView.toString());
    }
    
    // 27 moves
    @Test
    public void twentySevenMoves()
    {
        generateMoves(27);
        assertEquals(TWENTY_SEVEN_MOVES, testView.toString());
    }
    
    
    private void newMove(Square square, Result result)
    {
        Move move = new Move(square, result);
        testHistory.add(move);
    }
    
    private void generateMoves(int maxMoves)
    {
        for(int r = 1; r < 11; r++)
        {
            for(char c = 'A'; c < 'K'; c++)
            {
                if(testHistory.size() >= maxMoves)
                {
                    break;
                }
                String squareCoord = c + Integer.toString(r);
                Square newSquare = new Square(squareCoord);
                
                newMove(newSquare, Result.MISS);
            }
        }
    }
}