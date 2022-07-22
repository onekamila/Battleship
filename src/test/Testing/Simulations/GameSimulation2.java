package Testing.Simulations;

import GameIO.Input;
import game.Game;
import game.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameSimulation2
{
	private static final String INPUT_FILE_PATH = "src/test/resources/SimulationInputs/SimTest2.txt";
	
	private static FileInputStream testFile;
	
	private static Game testGame;
	
	private Player testP1;
	private Player testP2;
	
	
	@BeforeAll
	public static void init() throws FileNotFoundException
	{
		testFile = new FileInputStream(INPUT_FILE_PATH);
		System.setIn(testFile);
		
		testGame = new Game();
		
		testGame.start();
	}
	
	
	@BeforeEach
	public void setup()
	{
		testP1 = testGame.getPlayer1();
		testP2 = testGame.getPlayer2();
	}
	
	
	@Test
	public void correctWinner()
	{
		assertEquals(testP2, testGame.getWinner());
	}
	
	@Test
	public void correctNumMoves()
	{
		assertEquals(54, testGame.getHistory().size());
	}
	
	@Test
	public void correctP1Hits()
	{
		assertEquals(27, testP1.getFleet().getHits());
	}
	
	@Test
	public void correctP1Sunk()
	{
		assertEquals(9, testP1.getSunk());
	}
	
	@Test
	public void correctP2Hits()
	{
		assertEquals(8, testP2.getFleet().getHits());
	}
	
	@Test
	public void correctP2Sunk()
	{
		assertEquals(1, testP2.getSunk());
	}
	
	
	@AfterAll
	public static void shutdown()
	{
		Input testInput = Input.getInstance();
		testInput.close();
	}
}