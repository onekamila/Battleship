package Battleship.game;


/**
 * Represents a full game
 *
 * @see Player
 * @see MoveHistory
 *
 * @author Garrett Kamila Crayton
 * @version 0.1.0
 * @since 0.0.0
 */
public class Game
{
    private Player player1;
    private Player player2;
    private MoveHistory history;
    private Player currentPlayer;
    private Player currentOpponent;
    private Player winner;
    
    
    /**
     * Class constructor
     */
    public Game()
    {
        player1 = new Player();
        player2 = new Player();
        
        player1.setOpponentBoard(player2);
        player2.setOpponentBoard(player1);
        
        history = new MoveHistory();
        
        currentPlayer = player1;
        currentOpponent = player2;
        winner = null;
    }
    
    
    /**
     * Returns Player 1
     *
     * @return <code>Player</code> for Player 1
     */
    public Player getPlayer1()
    {
        return player1;
    }
    
    /**
     * Returns Player 2
     *
     * @return <code>Player</code> for Player 2
     */
    public Player getPlayer2()
    {
        return player2;
    }
    
    /**
     * Returns the current <code>Player</code>
     *
     * @return the <code>Player</code> for this turn
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }
    
    /**
     * Returns the current opponent <code>Player</code>
     *
     * @return the opponent <code>Player</code> for this turn
     */
    public Player getCurrentOpponent()
    {
        return currentOpponent;
    }
    
    /**
     * Returns the winning <code>Player</code>
     *
     * @return the winning <code>Player</code>, <code>null</code> if none yet
     */
    public Player getWinner()
    {
        return winner;
    }
    
    /**
     * Returns the <code>Game</code>'s <code>MoveHistory</code>
     *
     * @return the <code>MoveHistory</code> for the <code>Game</code>
     */
    public MoveHistory getHistory()
    {
        return history;
    }
    
    /**
     * Returns the most recent <code>Move</code> made
     *
     * @see MoveHistory#getLastMove()
     *
     * @return the last <code>Move</code> in the <code>MoveHistory</code>
     */
    public Move getLastMove()
    {
        return history.getLastMove();
    }
    
    
    /**
     * Make a single move
     *
     * @see Player#move(int, int)
     *
     * @param row the row of the desired move
     * @param col the column of the desired move
     */
    public Move move(int row, int col)
    {
        Move move = currentPlayer.move(row, col);
        history.add(move);
        setStatus();
        return move;
    }
    
    private void setStatus()
    {
        if(checkPlayer(currentOpponent))
        {
            int lastMove = history.size() - 1;
            history.get(lastMove).setWin();
            winner = currentPlayer;
        }
        else
        {
            currentOpponent = currentPlayer;
            
            if(currentPlayer == player1)
            {
                currentPlayer = player2;
            }
            else
            {
                currentPlayer = player1;
            }
        }
    }
    
    /**
     * Make sure that the <code>Player</code>'s <code>Fleet</code> hasn't been sunk
     *
     * @see Player#checkFleet()
     * @see Player#getSunk()
     *
     * @param player the <code>Player</code> to check
     * @return <code>true</code> if all <code>Ship</code>s in the <code>Player</code>'s <code>Fleet</code> are sunk
     */
    private boolean checkPlayer(Player player)
    {
        player.checkFleet();
        return (player.getSunk() == 9);
    }
}