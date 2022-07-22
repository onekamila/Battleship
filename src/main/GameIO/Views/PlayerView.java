package GameIO.Views;

import game.GameBoard.Board;
import game.GameBoard.OpponentBoard;
import game.MoveHistory;
import game.Player;

public class PlayerView
{
    private Board playerBoard;
    private OpponentBoard opponentBoard;
    private BoardView boardView;
    private FleetOverview fleetOverview;
    private MoveHistoryView historyView;
    
    
    public PlayerView(Player player, Player opponent, MoveHistory history)
    {
        this.playerBoard = player.getBoard();
        this.opponentBoard = player.getOpponentBoard();
        boardView = new BoardView(playerBoard, opponentBoard);
        fleetOverview = new FleetOverview(player, opponent);
        historyView = new MoveHistoryView(history);
    }
    
    
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