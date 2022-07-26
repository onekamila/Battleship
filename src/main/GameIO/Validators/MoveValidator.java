package GameIO.Validators;


import game.GameBoard.Board;


/**
 * Validates that the coordinate entered by the user is valid
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class MoveValidator extends InputValidator
{
    /**
     * Class constructor
     * @param board the <code>Board</code> the <code>Moves</code> will be made on
     */
    public MoveValidator(Board board)
    {
        super(board);
    }
    
    
    /**
     * Validate the coordinate given by the user
     *
     * @param coord the coordinate to be validated
     * @return <code>true</code> if the coordinate given is valid, <code>false</code> otherwise
     */
    public boolean validate(String coord)
    {
        return super.validate(coord);
    }
}