package GameIO.Validators;


import game.GameBoard.Board;
import game.Ships.Ship;


public class PlacementValidator extends InputValidator
{
    public PlacementValidator(Board board)
    {
        super(board);
    }
    
    public boolean validate(String coord1Str, String coord2Str, Ship ship)
    {
        if(!(super.validate(coord1Str) && super.validate(coord2Str)))
        {
            return false;
        }
        
        int[] coord1 = parse(coord1Str);
        int[] coord2 = parse(coord2Str);
        
        return validPosition(coord1, coord2, ship);
    }
    
    private boolean validPosition(int[] coord1, int[] coord2, Ship ship)
    {
        if(coord1[0] == coord2[0])
        {
            // Check valid horizontal
            return checkHorizontal(coord1, coord2, ship);
        }
        else if(coord1[1] == coord2[1])
        {
            // Check valid vertical
            return checkVertical(coord1, coord2, ship);
        }
        else
        {
            return false;
        }
    }
    
    private boolean checkHorizontal(int[] coord1, int[] coord2, Ship ship)
    {
        int distance = getDistance(coord1[1], coord2[1]);
        
        // Check ship length
        if(!checkLength(distance, ship))
        {
            return false;
        }
        
        // Check available slots
        return checkRow(coord1, distance);
    }
    
    private boolean checkVertical(int[] coord1, int[] coord2, Ship ship)
    {
        int distance = getDistance(coord1[0], coord2[0]);
        
        // Check ship length
        if(!checkLength(distance, ship))
        {
            return false;
        }
        
        // Check available slots
        return checkCol(coord1, distance);
    }
    
    private int getDistance(int sq1, int sq2)
    {
        if(sq1 > sq2)
        {
            return (sq1 - sq2) + 1;
        }
        else
        {
            return (sq2 - sq1) + 1;
        }
    }
    
    private boolean checkLength(int distance, Ship ship)
    {
        return (distance == ship.getLength());
    }
    
    private boolean checkRow(int[] origin, int distance)
    {
        for(int i = origin[1]; i < (origin[1] + distance); i++)
        {
            if(!board.checkAvailable(origin[0], i))
            {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean checkCol(int[] origin, int distance)
    {
        for(int i = origin[0]; i < (origin[0] + distance); i++)
        {
            if(!board.checkAvailable(i, origin[1]))
            {
                return false;
            }
        }
        
        return true;
    }
}