package GameIO.Views;


import game.Move;
import game.MoveHistory;

import java.util.List;


/**
 * Displays the most 22 most recent <code>Move</code>s
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class MoveHistoryView
{
    private MoveHistory moves;
    
    /**
     * Class constructor
     *
     * @param history the full <code>MoveHistory</code> of the <code>Game</code>
     */
    public MoveHistoryView(MoveHistory history)
    {
        this.moves = history;
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
            return moves.size() - 21;
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
     * Returns a <code>String</code> representation of this view
     *
     * @return a <code>String</code> representation of this view
     */
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