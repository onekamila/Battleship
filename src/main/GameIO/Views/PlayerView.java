package GameIO.Views;


import game.GameBoard.Board;
import game.GameBoard.OpponentBoard;
import game.MoveHistory;
import game.Player;


/**
 * Displays a combination of all views for a <code>Player</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class PlayerView
{
    private Board playerBoard;
    private OpponentBoard opponentBoard;
    private BoardView boardView;
    private FleetOverview fleetOverview;
    private MoveHistoryView historyView;
    
    
    /**
     * Class constructor
     *
     * @param player the <code>Player</code> for this <code>View</code>
     * @param opponent the opponent of this view's <code>Player</code>
     * @param history the <code>MoveHistory</code> for the <code>Game</code>
     */
    public PlayerView(Player player, Player opponent, MoveHistory history)
    {
        this.playerBoard = player.getBoard();
        this.opponentBoard = player.getOpponentBoard();
        boardView = new BoardView(playerBoard, opponentBoard);
        fleetOverview = new FleetOverview(player, opponent);
        historyView = new MoveHistoryView(history);
    }
    
    
    /**
     * Returns a <code>String</code> representation of this view
     *
     * @return a <code>String</code> representation of this view
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