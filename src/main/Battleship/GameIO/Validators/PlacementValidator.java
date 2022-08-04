package Battleship.GameIO.Validators;


import Battleship.game.GameBoard.Board;
import Battleship.game.GameBoard.Coordinate;
import Battleship.game.Ships.Ship;


/**
 * Validates the coordinates given by the user are valid
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.0
 * @since 0.0.0
 */
public class PlacementValidator extends InputValidator
{
    /**
     * Class constructor
     *
     * @param board the <code>Board</code> each <code>Ship</code> will be placed on
     */
    public PlacementValidator(Board board)
    {
        super(board);
    }
    
    
    /**
     * Validate the coordinates given by the <code>Player</code>
     *
     * @see InputValidator#validate(String) 
     * 
     * @param coord1Str the start coordinate of the <code>Ship</code>
     * @param coord2Str the end coordinate of the <code>Ship</code>
     * @param ship the <code>Ship</code> to be placed
     * @return <code>true</code> if the coordinates given are valid, <code>false</code> otherwise
     */
    public boolean validate(String coord1Str, String coord2Str, Ship ship)
    {
        if(!(super.validate(coord1Str) && super.validate(coord2Str)))
        {
            return false;
        }
        
        Coordinate coord1 = new Coordinate(coord1Str);
        Coordinate coord2 = new Coordinate(coord2Str);
    
        return validPosition(coord1, coord2, ship);
    }
    
    private boolean validPosition(Coordinate coord1, Coordinate coord2, Ship ship)
    {
        Coordinate diff = new Coordinate((coord2.x - coord1.x), (coord2.y - coord1.y));
        
        if((diff.x != 0) && (diff.y != 0))
        {
            return false;
        }
        
        if((diff.x < 0) || (diff.y < 0))
        {
            return validPosition(coord2, coord1, ship);
        }
        
        diff.x++;
        diff.y++;
        
        if((diff.x != ship.getLength()) && (diff.y != ship.getLength()))
        {
            return false;
        }
        
        for(int x = 0; x < diff.x; x++)
        {
            for(int y = 0; y < diff.y; y++)
            {
                if(!board.checkAvailable(x + coord1.x, y + coord1.y))
                {
                    return false;
                }
            }
        }
        
        return true;
    }
}