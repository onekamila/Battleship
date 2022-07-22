package Testing.UnitTests.game;


import GameIO.Input;
import GameIO.Views.PlayerView;
import Testing.TestingConstants;
import game.Game;
import game.GameBoard.Board;
import game.MoveHistory;
import game.Player;
import game.Ships.Fleet;
import game.Ships.Ship;
import game.Turn;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;


public class GameTest
{
	private static final String TEST_FILE_PATH = "src/test/resources/TestData/GameUnitTestData.txt";
	
	private static final int[][][] FLEET1_POSITIONS = TestingConstants.FLEET1_POSITIONS;
	private static final int[][][] FLEET2_POSITIONS = TestingConstants.FLEET2_POSITIONS;
	private static final int[][][] FLEET1_COORDS = TestingConstants.FLEET1_COORDS;
	private static final int[][][] FLEET2_COORDS = TestingConstants.FLEET2_COORDS;
	
	
	private Input testInput;
	private ByteArrayOutputStream testOutStream;
	private Game testGame;
	private Player testP1;
	private Player testP2;
	private MoveHistory testHistory;
	private Turn testTurn;
	private PlayerView testView1;
	private PlayerView testView2;
	
	
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
		testP1 = testGame.getPlayer1();
		testP2 = testGame.getPlayer2();
		testHistory = testGame.getHistory();
		testTurn = new Turn(testP1, testP2, testHistory);
		testView1 = new PlayerView(testP1, testP2, testHistory);
		testView2 = new PlayerView(testP2, testP1, testHistory);
	}
	
	private void setBoards()
	{
		setBoard(testP1, FLEET1_POSITIONS);
		setBoard(testP2, FLEET2_POSITIONS);
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
	
	// Has 2 players
	@Test
	public void hasTwoPlayers()
	{
		checkNotNull(testP1);
		checkNotNull(testP2);
	}
	
	// OpponentBoards switched
	@Test
	public void switchedOppBoards()
	{
		assertTrue(testP1.getBoard().equals(testP2.getOpponentBoard()));
		assertTrue(testP2.getBoard().equals(testP1.getOpponentBoard()));
	}
	
	// Initially empty
	@Test
	public void initiallyEmpty()
	{
		// Both boards empty
		int available1 = testGame.getPlayer1().getBoard().getAvailable().size();
		int available2 = testGame.getPlayer2().getBoard().getAvailable().size();
		assertEquals(100, available1);
		assertEquals(100, available2);
		
		// Move history empty
		assertEquals(0, testGame.getHistory().size());
		
		// No hits
		assertEquals(0, testGame.getPlayer1().getFleet().getHits());
		assertEquals(0, testGame.getPlayer2().getFleet().getHits());
		
		// No sunken ships
		assertEquals(0, testGame.getPlayer1().getSunk());
		assertEquals(0, testGame.getPlayer2().getSunk());
		
		// Identical Views
		assertEquals(testView1.toString(), testGame.getP1View().toString());
		assertEquals(testView2.toString(), testGame.getP2View().toString());
		
		// No winner
		assertNull(testGame.getWinner());
	}
	
	// Single Turn (beginning)
	@Test
	public void singleTurn()
	{
		setBoards();
		
		testTurn.start();
		
		// hits = 1
		assertEquals(0, testGame.getPlayer1().getFleet().getHits());
		assertEquals(1, testGame.getPlayer2().getFleet().getHits());
		
		assertEquals(1, testGame.getHistory().size());
		
		assertNull(testGame.getWinner());
		
	}
	
	// Winner
	@Test
	public void finalP1Turn()
	{
		// Setup Boards
		setBoards();
		
		// Sink P2 ships
		sinkShips(testP1, FLEET2_COORDS);
		
		// Last turn
		testGame.play();
		
		// Check winner
		assertNotNull(testGame.getWinner());
		assertEquals(testP1, testGame.getWinner());
	}
	
	@Test
	public void finalP2Turn()
	{
		// Setup Boards
		setBoards();
		
		// Sink P2 ships
		sinkShips(testP2, FLEET1_COORDS);
		
		// Last turn
		testGame.play();
		
		// Check winner
		assertNotNull(testGame.getWinner());
		assertEquals(testP2, testGame.getWinner());
	}
	
	@Test
	public void validSameSquareEach()
	{
		// Setup Boards
		setBoards();
		
		// Sink P2 ships
		sinkShips(testP2, FLEET1_COORDS);
		
		// Last turn
		testGame.play();
		
		// Check winner
		assertNotNull(testGame.getWinner());
		assertEquals(testP2, testGame.getWinner());
		
		// Check number of moves
		assertEquals(6, testHistory.size());
	}
	
	private void checkNotNull(Player player)
	{
		// Check that the Player object itself is not null
		assertNotNull(player);
		
		// Board
		assertNotNull(player.getBoard());
		
		// OppBoard
		assertNotNull(player.getOpponentBoard());
		
		// Fleet
		assertNotNull(player.getFleet());
	}
	
	private void sinkShips(Player player, int[][][] coords)
	{
		//for(int[][] ship: coords)
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
	
	
	
	@AfterEach
	public void shutdown()
	{
		testInput.close();
	}
}