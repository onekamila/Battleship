package Testing.UnitTests.GameIO.Views;


import GameIO.Views.BoardView;
import Testing.TestingConstants;
import game.GameBoard.Board;
import game.GameBoard.OpponentBoard;
import game.Ships.Fleet;
import game.Ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardViewTest
{
    private static final int[][][] FLEET1_POSITIONS = TestingConstants.FLEET1_POSITIONS;
    private static final int[][][] FLEET2_POSITIONS = TestingConstants.FLEET2_POSITIONS;
    private static final int[][][] FLEET1_COORDS = TestingConstants.FLEET1_COORDS;
    private static final int[][][] FLEET2_COORDS = TestingConstants.FLEET2_COORDS;
    private static final int[][] HIT_COORDS1 = {FLEET1_COORDS[0][0], FLEET1_COORDS[0][1]};
    private static final int[][] HIT_COORDS2 = {FLEET2_COORDS[0][0], FLEET2_COORDS[0][1]};
    private static final int[][] MISS_COORDS1 = {{0, 0}, {0, 1}};
    private static final int[][] MISS_COORDS2 = {{0, 0}, {1, 0}};
    
    private static final String EMPTY_BOARD = TestingConstants.EXPECTED_EMPTY_BOARD_VIEW;
    private static final String SINGLE_SHIP = TestingConstants.EXPECTED_SINGLE_SHIP_BOARD_VIEW;
    private static final String SINGLE_OPPONENT_SHIP = TestingConstants.EXPECTED_EMPTY_BOARD_VIEW;
    private static final String SINGLE_SHIP_BOTH_FLEETS = TestingConstants.EXPECTED_SINGLE_SHIP_BOARD_VIEW;
    private static final String ALL_SHIPS_SINGLE_FLEET = TestingConstants.EXPECTED_ALL_SHIPS_BOARD_VIEW;
    private static final String ALL_SHIPS_OPPONENT_FLEET = TestingConstants.EXPECTED_EMPTY_BOARD_VIEW;
    private static final String ALL_FLEETS = TestingConstants.EXPECTED_ALL_SHIPS_BOARD_VIEW;
    
    private static final String SINGLE_HIT = TestingConstants.EXPECTED_SINGLE_HIT_BOARD_VIEW;
    private static final String SINGLE_OPPONENT_HIT = TestingConstants.EXPECTED_SINGLE_OPPONENT_HIT_BOARD_VIEW;
    private static final String SINGLE_BOTH_HIT = TestingConstants.EXPECTED_SINGLE_BOTH_HIT_BOARD_VIEW;
    private static final String SINGLE_MISS = TestingConstants.EXPECTED_SINGLE_MISS_BOARD_VIEW;
    private static final String SINGLE_OPPONENT_MISS = TestingConstants.EXPECTED_SINGLE_OPPONENT_MISS_BOARD_VIEW;
    private static final String SINGLE_BOTH_MISS = TestingConstants.EXPECTED_SINGLE_BOTH_MISS_BOARD_VIEW;
    
    private static final String TWO_HITS = TestingConstants.EXPECTED_TWO_HITS_BOARD_VIEW;
    private static final String TWO_OPPONENT_HITS = TestingConstants.EXPECTED_OPPONENT_TWO_HITS_BOARD_VIEW;
    private static final String BOTH_TWO_HITS = TestingConstants.EXPECTED_BOTH_TWO_HITS_BOARD_VIEW;
    private static final String TWO_MISSES = TestingConstants.EXPECTED_TWO_MISSES_BOARD_VIEW;
    private static final String TWO_OPPONENT_MISSES = TestingConstants.EXPECTED_OPPONENT_TWO_MISSES_BOARD_VIEW;
    private static final String BOTH_TWO_MISSES = TestingConstants.EXPECTED_BOTH_TWO_MISSES_BOARD_VIEW;
    private static final String SINGLE_HIT_AND_MISS = TestingConstants.EXPECTED_SINGLE_HIT_AND_MISS_BOARD_VIEW;
    private static final String SINGLE_OPPONENT_HIT_AND_MISS = TestingConstants.EXPECTED_SINGLE_OPPONENT_HIT_AND_MISS_BOARD_VIEW;
    private static final String SINGLE_BOTH_HIT_AND_MISS = TestingConstants.EXPECTED_BOTH_SINGLE_HIT_AND_MISS_BOARD_VIEW;
    
    private static final String SINGLE_SUNK = TestingConstants.EXPECTED_SINGLE_SUNK_BOARD_VIEW;
    private static final String SINGLE_OPPONENT_SUNK = TestingConstants.EXPECTED_OPPONENT_SINGLE_SUNK_BOARD_VIEW;
    private static final String SINGLE_BOTH_SUNK = TestingConstants.EXPECTED_BOTH_SINGLE_SUNK_BOARD_VIEW;
    
    private static final String SUNK_FLEET = TestingConstants.EXPECTED_SUNK_FLEET_BOARD_VIEW;
    private static final String OPPONENT_SUNK_FLEET = TestingConstants.EXPECTED_OPPONENT_SUNK_FLEET_BOARD_VIEW;
    
    
    private Board testBoard1;
    private Board testBoard2;
    private Fleet testFleet1;
    private Fleet testFleet2;
    private Ship testShip1;
    private Ship testShip2;
    private BoardView testView;
    
    
    @BeforeEach
    public void init()
    {
        testBoard1 = new Board();
        testBoard2 = new Board();
        testFleet1 = new Fleet();
        testFleet2 = new Fleet();
        testShip1 = testFleet1.get(0);
        testShip2 = testFleet2.get(0);
        testView = new BoardView(testBoard1, new OpponentBoard(testBoard2));
    }
    
    private void setBoards()
    {
        setBoard(testBoard1, testFleet1, FLEET1_POSITIONS);
        setBoard(testBoard2, testFleet2, FLEET2_POSITIONS);
    }
    
    private void setBoard(Board board, Fleet fleet, int[][][] fleetPositions)
    {
        for(int i = 0; i < fleet.size(); i++)
        {
            Ship ship = fleet.get(i);
            int[][] positions = fleetPositions[i];
    
            setShip(board, positions, ship);
        }
    }
    
    private void setShip(Board board, int[][] positions, Ship ship)
    {
        int[] start = positions[0];
        int[] end = positions[1];
        
        board.placeShip(start[0], start[1], end[0], end[1], ship);
    }
    
    // Initially empty
    @Test
    public void initallyEmpty()
    {
        assertValid(EMPTY_BOARD);
    }
    
    // Place single ship
    @Test
    public void placeSingleShip()
    {
        setShip(testBoard1, FLEET1_POSITIONS[0], testFleet1.get(0));
        assertValid(SINGLE_SHIP);
    }
    
    // Place single opponent ship
    @Test
    public void placeSingleOpponentShip()
    {
        setShip(testBoard2, FLEET2_POSITIONS[0], testFleet2.get(0));
        assertValid(SINGLE_OPPONENT_SHIP);
    }
    
    // Single ship both sides (should be same as single ship)
    @Test
    public void placeSingleShipBothFleets()
    {
        setShip(testBoard1, FLEET1_POSITIONS[0], testFleet1.get(0));
        setShip(testBoard2, FLEET2_POSITIONS[0], testFleet2.get(0));
        assertValid(SINGLE_SHIP_BOTH_FLEETS);
    }
    
    // Place entire single fleet
    @Test
    public void placeEntireSingleFleetOnly()
    {
        setBoard(testBoard1, testFleet1, FLEET1_POSITIONS);
        assertValid(ALL_SHIPS_SINGLE_FLEET);
    }
    
    // Place entire opponent fleet ONLY (should appear empty)
    @Test
    public void placeEntireOpponentFleetOnly()
    {
        setBoard(testBoard2, testFleet2, FLEET2_POSITIONS);
        assertValid(ALL_SHIPS_OPPONENT_FLEET);
    }
    
    // Place both fleets
    @Test
    public void placeAllShips()
    {
        setBoards();
        assertValid(ALL_FLEETS);
    }
    
    // Single hit
    @Test
    public void singleHit()
    {
        setBoards();
        move(testBoard2, HIT_COORDS2[0]);
        assertValid(SINGLE_HIT);
    }
    
    @Test
    public void singleOpponentHit()
    {
        setBoards();
        move(testBoard1, HIT_COORDS1[0]);
        assertValid(SINGLE_OPPONENT_HIT);
    }
    
    @Test
    public void singleBothHit()
    {
        setBoards();
        move(testBoard2, HIT_COORDS2[0]);
        move(testBoard1, HIT_COORDS1[0]);
        assertValid(SINGLE_BOTH_HIT);
    }
    
    
    // Single miss
    @Test
    public void singleMiss()
    {
        setBoards();
        move(testBoard2, MISS_COORDS2[0]);
        assertValid(SINGLE_MISS);
    }
    
    @Test
    public void singleOpponentMiss()
    {
        setBoards();
        move(testBoard1, MISS_COORDS1[0]);
        assertValid(SINGLE_OPPONENT_MISS);
    }
    
    @Test
    public void singleBothMiss()
    {
        setBoards();
        move(testBoard2, MISS_COORDS2[0]);
        move(testBoard1, MISS_COORDS1[0]);
        assertValid(SINGLE_BOTH_MISS);
    }
    
    
    // Two hits
    @Test
    public void twoHits()
    {
        setBoards();
        move(testBoard2, HIT_COORDS2[0]);
        move(testBoard2, HIT_COORDS2[1]);
        assertValid(TWO_HITS);
    }
    
    @Test
    public void twoOpponentHits()
    {
        setBoards();
        move(testBoard1, HIT_COORDS1[0]);
        move(testBoard1, HIT_COORDS1[1]);
        assertValid(TWO_OPPONENT_HITS);
    }
    
    @Test
    public void bothTwoHits()
    {
        setBoards();
        move(testBoard2, HIT_COORDS2[0]);
        move(testBoard2, HIT_COORDS2[1]);
        move(testBoard1, HIT_COORDS1[0]);
        move(testBoard1, HIT_COORDS1[1]);
        assertValid(BOTH_TWO_HITS);
    }
    
    
    // Single miss
    @Test
    public void twoMisses()
    {
        setBoards();
        move(testBoard2, MISS_COORDS2[0]);
        move(testBoard2, MISS_COORDS2[1]);
        assertValid(TWO_MISSES);
    }
    
    @Test
    public void twoOpponentMisses()
    {
        setBoards();
        move(testBoard1, MISS_COORDS1[0]);
        move(testBoard1, MISS_COORDS1[1]);
        assertValid(TWO_OPPONENT_MISSES);
    }
    
    @Test
    public void bothTwoMisses()
    {
        setBoards();
        move(testBoard2, MISS_COORDS2[0]);
        move(testBoard2, MISS_COORDS2[1]);
        move(testBoard1, MISS_COORDS1[0]);
        move(testBoard1, MISS_COORDS1[1]);
        assertValid(BOTH_TWO_MISSES);
    }
    
    // Single hit and miss
    @Test
    public void hitAndMiss()
    {
        setBoards();
        move(testBoard2, HIT_COORDS1[0]);
        move(testBoard2, MISS_COORDS1[0]);
        assertValid(SINGLE_HIT_AND_MISS);
    }
    
    @Test
    public void opponentHitAndMiss()
    {
        setBoards();
        move(testBoard1, HIT_COORDS2[0]);
        move(testBoard1, MISS_COORDS1[0]);
        assertValid(SINGLE_OPPONENT_HIT_AND_MISS);
    }
    
    @Test
    public void bothHitAndMiss()
    {
        setBoards();
        move(testBoard2, HIT_COORDS1[0]);
        move(testBoard2, MISS_COORDS1[0]);
        move(testBoard1, HIT_COORDS2[0]);
        move(testBoard1, MISS_COORDS1[0]);
        assertValid(SINGLE_BOTH_HIT_AND_MISS);
    }
    
    
    // Sink single ship
    @Test
    public void sinkSingleShip()
    {
        setBoards();
        sinkShip(testBoard2, FLEET2_COORDS[0]);
        assertValid(SINGLE_SUNK);
    }
    
    @Test
    public void opponentSinkSingleShip()
    {
        setBoards();
        sinkShip(testBoard1, FLEET1_COORDS[0]);
        assertValid(SINGLE_OPPONENT_SUNK);
    }
    
    @Test
    public void bothSinkSingleShip()
    {
        setBoards();
        sinkShip(testBoard2, FLEET2_COORDS[0]);
        sinkShip(testBoard1, FLEET1_COORDS[0]);
        assertValid(SINGLE_BOTH_SUNK);
    }
    
    
    // Sink Entire Fleet
    @Test
    public void sinkEntireFleet()
    {
        setBoards();
        sinkFleet(testBoard2, FLEET2_COORDS);
        assertValid(SUNK_FLEET);
    }
    
    @Test
    public void opponentSinkEntireFleet()
    {
        setBoards();
        sinkFleet(testBoard1, FLEET1_COORDS);
        assertValid(OPPONENT_SUNK_FLEET);
    }
    
    
    
    private void move(Board board, int[] coord)
    {
        move(board, coord[0], coord[1]);
    }
    
    private void move(Board board, int row, int col)
    {
        board.move(row, col);
    }
    
    
    
    private void sinkShip(Board board, int[][] coords)
    {
        for(int[] coord: coords)
        {
            move(board, coord);
        }
    }
    
    private void sinkFleet(Board board, int[][][] fleetCoords)
    {
        for(int[][] coords: fleetCoords)
        {
            sinkShip(board, coords);
        }
    }
    
    
    private void assertValid(String expected)
    {
        assertEquals(expected, testView.toString());
    }
}