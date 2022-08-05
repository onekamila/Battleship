package Testing.UnitTests.Battleship.game;


import Battleship.GameIO.Input;
import Battleship.game.GameBoard.Coordinate;
import Testing.TestingConstants;
import Battleship.game.Game;
import Battleship.game.GameBoard.Board;
import Battleship.game.Player;
import Battleship.game.Ships.Fleet;
import Battleship.game.Ships.Ship;

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
			
			Coordinate start = new Coordinate(positions[i][0][0], positions[i][0][1]);
			Coordinate end = new Coordinate(positions[i][1][0], positions[i][1][1]);
			
			board.placeShip(start, end, ship);
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
		
		// No winner
		assertNull(testGame.getWinner());
		
		// Current player is P1
		assertEquals(testP1, testGame.getCurrentPlayer());
		assertEquals(testP2, testGame.getCurrentOpponent());
	}
	
	// Player 1 First Turn
	@Test
	public void p1First()
	{
		assertEquals(testP1, testGame.getCurrentPlayer());
	}
	
	// Single Turn (beginning)
	@Test
	public void singleTurn()
	{
		setBoards();
		
		move(9, 5);
		
		// hits == 1
		assertEquals(0, testGame.getPlayer1().getFleet().getHits());
		assertEquals(1, testGame.getPlayer2().getFleet().getHits());
		
		assertEquals(1, testGame.getHistory().size());
		
		assertNull(testGame.getWinner());
		
		assertEquals(testP2, testGame.getCurrentPlayer());
		assertEquals(testP1, testGame.getCurrentOpponent());
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
		move(9, 5);
		
		// Check winner
		assertNotNull(testGame.getWinner());
		assertEquals(testP1, testGame.getWinner());
		assertEquals(testP1, testGame.getCurrentPlayer());
		assertEquals(testP2, testGame.getCurrentOpponent());
	}
	
	@Test
	public void finalP2Turn()
	{
		// Setup Boards
		setBoards();
		
		// Sink P2 ships
		sinkShips(testP2, FLEET1_COORDS);
		
		// Last turn
		move(9, 5);
		move(9, 5);
		move(1, 1);
		move(7, 1);
		
		// Check winner
		assertNotNull(testGame.getWinner());
		assertEquals(testP2, testGame.getWinner());
		assertEquals(testP2, testGame.getCurrentPlayer());
		assertEquals(testP1, testGame.getCurrentOpponent());
	}
	
	
	
	@Test
	public void singleP1Move()
	{
		setBoards();
		
		move(1, 3);
		
		assertEquals(1, testGame.getHistory().size());
		assertEquals(1, testGame.getPlayer2().getFleet().getHits());
		assertEquals(testP2, testGame.getCurrentPlayer());
		assertEquals(testP1, testGame.getCurrentOpponent());
	}
	
	@Test
	public void singleP2Move()
	{
		setBoards();
		
		move(0, 0);
		move(3, 1);
		
		assertEquals(2, testGame.getHistory().size());
		assertEquals(1, testGame.getPlayer1().getFleet().getHits());
		assertEquals(testP1, testGame.getCurrentPlayer());
		assertEquals(testP2, testGame.getCurrentOpponent());
	}
	
	
	private void skipPlacementCoords()
	{
		for(int i = 0; i < 36; i++)
		{
			testInput.readLine();
		}
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
	
	private void move(int x, int y)
	{
		testGame.move(new Coordinate(x, y));
	}
	
	private void sinkShips(Player player, int[][][] coords)
	{
		for(int i = 0; i < 8; i++)
		{
			int[][] ship = coords[i];
			
			for(int[] coord: ship)
			{
				player.move(new Coordinate(coord[0], coord[1]));
			}
		}
		
		player.move(new Coordinate(coords[8][0][0], coords[8][0][1]));
	}
	
	
	@AfterEach
	public void shutdown()
	{
		testInput.close();
	}
}