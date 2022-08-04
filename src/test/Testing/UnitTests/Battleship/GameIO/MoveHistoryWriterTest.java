package Testing.UnitTests.Battleship.GameIO;


import Battleship.GameIO.MoveHistoryWriter;
import Battleship.game.Game;
import Battleship.game.GameBoard.Coordinate;

import Testing.TestingConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.misc.Signal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;


public class MoveHistoryWriterTest
{
	private static final String DT_FORMAT_STRING = "YYYY/MM/DD HH:mm:ss";
	private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern(DT_FORMAT_STRING);
	
	private static final String EMPTY_HISTORY = TestingConstants.EXPECTED_EMPTY_HISTORY_LOG;
	private static final String FIRST_MOVE = TestingConstants.EXPECTED_FIRST_MOVE_HISTORY_LOG;
	private static final String SECOND_MOVE = TestingConstants.EXPECTED_SECOND_MOVE_HISTORY_LOG;
	private static final String THIRD_MOVE = TestingConstants.EXPECTED_THIRD_MOVE_HISTORY_LOG;
	private static final String ELEVEN_MOVES = TestingConstants.EXPECTED_ELEVEN_MOVES_HISTORY_LOG;
	private static final String TWENTY_TWO_MOVES = TestingConstants.EXPECTED_TWENTY_TWO_MOVES_HISTORY_LOG;
	private static final String TWENTY_THREE_MOVES = TestingConstants.EXPECTED_TWENTY_THREE_MOVES_HISTORY_LOG;
	private static final String TWENTY_FOUR_MOVES = TestingConstants.EXPECTED_TWENTY_FOUR_MOVES_HISTORY_LOG;
	private static final String TWENTY_SEVEN_MOVES = TestingConstants.EXPECTED_TWENTY_SEVEN_MOVES_HISTORY_LOG;
	private static final String TWENTY_EIGHT_MOVES = TestingConstants.EXPECTED_TWENTY_EIGHT_MOVES_HISTORY_LOG;
	
	private String testStr;
	private Game testGame;
	private MoveHistoryWriter testWriter;
	private File testFile;
	private Scanner reader;
	private String fileStr;
	
	
	
	@BeforeEach
	public void init() throws FileNotFoundException
	{
		MoveHistoryWriter.LOG_DIR_PATH = "." + File.separator + "logs";
		
		testGame = new Game();
		waitSecond();
		testStr = "Game Start: " + DT_FORMATTER.format(LocalDateTime.now()) + "\n\n";
		testWriter = new MoveHistoryWriter(testGame.getHistory());
		fileStr = "";
	}
	
	
	// Create directory
	@Test
	public void createDirectory() throws IOException
	{
		deleteTestFile();
		
		MoveHistoryWriter.LOG_DIR_PATH = null;
		
		testWriter = new MoveHistoryWriter(testGame.getHistory());
		
		File logDir = new File(MoveHistoryWriter.LOG_DIR_PATH);
		deleteTestFile();
		Files.deleteIfExists(logDir.toPath());
		
		assertFalse(logDir.exists());
		
		testWriter = new MoveHistoryWriter(testGame.getHistory());
		
		assertTrue(logDir.exists());
		
		deleteTestFile();
		Files.deleteIfExists(logDir.toPath());
	}
	
	@Test
	public void multipleLogs() throws IOException
	{
		testWriter.close();
		
		testFile = new File("logs" + File.separator + "game.log");
		testWriter = new MoveHistoryWriter(testGame.getHistory());
		testWriter.close();
		File testFile1 = new File("logs" + File.separator + "game1.log");
		Files.deleteIfExists(testFile1.toPath());
		
		deleteTestFile();
	}
	
	// Blank move
	@Test
	public void initiallyBlank() throws IOException
	{
		writeHistory(EMPTY_HISTORY);
		deleteTestFile();
	}
	
	// One Move
	@Test
	public void oneMove()
	{
		move(1);
		
		writeHistory(FIRST_MOVE);
	}
	
	// Two moves
	@Test
	public void twoMoves()
	{
		move(2);
		
		writeHistory(SECOND_MOVE);
	}
	
	// Three moves
	@Test
	public void threeMoves()
	{
		move(3);
		
		writeHistory(THIRD_MOVE);
	}
	
	// Twenty Two Moves
	@Test
	public void twentyTwoMoves()
	{
		move(22);
		
		writeHistory(TWENTY_TWO_MOVES);
	}
	
	// Twenty Three Moves
	@Test
	public void twentyThreeMoves()
	{
		move(23);
		
		writeHistory(TWENTY_THREE_MOVES);
	}
	
	// Twenty Four Moves
	@Test
	public void twentyFourMoves()
	{
		move(24);
		
		writeHistory(TWENTY_FOUR_MOVES);
	}
	
	// Twenty Seven Moves
	@Test
	public void twentySevenMovesMoves()
	{
		move(27);
		
		writeHistory(TWENTY_SEVEN_MOVES);
	}
	
	// Twenty Eight Moves
	@Test
	public void twentyEightMoves()
	{
		move(28);
		
		writeHistory(TWENTY_EIGHT_MOVES);
	}
	
	// Eleven Moves Interrupt
	@Test
	public void interrupt() throws InterruptedException, IOException
	{
		String expectedStr = testStr + ELEVEN_MOVES + " ";
		
		move(11);
		
		waitSecond();
		Signal.raise(new Signal("INT"));
		Thread.sleep(100);
		
		expectedStr += DT_FORMATTER.format(LocalDateTime.now()) + "\n";
		
		checkStr(expectedStr);
	}
	
	// This is just to help with code coverage
	@Test
	public void getException() throws IOException
	{
		deleteTestFile();
		
		File tempFile = new File("test.txt");
		tempFile.createNewFile();
		tempFile.setWritable(false);
		
		testWriter.setFile(new File("test.txt"));
		
		assertNull(testWriter.getGameLog());
		
		tempFile.setWritable(true);
		
		Files.deleteIfExists(tempFile.toPath());
	}
	
	// Correct Windows (again, for testing coverage)
	@Test
	public void correctWindows() throws IOException
	{
		deleteTestFile();
		
		System.setProperty("os.name", "WIN");
		
		assertEquals("WIN", System.getProperty("os.name"));
		
		MoveHistoryWriter.LOG_DIR_PATH = null;
		
		testWriter = new MoveHistoryWriter(testGame.getHistory());
		
		String logDirPath = MoveHistoryWriter.LOG_DIR_PATH;
		
		assertNotNull(logDirPath);
		
		File logDir = new File(logDirPath);
		File appDir = logDir.getParentFile().getParentFile();
		
		assertEquals("Roaming", appDir.getName());
		
		deleteTestFile();
	}
	
	// Correct Mac (again, for testing coverage)
	@Test
	public void correctMac() throws IOException
	{
		deleteTestFile();
		
		System.setProperty("os.name", "mac");
		
		assertEquals("mac", System.getProperty("os.name"));
		
		MoveHistoryWriter.LOG_DIR_PATH = null;
		
		testWriter = new MoveHistoryWriter(testGame.getHistory());
		
		String logDirPath = MoveHistoryWriter.LOG_DIR_PATH;
		
		assertNotNull(logDirPath);
		
		File logDir = new File(logDirPath);
		File appDir = logDir.getParentFile().getParentFile();
		
		assertEquals("Application Support", appDir.getName());
		
		deleteTestFile();
	}
	
	
	private void waitSecond()
	{
		LocalDateTime now = LocalDateTime.now();
		int milliseconds = 1000 - (now.getNano() / 1000000);
		int nanoseconds = 1000000 - (now.getNano() % 1000000);
		try
		{
			Thread.sleep(milliseconds, nanoseconds);
		}
		catch (InterruptedException ignored)
		{
			;
		}
	}
	
	private void move(int moves)
	{
		Coordinate coord;
		for(int x = 0; x < 10; x++)
		{
			for(int y = 0; y < 10; y++)
			{
				if(((x * 10) + y) > (moves - 1))
				{
					break;
				}
				coord = new Coordinate(x, y);
				testGame.move(coord);
			}
		}
	}
	
	private void writeHistory(String expected)
	{
		testStr += expected + "\n";
		
		testWriter.writeHistory();
		waitSecond();
		testStr += "\nGame End: " + DT_FORMATTER.format(LocalDateTime.now()) + "\n";
		testWriter.close();
		
		checkStr(testStr);
	}
	
	private void checkStr(String expected)
	{
		try
		{
			testFile = new File("logs" + File.separator + "game.log");
		
			reader = new Scanner(testFile);
			
			while(reader.hasNextLine())
			{
				fileStr += reader.nextLine() + "\n";
			}
			
			reader.close();
			
			assertEquals(expected, fileStr);
		}
		catch (FileNotFoundException e)
		{
			fail("File not found");
		}
	}
	
	private void deleteTestFile() throws IOException
	{
		testWriter.close();
		testFile = new File(MoveHistoryWriter.LOG_DIR_PATH + File.separator + "game.log");
		Files.deleteIfExists(testFile.toPath());
	}
	
	
	@AfterEach
	public void cleanup() throws IOException
	{
		if(testFile != null)
		{
			Files.deleteIfExists(testFile.toPath());
		}
	}
	
	
}