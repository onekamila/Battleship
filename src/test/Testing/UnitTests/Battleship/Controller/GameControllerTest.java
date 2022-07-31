package Testing.UnitTests.Battleship.Controller;


import Battleship.Controller.GameController;
import Battleship.GameIO.Input;
import Battleship.GameIO.Views.PlayerView;
import Testing.TestingConstants;
import Battleship.game.Game;
import Battleship.game.GameBoard.Board;
import Battleship.game.MoveHistory;
import Battleship.game.Player;
import Battleship.game.Ships.Fleet;
import Battleship.game.Ships.Ship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;


public class GameControllerTest
{
	private static final String TEST_FILE_PATH = "src/test/resources/TestData/GameUnitTestData.txt";
	
	private static final int[][][] FLEET1_POSITIONS = TestingConstants.FLEET1_POSITIONS;
	private static final int[][][] FLEET2_POSITIONS = TestingConstants.FLEET2_POSITIONS;
	private static final int[][][] FLEET1_COORDS = TestingConstants.FLEET1_COORDS;
	
	
	private Input testInput;
	private ByteArrayOutputStream testOutStream;
	private Game testGame;
	private Player testP1;
	private Player testP2;
	private MoveHistory testHistory;
	private PlayerView testView;
	
	private GameController testController;
	
	
	@BeforeEach
	public void init() throws FileNotFoundException
	{
		InputStream testFile = new FileInputStream(TEST_FILE_PATH);
		System.setIn(testFile);
		testOutStream = new ByteArrayOutputStream();
		PrintStream testStream = new PrintStream(testOutStream);
		System.setOut(testStream);
		testInput = Input.getInstance();
		
		testGame = new Game();
		testP1 = testGame.getCurrentPlayer();
		testP2 = testGame.getCurrentOpponent();
		testHistory = testGame.getHistory();
		testView = new PlayerView(testGame);
		
		testController = new GameController(testGame);
	}
	
	private void setBoards()
	{
		setBoard(testP1, FLEET1_POSITIONS);
		setBoard(testP2, FLEET2_POSITIONS);
		skipPlacementCoords();
	}
	
	private void setBoard(Player player, int[][][] positions)
	{
		Board board = player.getBoard();
		Fleet fleet = player.getFleet();
		
		for(int i = 0; i < 9; i++)
		{
			Ship ship = fleet.get(i);
			int[] start = positions[i][0];
			int[] end = positions[i][1];
			
			board.placeShip(start[0], start[1], end[0], end[1], ship);
		}
	}
	
	
	@Test
	public void initiallyEmptyView()
	{
		String testOutStr = testOutStream.toString();
		assertEquals("", testOutStr);
		assertEquals(testView.toString(), testController.getView().toString());
	}
	
	@Test
	public void validSameSquareEach()
	{
		// Setup Boards
		setBoards();
		
		// Sink P2 ships
		sinkShips(testP2, FLEET1_COORDS);
		
		// Last turn
		//testController.play();
		testController.startGame();
		
		// Check winner
		assertNotNull(testGame.getWinner());
		assertEquals(testP2, testGame.getWinner());
		
		// Check number of moves
		assertEquals(6, testHistory.size());
	}
	
	@Test
	public void placeShips()
	{
		startGame();
		
		assertNotNull(testGame.getPlayer1().getBoard().getSquare(3, 1).getShip());
		assertNotNull(testGame.getPlayer2().getBoard().getSquare(1, 3).getShip());
		
		assertEquals(6, testGame.getHistory().size());
		assertNull(testGame.getWinner());
		
		assertEquals(1, testGame.getPlayer1().getFleet().getHits());
		assertEquals(1, testGame.getPlayer2().getFleet().getHits());
	}
	
	
	private void skipPlacementCoords()
	{
		for(int i = 0; i < 36; i++)
		{
			String line = testInput.readLine();
			continue;
		}
	}
	
	private void sinkShips(Player player, int[][][] coords)
	{
		for(int i = 0; i < 8; i++)
		{
			int[][] ship = coords[i];
			
			for(int[] coord: ship)
			{
				player.move(coord[0], coord[1]);
			}
		}
		
		player.move(coords[8][0][0], coords[8][0][1]);
	}
	
	private void startGame()
	{
		try
		{
			testController.play();
		}
		catch(NoSuchElementException ignored)
		{
			;
		}
	}
	
	
	@AfterEach
	public void shutdown()
	{
		testInput.close();
	}
}