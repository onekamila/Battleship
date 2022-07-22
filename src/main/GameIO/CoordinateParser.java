package GameIO;

public class CoordinateParser
{
    public static int[] parse(String coordStr)
    {
        coordStr = coordStr.toUpperCase();
        int[] coords = new int[2];
        coords[1] = Character.codePointAt(coordStr, 0) - 65;
        coords[0] = Integer.parseInt(coordStr.substring(1)) - 1;
        return coords;
    }
}
