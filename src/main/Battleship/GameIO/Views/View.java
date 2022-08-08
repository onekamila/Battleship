package Battleship.GameIO.Views;


import Battleship.game.Game;
import Battleship.game.Player;


/**
 * Handles the output of the current <code>Game</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.2
 * @since 0.1.0
 */
public abstract class View
{
	/**
	 * The <code>Game</code> represented by this <code>View</code>
	 */
	protected Game game;
	/**
	 * The current <code>Player</code> of this <code>View</code>'s <code>Game</code>
	 *
	 * @see Game#getCurrentPlayer()
	 */
	protected Player player;
	/**
	 * The current opponent <code>Player</code> of this <code>View</code>'s <code>Game</code>
	 *
	 * @see Game#getCurrentOpponent()
	 */
	protected Player opponent;
	
	
	/**
	 * Class constructor
	 *
	 * @param game the <code>Game</code> this <code>View</code> is based on
	 */
	public View(Game game)
	{
		this.game = game;
		this.player = game.getCurrentPlayer();
		this.opponent = game.getCurrentOpponent();
	}
	
	
	/**
	 * Update the current <code>Player</code>s
	 */
	protected void updatePlayers()
	{
		this.player = game.getCurrentPlayer();
		this.opponent = game.getCurrentOpponent();
	}
	
	/**
	 * Returns a <code>String</code> representation of this <code>View</code>
	 *
	 * @return a <code>String</code> representation of this <code>View</code>
	 */
	public abstract String toString();
}