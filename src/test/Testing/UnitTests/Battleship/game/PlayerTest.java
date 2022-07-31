package Testing.UnitTests.Battleship.game;

import Testing.TestingConstants;
import Battleship.game.GameBoard.OpponentBoard;
import Battleship.game.Move;
import Battleship.game.Player;
import Battleship.game.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest
{
	private static final int[][][] FLEET_POSITIONS = TestingConstants.FLEET1_POSITIONS;
	private static final int[][] HIT_COORDS = TestingConstants.FLEET1_COORDS[0];
	
	private Player testPlayer;
	
	
	@BeforeEach
	public void init()
	{
		testPlayer = new Player();
	}
	
	
	// Has Fleet
	@Test
	public void hasFleet()
	{
		assertNotNull(testPlayer.getFleet());
	}
	
	// Initially no sunken ships
	@Test
	public void initiallyNoSunkShips()
	{
		assertEquals(9, testPlayer.getFleet().size());
		assertEquals(0, testPlayer.getSunk());
	}
	
	// Set opponent board
	@Test
	public void setOpponentBoard()
	{
		//OpponentBoard testOpponentBoard = new OpponentBoard(new Board());
		Player testOpponent = new Player();
		OpponentBoard testOpponentBoard = testOpponent.getOpponentBoard();
		
		assertNotEquals(testOpponentBoard, testPlayer.getOpponentBoard());
		
		testPlayer.setOpponentBoard(testOpponent);
		
		assertTrue(testOpponentBoard.equals(testPlayer.getOpponentBoard()));
	}
	
	// Place ship
	@Test
	public void placeShip()
	{
		int[] start = FLEET_POSITIONS[0][0];
		int[] end = FLEET_POSITIONS[0][1];
		setShip();
		
		assertFalse(testPlayer.getBoard().checkAvailable(start[0], start[1]));
	}
	
	// Make move
	@Test
	public void makeOneMoveMiss()
	{
		setShip();
		
		Move testMove = move(0, 0);
		
		assertEquals(Result.MISS, testMove.getResult());
	}
	
	@Test
	public void makeOneMoveHit()
	{
		setShip();
		
		Move testMove = move(1, 1);
		
		assertEquals(Result.HIT, testMove.getResult());
	}
	
	// Sink ship
	@Test
	public void sinkOneShip()
	{
		setShip();
		
		move(1, 1);
		move(2, 1);
		move(3, 1);
		move(4, 1);
		
		Move testMove = move(5, 1);
		
		assertEquals(Result.SUNK, testMove.getResult());
	}
	
	// Get sunk
	@Test
	public void getSunk()
	{
		sinkShip();
		
		assertEquals(1, testPlayer.getSunk());
	}
	
	
	private void setShip()
	{
		int[] start = FLEET_POSITIONS[0][0];
		int[] end = FLEET_POSITIONS[0][1];
		testPlayer.placeShip(start[0], start[1], end[0], end[1], testPlayer.getFleet().get(0));
	}
	
	private Move move(int row, int col)
	{
		return testPlayer.move(row, col);
	}
	
	private void sinkShip()
	{
		setShip();
		
		for(int[] coord: HIT_COORDS)
		{
			testPlayer.move(coord[0], coord[1]);
		}
	}
}