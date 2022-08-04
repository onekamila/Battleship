package Testing.Simulations;


import Battleship.Controller.GameController;
import Battleship.GameIO.Input;
import Battleship.GameIO.MoveHistoryWriter;
import Battleship.game.Game;
import Battleship.game.MoveHistory;
import Battleship.game.Player;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameSimulation1
{
	private static final String INPUT_FILE_PATH = "src/test/resources/SimulationInputs/SimTest1.txt";
	
	private static FileInputStream testFile;
	
	private static Game testGame;
	private static GameController testController;
	
	private Player testP1;
	private Player testP2;
	private MoveHistory testHistory;
	
	
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
		testHistory = testGame.getHistory();
	}
	
	
	@Test
	public void correctWinner()
	{
		assertEquals(testP1, testGame.getWinner());
	}
	
	@Test
	public void correctNumMoves()
	{
		assertEquals(53, testGame.getHistory().size());
	}
	
	@Test
	public void correctP1Hits()
	{
		assertEquals(7, testP1.getFleet().getHits());
	}
	
	@Test
	public void correctP1Sunk()
	{
		assertEquals(0, testP1.getSunk());
	}
	
	@Test
	public void correctP2Hits()
	{
		assertEquals(27, testP2.getFleet().getHits());
	}
	
	@Test
	public void correctP2Sunk()
	{
		assertEquals(9, testP2.getSunk());
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