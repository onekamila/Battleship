package Battleship.GameIO.Views;


import Battleship.game.Game;
import Battleship.game.Move;
import Battleship.game.MoveHistory;

import java.util.List;


/**
 * Displays the most 22 most recent <code>Move</code>s
 *
 * @see MoveHistory
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.1
 * @since 0.0.0
 */
public class MoveHistoryView extends View
{
    private MoveHistory moves;
    
    /**
     * Class constructor
     *
     * @param game the <code>Game</code> this <code>View</code> is based on
     */
    public MoveHistoryView(Game game)
    {
        super(game);
        moves = game.getHistory();
    }
    
    
    private Move[] getRecentMoves()
    {
        Move[] recentMoves;
    
        int start = getStartIndex();
        
        List<Move> moveList = moves.subList(start, moves.size());
        
        recentMoves = moveList.toArray(new Move[22]);
        
        return recentMoves;
    }
    
    private int getStartIndex()
    {
        if(moves.size() <= 22)
        {
            return 0;
        }
        else
        {
            int lastMoves = moves.size() - 21;
            return (lastMoves - (lastMoves % 2));
        }
    }
    
    private int getOffset()
    {
        if(moves.size() < 22)
        {
            return 1;
        }
        else
        {
            return ((moves.size() - 21) / 2) + 1;
        }
    }
    
    
    /**
     * Returns a <code>String</code> representation of this <code>View</code>
     *
     * @see Move#toString()
     *
     * @return a <code>String</code> representation of this <code>View</code>
     */
    @Override
    public String toString()
    {
        String outStr = "\t\t\t Moves\n";
        outStr += "  ===================\n";
        
        Move[] recentMoves = getRecentMoves();
        int offset = getOffset();
        for(int i = 0; i < 22; i += 2)
        {
            Move move1 = recentMoves[i];
            Move move2 = recentMoves[i + 1];
            
            if(move1 == null)
            {
                outStr += "\n\t";
                continue;
            }
            
            int moveNum = (i / 2) + offset;
            
            
            outStr += String.format("%3d. %6s", moveNum, move1);
            
            if(move2 != null)
            {
                outStr += String.format(", %6s", move2);
            }
            
            outStr += "\n";
        }
        
        return outStr;
    }
}