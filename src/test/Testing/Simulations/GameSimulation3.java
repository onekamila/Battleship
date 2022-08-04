package Testing.Simulations;

import Battleship.Controller.GameController;
import Battleship.GameIO.Input;
import Battleship.GameIO.MoveHistoryWriter;
import Battleship.game.Game;
import Battleship.game.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameSimulation3
{
	/**
	 * Game Simulation 3 (Real Battleship.game)
	 *
	 * Date: 2022 June 28
	 * Time: 21:28
	 *
	 * Player 1: Audrey Lee
	 * Player 2: Garrett K Crayton
	 *
	 * Winner: Player 2 (Garrett K Crayton)
	 * Moves:  100
	 *
	 * 			Player 1:	Player 2:
	 * 		-------------------------
	 * Hits:	 	   27		   23
	 * Sunk:	 	    9		    7
	 * Left: 	 	    0		    2
	 */
	
	private static final String INPUT_FILE_PATH = "src/test/resources/SimulationInputs/SimTest3.txt";
	
	private static FileInputStream testFile;
	
	private static Game testGame;
	private static GameController testController;
	
	private Player testP1;
	private Player testP2;
	
	
	@BeforeAll
	public static void init() throws FileNotFoundException
	{
		testFile = new FileInputStream(INPUT_FILE_PATH);
		System.setIn(testFile);
		
		testGame = new Game();
		testController = new GameController(testGame);
		testController.play();
	}
	
	
	@BeforeEach
	public void setup()
	{
		testP1 = testGame.getPlayer1();
		testP2 = testGame.getPlayer2();
	}
	
	
	// Winner = P2
	@Test
	public void correctWinner()
	{
		assertEquals(testP2, testGame.getWinner());
	}
	
	// Moves = 100
	@Test
	public void correctNumMoves()
	{
		assertEquals(100, testGame.getHistory().size());
	}
	
	
	// P1 Hits = 27
	@Test
	public void correctP1Hits()
	{
		assertEquals(27, testP1.getFleet().getHits());
	}
	
	// P1 Sunk = 9
	@Test
	public void correctP1Sunk()
	{
		assertEquals(9, testP1.getSunk());
	}
	
	// P2 Hits = 23
	@Test
	public void correctP2Hits()
	{
		assertEquals(23, testP2.getFleet().getHits());
	}
	
	// P2 Sunk = 7
	@Test
	public void correctP2Sunk()
	{
		assertEquals(7, testP2.getSunk());
	}
	
	
	@AfterAll
	public static void shutdown() throws IOException
	{
		testController.getHistoryWriter().close();
		File testFile = new File(MoveHistoryWriter.LOG_DIR_PATH + File.separator + "game.log");
		Files.deleteIfExists(testFile.toPath());
		
		Input.getInstance().close();
	}
}