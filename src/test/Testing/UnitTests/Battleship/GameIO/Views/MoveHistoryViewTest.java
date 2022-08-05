package Testing.UnitTests.Battleship.GameIO.Views;


import Battleship.GameIO.Input;
import Battleship.GameIO.Views.MoveHistoryView;
import Battleship.game.GameBoard.Coordinate;
import Testing.TestingConstants;
import Battleship.game.Game;
import Battleship.game.GameBoard.Square;
import Battleship.game.Move;
import Battleship.game.MoveHistory;
import Battleship.game.Result;
import Battleship.game.Ships.Battleship;
import Battleship.game.Ships.Ship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoveHistoryViewTest
{
    private static final Coordinate FIRST_MOVE_SQUARE = new Coordinate("A1");
    private static final Coordinate SECOND_MOVE_SQUARE = new Coordinate("A2");
    
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
    private static final String TWENTY_FOUR_MOVES = TestingConstants.EXPECTED_TWENTY_FOUR_MOVES_HISTORY_VIEW;
    private static final String TWENTY_SEVEN_MOVES = TestingConstants.EXPECTED_TWENTY_SEVEN_MOVES_HISTORY_VIEW;
    private static final String TWENTY_EIGHT_MOVES = TestingConstants.EXPECTED_TWENTY_EIGHT_MOVES_HISTORY_VIEW;
    
    
    private Game testGame;
    private MoveHistory testHistory;
    private MoveHistoryView testView;
    private Square testSquare1;
    private Square testSquare2;
    private Square testSquare3;
    private Square testSquare4;
    private Ship testShip1;
    private Ship testShip2;
    
    
    @BeforeEach
    public void init()
    {
        testShip1 = new Battleship();
        testShip2 = new Battleship();
        testGame = new Game();
        testHistory = testGame.getHistory();
        testView = new MoveHistoryView(testGame);
        testSquare1 = new Square(FIRST_MOVE_SQUARE);
        testSquare2 = new Square(SECOND_MOVE_SQUARE);
        testSquare3 = new Square(FIRST_MOVE_SQUARE);
        testSquare3.setShip(testShip1);
        testSquare4 = new Square(SECOND_MOVE_SQUARE);
        testSquare4.setShip(testShip2);
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
        newMove(testSquare3, Result.HIT);
        assertEquals(FIRST_MOVE_HIT, testView.toString());
    }
    
    @Test
    public void firstMoveSunk()
    {
        newMove(testSquare3, Result.SUNK);
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
        newMove(testSquare3, Result.HIT);
        assertEquals(SECOND_MOVE_HIT, testView.toString());
    }
    
    @Test
    public void secondMoveSunk()
    {
        newMove(testSquare1, Result.MISS);
        newMove(testSquare3, Result.SUNK);
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
        newMove(testSquare4, Result.HIT);
        assertEquals(THIRD_MOVE_HIT, testView.toString());
    }
    
    @Test
    public void thirdMoveSunk()
    {
        newMove(testSquare1, Result.MISS);
        newMove(testSquare1, Result.MISS);
        newMove(testSquare4, Result.SUNK);
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
    
    // 24 Moves
    @Test
    public void twentyFourMoves()
    {
        generateMoves(24);
        assertEquals(TWENTY_FOUR_MOVES, testView.toString());
    }
    
    // 27 moves
    @Test
    public void twentySevenMoves()
    {
        generateMoves(27);
        assertEquals(TWENTY_SEVEN_MOVES, testView.toString());
    }
    
    // 28 moves
    @Test
    public void twentyEightMoves()
    {
        generateMoves(28);
        assertEquals(TWENTY_EIGHT_MOVES, testView.toString());
    }
    
    
    private void newMove(Square square, Result result)
    {
        if(result == Result.SUNK)
        {
            for(int i = 0; i < square.getShip().getLength() - 1; i++)
            {
                square.getShip().hit();
            }
        }
        
        square.move();
        Move move = new Move(square);
        testHistory.add(move);
    }
    
    private void generateMoves(int maxMoves)
    {
        for(int x = 0; x < 10; x++)
        {
            for(int y = 0; y < 10; y++)
            {
                if(testHistory.size() >= maxMoves)
                {
                    break;
                }
                Square newSquare = new Square(new Coordinate(x, y));
                
                newMove(newSquare, Result.MISS);
            }
        }
    }
    
    
    @AfterEach
    public void shutdown()
    {
        Input.getInstance().close();
    }
}