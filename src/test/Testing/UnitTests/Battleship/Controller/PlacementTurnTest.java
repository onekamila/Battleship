package Testing.UnitTests.Battleship.Controller;

import Battleship.GameIO.Input;
import Battleship.game.GameBoard.Coordinate;
import Testing.TestingConstants;
import Battleship.game.GameBoard.Board;
import Battleship.Controller.PlacementTurn;
import Battleship.game.Player;
import Battleship.game.Ships.Fleet;
import Battleship.game.Ships.Ship;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlacementTurnTest
{
	private static final String TEST_FILE_PATH = "src/test/resources/TestData/PlacementTurnTestData.txt";
	private static final int[][][] TEST_FLEET_POSITIONS = TestingConstants.FLEET1_POSITIONS;
	private static final int[][][] TEST_FLEET_COORDS = TestingConstants.FLEET1_COORDS;
	
	private Player testPlayer;
	private Board testBoard;
	private PlacementTurn testTurn;
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
		
		testPlayer = new Player();
		testBoard = testPlayer.getBoard();
		testTurn = new PlacementTurn(testPlayer);
		testInput = Input.getInstance();
	}
	
	
	@Test
	public void initiallyEmpty()
	{
		String testOutStr = testOutStream.toString();
		assertEquals("", testOutStr);
	}
	
	@Test
	public void validPlaceSingleShip()
	{
		skipInvalid();
		
		testTurn.placeShip(testPlayer.getFleet().get(0));
		
		// Check available
		int remaining = testBoard.getAvailable().size();
		assertEquals(95, remaining);
		
		// Check squares
		for(int i = 1; i < 6; i++)
		{
			assertFalse(testBoard.checkAvailable(i, 1));
		}
		
		// Check output
		String outStr = testOutStream.toString();
		String expectedOut = validShipStr(new Board(), testPlayer.getFleet().get(0));
		
		assertEquals(expectedOut, outStr);
	}
	
	@Test
	public void invalidPlaceSingleShip()
	{
		testTurn.placeShip(testPlayer.getFleet().get(0));
		
		// Check available
		int remaining = testBoard.getAvailable().size();
		assertEquals(95, remaining);
		
		// Check squares
		for(int i = 1; i < 6; i++)
		{
			assertFalse(testBoard.checkAvailable(i, 1));
		}
		
		// Check output
		String outStr = testOutStream.toString();
		String expectedOut = invalidSingleShipStr();
		
		assertEquals(expectedOut, outStr);
	}
	
	@Test
	public void validPlaceEntireFleet()
	{
		skipInvalid();
		
		testTurn.start();
		
		// Check available
		int remaining = testBoard.getAvailable().size();
		assertEquals(73, remaining);
		
		// Check squares
		for(int[][] ship: TEST_FLEET_COORDS)
		{
			for(int[] coord: ship)
			{
				assertFalse(testBoard.checkAvailable(coord[0], coord[1]));
			}
		}
		
		String expectedOut = validFleetStr();
		String outStr = testOutStream.toString();
		
		assertEquals(expectedOut, outStr);
	}
	
	private void skipInvalid()
	{
		testInput.readLine();
		testInput.readLine();
		testInput.readLine();
	}
	
	
	
	private String validFleetStr()
	{
		Board board = new Board();
		Fleet fleet = new Fleet();
		String outStr = "";
		
		for(int i = 0; i < 9; i++)
		{
			Ship ship = fleet.get(i);
			//int[] start = TEST_FLEET_POSITIONS[i][0];
			//int[] end = TEST_FLEET_POSITIONS[i][1];
			Coordinate start = new Coordinate(TEST_FLEET_POSITIONS[i][0][0], TEST_FLEET_POSITIONS[i][0][1]);
			Coordinate end = new Coordinate(TEST_FLEET_POSITIONS[i][1][0], TEST_FLEET_POSITIONS[i][1][1]);
			
			outStr += validShipStr(board, ship);
			
			//board.placeShip(start[0], start[1], end[0], end[1], ship);
			board.placeShip(start, end, ship);
		}
		
		return outStr;
	}
	
	private String validShipStr(Board board, Ship ship)
	{
		String outStr = board.toString();
		outStr += "\r\n";
		outStr += "Where would you like to start your " + ship.getName() + "? (Length: " + ship.getLength() + "): ";
		outStr += "Where would you like to end your " + ship.getName() + "? (Length: " + ship.getLength() + "): ";
		outStr += "\n\r\n";
		return outStr;
	}
	
	private String invalidSingleShipStr()
	{
		Board board = new Board();
		String outStr = board.toString();
		
		outStr += "\r\n";
		outStr += "Where would you like to start your Aircraft Carrier? (Length: 5): ";
		outStr += "Invalid input! Please try again.\r\n";
		outStr += "Where would you like to start your Aircraft Carrier? (Length: 5): ";
		outStr += "Where would you like to end your Aircraft Carrier? (Length: 5): ";
		outStr += "Invalid input! Please try again.\r\n";
		outStr += "Where would you like to end your Aircraft Carrier? (Length: 5): ";
		outStr += "Invalid input! Please try again.\r\n";
		outStr += "Where would you like to end your Aircraft Carrier? (Length: 5): ";
		outStr += "\n\r\n";
		
		return outStr;
	}
	
	
	
	@AfterEach
	public void shutdown()
	{
		testInput.close();
	}
}