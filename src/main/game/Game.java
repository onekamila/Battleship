package game;


import GameIO.Views.PlayerView;


/**
 * Represents a full game
 */
public class Game
{
    private Player player1;
    private Player player2;;
    private PlacementTurn p1PT;
    private PlacementTurn p2PT;
    private Turn p1Turn;
    private Turn p2Turn;
    private MoveHistory history;
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
        winner = null;
        
        p1PT = new PlacementTurn(player1);
        p2PT = new PlacementTurn(player2);
        p1Turn = new Turn(player1, player2, history);
        p2Turn = new Turn(player2, player1, history);
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
     * @return the <code>MoveHistory</code> for the <code>Game</code>
     */
    public MoveHistory getHistory()
    {
        return history;
    }
    
    /**
     * Returns the <code>PlayerView</code> for Player 1
     *
     * @return the <code>PlayerView</code> for Player 1
     */
    public PlayerView getP1View()
    {
        return p1Turn.getView();
    }
    
    /**
     * Returns the <code>PlayerView</code> for Player 2
     *
     * @return the <code>PlayerView</code> for Player 2
     */
    public PlayerView getP2View()
    {
        return p2Turn.getView();
    }
    
    
    /**
     * Start the full game (includes placement turns)
     */
    public void start()
    {
        // Place ships
        p1PT.start();
        p2PT.start();
        
        // Begin game
        play();
    }
    
    /**
     * Start the game (skipping placement turns)
     */
    public void play()
    {
        boolean playing = true;
        
        while(playing)
        {
            // Player 1 turn
            turn(p1Turn);
            
            // Check Player 2
            if(checkPlayer(player2))
            {
                playing = false;
                winner = player1;
                continue;
            }
            
            // Player 2 turn
            turn(p2Turn);
            
            // Check Player 1
            if(checkPlayer(player1))
            {
                playing = false;
                winner = player2;
            }
        }
    }
    
    
    /**
     * Run a single <code>Turn</code>
     *
     * @param turn the <code>Turn</code> to be run
     */
    public void turn(Turn turn)
    {
        boolean valid = false;
        while(!valid)
        {
            valid = turn.start();
        }
    }
    
    /**
     * Make sure that the <code>Player</code>'s <code>Fleet</code> hasn't been sunk
     *
     * @param player the <code>Player</code> to check
     * @return <code>true</code> if all <code>Ship</code>s in the <code>Player</code>'s <code>Fleet</code> are sunk
     */
    private boolean checkPlayer(Player player)
    {
        return (player.getSunk() == 9);
    }
}