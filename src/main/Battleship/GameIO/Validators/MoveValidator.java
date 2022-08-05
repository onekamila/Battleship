package Battleship.GameIO.Validators;


import Battleship.game.Game;


/**
 * Validates that the coordinate entered by the user is valid
 *
 * @see InputValidator
 * @see Battleship.game.GameBoard.OpponentBoard
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.1
 * @since 0.0.0
 */
public class MoveValidator extends InputValidator
{
    private Game game;
    
    
    /**
     * Class constructor
     *
     * @param game the <code>Game</code> this validator is used for
     */
    public MoveValidator(Game game)
    {
        super(game.getCurrentPlayer().getOpponentBoard());
        this.game = game;
    }
    
    
    /**
     * Validate the coordinate given by the user
     *
     * @see InputValidator#validate(String)
     *
     * @param coord the coordinate to be validated
     * @return <code>true</code> if the coordinate given is valid, <code>false</code> otherwise
     */
    public boolean validate(String coord)
    {
        super.board = game.getCurrentPlayer().getOpponentBoard();
        return super.validate(coord);
    }
}