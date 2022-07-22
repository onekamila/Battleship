package Testing.UnitTests.game;


import GameIO.Input;
import GameIO.Views.PlayerView;
import Testing.TestingConstants;
import game.*;
import game.Ships.Fleet;
import game.Ships.Ship;

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
	private static final String EXPECTED_INVALID_STR = "Invalid input! Please try again.\r\n";
	private static final int[][][] FLEET1_POSITIONS = TestingConstants.FLEET1_POSITIONS;
	private static final int[][][] FLEET2_POSITIONS = TestingConstants.FLEET2_POSITIONS;
	
	private Player testPlayer;
	private Player testOpponent;
	private MoveHistory testHistory;
	private PlayerView testView;
	private Turn testTurn;
	private Input testInput;
	private ByteArrayOutputStream testOutStream;
	
	
	@BeforeEach
	public void init() throws FileNotFoundException
	{
		InputStream testFile = new FileInputStream(TEST_FILE_PATH);
		System.setIn(testFile);
		testOutStream = new ByteArrayOutputStream();
		PrintStream testStream = new PrintStream(testOutStream);
		System.setOut(testStream);
		testInput = Input.getInstance();
		
		testPlayer = new Player();
		testOpponent = new Player();
		testHistory = new MoveHistory();
		
		//OpponentBoard pOBoard = testPlayer.getOpponentBoard();
		//OpponentBoard oOBoard = testOpponent.getOpponentBoard();
		
		testPlayer.setOpponentBoard(testOpponent);
		testOpponent.setOpponentBoard(testPlayer);
		
		testTurn = new Turn(testPlayer, testOpponent, testHistory);
		testView = new PlayerView(testPlayer, testOpponent, testHistory);
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
		int[] start = positions[0];
		int[] end = positions[1];
		
		player.placeShip(start[0], start[1], end[0], end[1], ship);
	}
	
	
	
	@Test
	public void initiallyEmptyView()
	{
		String testOutStr = testOutStream.toString();
		assertEquals("", testOutStr);
		assertEquals(testView.toString(), testTurn.getView().toString());
	}
	
	@Test
	public void miss()
	{
		String expectedOutStr = testView.toString();
		
		boolean valid = testTurn.start();
		int remainingSquares = testPlayer.getOpponentBoard().getAvailable().size();
		String moveStr = testHistory.get(0).toString();
		int hits = getHits();
		assertTrue(valid);
		assertEquals(EXPECTED_REMAINING, remainingSquares);
		assertEquals(EXPECTED_MISS_STR, moveStr);
		assertEquals(EXPECTED_MISS_HITS, hits);
		
		String testOutStr = testOutStream.toString();
		Move move = testHistory.get(0);
		expectedOutStr += getResultStr(move);
		
		assertEquals(expectedOutStr, testOutStr);
	}
	
	@Test
	public void hit()
	{
		setBoard();
		
		// Skip first move
		testInput.readLine();
		
		// Run turn
		String expectedOutStr = testView.toString();
		
		boolean valid = testTurn.start();
		int remainingSquares = testPlayer.getOpponentBoard().getAvailable().size();
		String moveStr = testHistory.get(0).toString();
		int hits = getHits();
		assertTrue(valid);
		assertEquals(EXPECTED_REMAINING, remainingSquares);
		assertEquals(EXPECTED_HIT_STR, moveStr);
		assertEquals(EXPECTED_HIT_HITS, hits);
		
		String testOutStr = testOutStream.toString();
		Move move = testHistory.get(0);
		expectedOutStr += getResultStr(move);
		
		assertEquals(expectedOutStr, testOutStr);
	}
	
	@Test
	public void sunk()
	{
		setBoard();
		almostSink();
		
		// Skip first move
		testInput.readLine();
		
		// Run turn
		String expectedOutStr = testView.toString();
		
		boolean valid = testTurn.start();
		int remainingSquares = testPlayer.getOpponentBoard().getAvailable().size();
		String moveStr = testHistory.get(0).toString();
		int hits = getHits();
		assertTrue(valid);
		assertEquals(EXPECTED_SUNK_REMAINING, remainingSquares);
		assertEquals(EXPECTED_SUNK_STR, moveStr);
		assertEquals(EXPECTED_SUNK_HITS, hits);
		
		String testOutStr = testOutStream.toString();
		Move move = testHistory.get(0);
		expectedOutStr += getResultStr(move);
		
		assertEquals(expectedOutStr, testOutStr);
	}
	
	@Test
	public void invalid()
	{
		testInput.readLine();
		testInput.readLine();
		
		String expectedOutStr = testView.toString() + "\r\nEnter target coordinate: " + EXPECTED_INVALID_STR;
		
		boolean valid = testTurn.start();
		int remainingSquares = testPlayer.getOpponentBoard().getAvailable().size();
		int hits = getHits();
		assertFalse(valid);
		assertEquals(100, remainingSquares);
		assertEquals(0, hits);
		
		String testOutStr = testOutStream.toString();
		
		assertEquals(expectedOutStr, testOutStr);
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
		testPlayer.move(1, 2);
		testPlayer.move(1, 3);
		testPlayer.move(1, 4);
		testPlayer.move(1, 5);
	}
	
	private String getResultStr(Move move)
	{
		String resultStr = "\r\nEnter target coordinate: ";
		Ship ship = move.getSquare().getShip();
		
		if(move.getResult() == Result.SUNK)
		{
			resultStr += "You sank my " + ship.getName() + "!";
		}
		else
		{
			resultStr += move.getResult().name() + "!";
			
			if(move.getResult() == Result.HIT)
			{
				resultStr += " - " + ship.getName();
			}
		}
		
		resultStr += "\r\n";
		
		return resultStr;
	}
	
	
	@AfterEach
	public void shutdown()
	{
		testInput.close();
	}
}