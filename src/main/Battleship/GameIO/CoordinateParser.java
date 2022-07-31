package Battleship.GameIO;


/**
 * Parses the given coordinate
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class CoordinateParser
{
    /**
     * Parses the given <code>String</code> into a coordinate
     *
     * @param coordStr the coordinate <code>String</code>
     * @return the actual coordinate as an <code>int</code> array
     */
    public static int[] parse(String coordStr)
    {
        coordStr = coordStr.toUpperCase();
        int[] coords = new int[2];
        coords[1] = Character.codePointAt(coordStr, 0) - 65;
        coords[0] = Integer.parseInt(coordStr.substring(1)) - 1;
        return coords;
    }
}