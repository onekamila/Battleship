package Battleship.GameIO.Validators;


import Battleship.game.GameBoard.Board;
import Battleship.game.GameBoard.Coordinate;


/**
 * Validates the input from the user
 *
 * @see Board
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.2
 * @since 0.0.0
 */
public abstract class InputValidator
{
    /**
     * The <code>Board</code> this <code>Validator</code> will use to validate the given input
     */
    protected Board board;
    
    
    /**
     * Class constructor
     *
     * @param board the <code>Board</code> that the input will be applied to
     */
    public InputValidator(Board board)
    {
        this.board = board;
    }
    
    
    /**
     * Validate the input given
     *
     * @see Board#checkAvailable(int, int)
     *
     * @param input the input to be validated
     * @return <code>true</code> if the input given is valid, <code>false</code> otherwise
     */
    public boolean validate(String input)
    {
        try
        {
            Coordinate coord = new Coordinate(input);
            
            if ((coord.x < 10) && (coord.y < 10))
            {
                return board.checkAvailable(coord.x, coord.y);
            }
            
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}