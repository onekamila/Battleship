package Testing.UnitTests.Battleship.game.GameBoard;


import Battleship.game.GameBoard.Coordinate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CoordinateTest
{
	private Coordinate testCoord;
	private int testX;
	private int testY;
	private String testStr;
	
	
	// A1 (int)
	@Test
	public void a1Int()
	{
		setVars(0, 0);
		testCoord(testX, testY);
	}
	
	// J10 (int)
	@Test
	public void j10Int()
	{
		setVars(9, 9);
		testCoord(testX, testY);
	}
	
	// A1-J10 (int)
	@Test
	public void allInts()
	{
		for(testX = 0; testX < 10; testX++)
		{
			for(testY = 0; testY < 10; testY ++)
			{
				parseStr(testX, testY);
				testCoord(testX, testY);
			}
		}
	}
	
	// A1 (String)
	@Test
	public void a1String()
	{
		setVars(0, 0);
		testCoord(testStr);
	}
	
	// J10 (String)
	@Test
	public void j10String()
	{
		setVars(9, 9);
		testCoord(testStr);
	}
	
	// A1-J10 (String)
	@Test
	public void allStrings()
	{
		for(testX = 0; testX < 10; testX++)
		{
			for(testY = 0; testY < 10; testY ++)
			{
				parseStr(testX, testY);
				testCoord(testStr);
			}
		}
	}
	
	@Test
	public void validEquals()
	{
		testCoord = new Coordinate(0, 0);
		Coordinate otherCoord = new Coordinate(0, 0);
		
		assertTrue(testCoord.equals(otherCoord));
	}
	
	@Test
	public void invalidEquals()
	{
		testCoord = new Coordinate(0, 0);
		Coordinate otherCoord = new Coordinate(9, 4);
		assertFalse(testCoord.equals(otherCoord));
	}
	
	
	private void setVars(int x, int y)
	{
		testX = x;
		testY = y;
		parseStr(x, y);
	}
	
	private void parseStr(int x, int y)
	{
		testStr = Character.toString(y + 65) + (x + 1);
	}
	
	private void testCoord(int x, int y)
	{
		testCoord = new Coordinate(x, y);
		runTests();
	}
	
	private void testCoord(String coordStr)
	{
		testCoord = new Coordinate(coordStr);
		runTests();
	}
	
	private void runTests()
	{
		assertEquals(testX, testCoord.x);
		assertEquals(testY, testCoord.y);
		assertEquals(testStr, testCoord.toString());
	}
}