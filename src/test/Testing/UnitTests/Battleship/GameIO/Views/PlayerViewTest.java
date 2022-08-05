package Testing.UnitTests.Battleship.GameIO.Views;


import Battleship.GameIO.Input;
import Battleship.GameIO.Views.BoardView;
import Battleship.GameIO.Views.FleetOverview;
import Battleship.GameIO.Views.MoveHistoryView;
import Battleship.GameIO.Views.PlayerView;
import Battleship.game.GameBoard.Coordinate;
import Testing.TestingConstants;
import Battleship.game.Game;
import Battleship.game.GameBoard.Board;
import Battleship.game.Move;
import Battleship.game.MoveHistory;
import Battleship.game.Player;
import Battleship.game.Ships.Fleet;
import Battleship.game.Ships.Ship;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerViewTest
{
    private static final int[][][] FLEET1_POSITIONS = TestingConstants.FLEET1_POSITIONS;
    private static final int[][][] FLEET2_POSITIONS = TestingConstants.FLEET2_POSITIONS;
    private static final int[][][] FLEET2_COORDS = TestingConstants.FLEET2_COORDS;
    private static final int[][] HIT_COORDS = {FLEET2_COORDS[0][0], FLEET2_COORDS[0][1]};
    private static final int[][] MISS_COORDS = {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}};
    
    
    private PlayerView testPlayerView;
    private Game testGame;
    private Player testPlayer;
    private Player testOpponent;
    private MoveHistory testHistory;
    
    private BoardView testBoardView;
    private FleetOverview testFleetView;
    private MoveHistoryView testHistoryView;
    
    
    @BeforeEach
    public void init()
    {
        testGame = new Game();
        testPlayer = testGame.getCurrentPlayer();
        testOpponent = testGame.getCurrentOpponent();
        testHistory = testGame.getHistory();
        
        testPlayerView = new PlayerView(testGame);
        
        testBoardView = new BoardView(testGame);
        testFleetView = new FleetOverview(testGame);
        testHistoryView = new MoveHistoryView(testGame);
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
        Coordinate start = new Coordinate(positions[0][0], positions[0][1]);
        Coordinate end = new Coordinate(positions[1][0], positions[1][1]);
    
        board.placeShip(start, end, ship);
    }
    
    // Initially blank
    @Test
    public void initiallyBlank()
    {
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
        move(testOpponent, MISS_COORDS[0]);
        checkView();
    }
    
    // Single hit
    @Test
    public void singleHit()
    {
        setBoards();
        move(testOpponent, HIT_COORDS[0]);
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
            move(testPlayer, MISS_COORDS[i]);
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
        Move move = player.move(new Coordinate(coord[0], coord[1]));
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
    
    
    @AfterEach
    public void shutdown()
    {
        Input.getInstance().close();
    }
}