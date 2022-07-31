package Battleship.game;


import java.util.ArrayList;


/**
 * Represents the full Battleship.game log
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class MoveHistory extends ArrayList<Move>
{
    /**
     * Class constructor
     */
    public MoveHistory()
    {
        super();
    }
    
    /**
     * Returns the most recent <code>Move</code> made
     *
     * @return the last <code>Move</code> in the <code>MoveHistory</code>
     */
    public Move getLastMove()
    {
        int last = size() - 1;
        return get(last);
    }
}