package Battleship.GameIO.Views;


import Battleship.game.Game;


/**
 * Displays a combination of all views for a <code>Player</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class PlayerView extends View
{
    private BoardView boardView;
    private FleetOverview fleetOverview;
    private MoveHistoryView historyView;
    
    
    /**
     * Class constructor
     *
     * @param game the <code>Game</code> this <code>View</code> is based on
     */
    public PlayerView(Game game)
    {
        super(game);
        boardView = new BoardView(game);
        fleetOverview = new FleetOverview(game);
        historyView = new MoveHistoryView(game);
    }
    
    /**
     * Returns a <code>String</code> representation of this <code>View</code>
     *
     * @return a <code>String</code> representation of this <code>View</code>
     */
    @Override
    public String toString()
    {
        String outStr = "";
        
        String[] boards = boardView.toString().split("\n");
        String[] fleets = fleetOverview.toString().split("\n");
        String[] history = historyView.toString().split("\n");
        
        for(int i = 0; i < 13; i++)
        {
            outStr += boards[i] + "\t" + fleets[i] + "\t" + history[i] + "\n";
        }
        
        return outStr;
    }
}