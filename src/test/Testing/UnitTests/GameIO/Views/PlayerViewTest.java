package Testing.UnitTests.GameIO.Views;

import GameIO.Views.BoardView;
import GameIO.Views.FleetOverview;
import GameIO.Views.MoveHistoryView;
import GameIO.Views.PlayerView;
import Testing.TestingConstants;
import game.GameBoard.Board;
import game.Move;
import game.MoveHistory;
import game.Player;
import game.Ships.Fleet;
import game.Ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerViewTest
{
    private static final int[][][] ALL_COORDS = TestingConstants.ALL_COORDS;
    private static final int[][][] FLEET1_POSITIONS = TestingConstants.FLEET1_POSITIONS;
    private static final int[][][] FLEET2_POSITIONS = TestingConstants.FLEET2_POSITIONS;
    private static final int[][][] FLEET1_COORDS = TestingConstants.FLEET1_COORDS;
    private static final int[][][] FLEET2_COORDS = TestingConstants.FLEET2_COORDS;
    private static final int[][] HIT_COORDS1 = {FLEET1_COORDS[0][0], FLEET1_COORDS[0][1]};
    private static final int[][] HIT_COORDS2 = {FLEET2_COORDS[0][0], FLEET2_COORDS[0][1]};
    private static final int[][] MISS_COORDS1 = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}};
    private static final int[][] MISS_COORDS2 = {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}};
    private static final String EMPTY_VIEW = TestingConstants.EXPECTED_EMPTY_PLAYER_VIEW;
    
    
    private PlayerView testPlayerView;
    private Player testPlayer;
    private Player testOpponent;
    private MoveHistory testHistory;
    
    private BoardView testBoardView;
    private FleetOverview testFleetView;
    private MoveHistoryView testHistoryView;
    
    
    @BeforeEach
    public void init()
    {
        testPlayer = new Player();
        testOpponent = new Player();
        testHistory = new MoveHistory();
        
        testPlayerView = new PlayerView(testPlayer, testOpponent, testHistory);
        
        testBoardView = new BoardView(testPlayer.getBoard(), testPlayer.getOpponentBoard());
        testFleetView = new FleetOverview(testPlayer, testOpponent);
        testHistoryView = new MoveHistoryView(testHistory);
    }
    
    private void setBoards()
    {
        setBoard(testPlayer, FLEET1_POSITIONS);
        setBoard(testOpponent, FLEET2_POSITIONS);
    }
    
    private void setBoard(Player player, int[][][] fleetPositions)
    {
        Fleet fleet = player.getFleet();
        for (int i = 0; i < fleet.size(); i++)
        {
            Ship ship = fleet.get(i);
            int[][] positions = fleetPositions[i];
            
            setShip(player.getBoard(), positions, ship);
        }
    }
    
    private void setShip(Board board, int[][] positions, Ship ship)
    {
        int[] start = positions[0];
        int[] end = positions[1];
        
        board.placeShip(start[0], start[1], end[0], end[1], ship);
    }
    
    // Initially blank
    @Test
    public void initiallyBlank()
    {
        //assertValid(EMPTY_VIEW);
        checkView();
    }
    
    // Place single ship
    @Test
    public void placeSingleShip()
    {
        setShip(testPlayer.getBoard(), FLEET1_POSITIONS[0], testPlayer.getFleet().get(0));
        checkView();
    }
    
    
    // Place entire fleet
    @Test
    public void placeEntireFleet()
    {
        setBoard(testPlayer, FLEET1_POSITIONS);
        checkView();
    }
    
    
    // Single miss
    @Test
    public void singleMiss()
    {
        setBoards();
        move(testOpponent, MISS_COORDS2[0]);
        checkView();
    }
    
    // Single hit
    @Test
    public void singleHit()
    {
        setBoards();
        move(testOpponent, HIT_COORDS2[0]);
        checkView();
    }
    
    // Single sunk
    @Test
    public void singleSunk()
    {
        setBoards();
        int[][] hitCoords = FLEET2_POSITIONS[0];
        
        for(int i = 0; i < hitCoords.length; i++)
        {
            move(testOpponent, hitCoords[i]);
            move(testPlayer, MISS_COORDS2[i]);
        }
        
        checkView();
    }
    
    // All squares
    @Test
    public void allPossibleMovesOrdered()
    {
        int[] coords = new int[2];
        for(int i = 0; i < 10; i++)
        {
            coords[0] = i;
            for(int j = 0; j < 10; j++)
            {
                coords[1] = j;
                move(testOpponent, coords);
                move(testPlayer, coords);
            }
        }
        
        checkView();
    }
    
    
    private void move(Player player, int[] coord)
    {
        Move move = player.move(coord[0], coord[1]);
        testHistory.add(move);
    }
    
    private void checkView()
    {
        // Combine views
        String expectedView = combineViews();
        
        // Cast views
        String actualView = testPlayerView.toString();
        
        // Compare views
        assertEquals(expectedView, actualView);
    }
    
    private String combineViews()
    {
        String outStr = "";
        
        // Boards, Fleet, moves
        String[] boardLines = testBoardView.toString().split("\n");
        String[] fleetLines = testFleetView.toString().split("\n");
        String[] historyLines = testHistoryView.toString().split("\n");
        
        for(int i = 0; i < 13; i++)
        {
            outStr += boardLines[i] + "\t" + fleetLines[i] + "\t" + historyLines[i] + "\n";
        }
        
        return outStr;
    }
}