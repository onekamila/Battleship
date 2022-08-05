package Battleship.game.GameBoard;


/**
 * Represents a coordinate on a <code>Board</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.1
 * @since 0.2.0
 */
public class Coordinate
{
	/**
	 * The x coordinate
	 */
	public int x;
	/**
	 * The y coordinate
	 */
	public int y;
	
	
	/**
	 * Class constructor
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Alternate constructor
	 *
	 * @param coordStr the coordinate as a <code>String</code>
	 */
	public Coordinate(String coordStr)
	{
		parseCoord(coordStr);
	}
	
	private void parseCoord(String coordStr)
	{
		coordStr = coordStr.toUpperCase();
		
		String xStr = coordStr.substring(1);
		char yChar = coordStr.charAt(0);
		
		this.x = Integer.parseInt(xStr) - 1;
		this.y = yChar - 65;
	}
	
	
	/**
	 * Returns a <code>String</code> representation of this of the <code>Board</code>
	 *
	 * @return this <code>Coordinate</code> as a <code>String</code>
	 */
	public String toString()
	{
		String xStr = Integer.toString(x + 1);
		String yStr = Character.toString(y + 65);
		
		return yStr + xStr;
	}
	
	
	/**
	 * Compares this <code>Coordinate</code> with another <code>Coordinate</code>
	 *
	 * @param other the <code>Coordinate</code> to compare against
	 * @return <code>true</code> if the <code>Coordinate</code>s are the same, <code>false</code> otherwise
	 */
	public boolean equals(Coordinate other)
	{
		return ((this.x == other.x) && (this.y == other.y));
	}
}