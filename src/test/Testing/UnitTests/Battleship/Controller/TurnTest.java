package Testing.UnitTests.Battleship.Controller;


import Battleship.Controller.Turn;
import Battleship.GameIO.Input;
import Battleship.GameIO.Views.PlayerView;
import Battleship.game.GameBoard.Coordinate;
import Testing.TestingConstants;
import Battleship.game.*;
import Battleship.game.Ships.Fleet;
import Battleship.game.Ships.Ship;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TurnTest
{
	private static final String TEST_FILE_PATH = "src/test/resources/TestData/TurnTestData.txt";
	
	private static final int EXPECTED_REMAINING = 99;
	private static final int EXPECTED_SUNK_REMAINING = 95;
	private static final int EXPECTED_MISS_HITS = 0;
	private static final int EXPECTED_HIT_HITS = 1;
	private static final int EXPECTED_SUNK_HITS = 5;
	private static final String EXPECTED_MISS_STR = "A1-M";
	private static final String EXPECTED_HIT_STR = "B2-HA";
	private static final String EXPECTED_SUNK_STR = "B2-SA";
	private static final int[][][] FLEET1_POSITIONS = TestingConstants.FLEET1_POSITIONS;
	private static final int[][][] FLEET2_POSITIONS = TestingConstants.FLEET2_POSITIONS;
	
	private Game testGame;
	private Player testPlayer;
	private Player testOpponent;
	private MoveHistory testHistory;
	private PlayerView testView;
	private Turn testTurn;
	private Input testInput;
	
	
	@BeforeEach
	public void init() throws FileNotFoundException
	{
		InputStream testFile = new FileInputStream(TEST_FILE_PATH);
		System.setIn(testFile);
		testInput = Input.getInstance();
		
		testGame = new Game();
		testPlayer = testGame.getCurrentPlayer();
		testOpponent = testGame.getCurrentOpponent();
		testHistory = testGame.getHistory();
		
		testTurn = new Turn(testGame);
		testView = new PlayerView(testGame);
	}
	
	private void setBoard()
	{
		for(int i = 0; i < 9; i++)
		{
			placeShip(testPlayer, testPlayer.getFleet().get(i), FLEET1_POSITIONS[i]);
			placeShip(testOpponent, testOpponent.getFleet().get(i), FLEET2_POSITIONS[i]);
		}
	}
	
	private void placeShip(Player player, Ship ship, int[][] positions)
	{
		Coordinate start = new Coordinate(positions[0][0], positions[0][1]);
		Coordinate end = new Coordinate(positions[1][0], positions[1][1]);
		
		player.placeShip(start, end, ship);
	}
	
	
	@Test
	public void miss()
	{
		boolean valid = testTurn.start();
		int remainingSquares = testPlayer.getOpponentBoard().getAvailable().size();
		String moveStr = testHistory.get(0).toString();
		int hits = getHits();
		assertTrue(valid);
		assertEquals(EXPECTED_REMAINING, remainingSquares);
		assertEquals(EXPECTED_MISS_STR, moveStr);
		assertEquals(EXPECTED_MISS_HITS, hits);
	}
	
	@Test
	public void hit()
	{
		setBoard();
		
		// Skip first move
		testInput.readLine();
		
		// Run turn
		boolean valid = testTurn.start();
		int remainingSquares = testPlayer.getOpponentBoard().getAvailable().size();
		String moveStr = testHistory.get(0).toString();
		int hits = getHits();
		assertTrue(valid);
		assertEquals(EXPECTED_REMAINING, remainingSquares);
		assertEquals(EXPECTED_HIT_STR, moveStr);
		assertEquals(EXPECTED_HIT_HITS, hits);
	}
	
	@Test
	public void sunk()
	{
		setBoard();
		almostSink();
		
		// Skip first move
		testInput.readLine();
		
		// Run turn
		boolean valid = testTurn.start();
		int remainingSquares = testPlayer.getOpponentBoard().getAvailable().size();
		String moveStr = testHistory.get(0).toString();
		int hits = getHits();
		assertTrue(valid);
		assertEquals(EXPECTED_SUNK_REMAINING, remainingSquares);
		assertEquals(EXPECTED_SUNK_STR, moveStr);
		assertEquals(EXPECTED_SUNK_HITS, hits);
	}
	
	@Test
	public void invalid()
	{
		testInput.readLine();
		testInput.readLine();
		
		boolean valid = testTurn.start();
		int remainingSquares = testPlayer.getOpponentBoard().getAvailable().size();
		int hits = getHits();
		assertFalse(valid);
		assertEquals(100, remainingSquares);
		assertEquals(0, hits);
	}
	
	
	private int getHits()
	{
		Fleet fleet = testOpponent.getFleet();
		int hits = 0;
		
		for(Ship ship: fleet)
		{
			hits += ship.getHits();
		}
		
		return hits;
	}
	
	private void almostSink()
	{
		testPlayer.move(new Coordinate(1, 2));
		testPlayer.move(new Coordinate(1, 3));
		testPlayer.move(new Coordinate(1, 4));
		testPlayer.move(new Coordinate(1, 5));
	}
	
	
	@AfterEach
	public void shutdown()
	{
		testInput.close();
	}
}