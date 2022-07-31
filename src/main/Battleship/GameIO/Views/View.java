package Battleship.GameIO.Views;


import Battleship.game.Game;
import Battleship.game.Player;


/**
 * Handles the output of the current <code>Game</code>
 *
 * @author Garrett Kamila Crayton
 * @version 0.1.0
 */
public abstract class View
{
	protected Game game;
	protected Player player;
	protected Player opponent;
	
	
	/**
	 * Class constuctor
	 *
	 * @param game the <code>Game</code> this <code>View</code> is based on
	 */
	public View(Game game)
	{
		this.game = game;
		this.player = game.getCurrentPlayer();
		this.opponent = game.getCurrentOpponent();
	}
	
	
	protected void updatePlayers()
	{
		this.player = game.getCurrentPlayer();
		this.opponent = game.getCurrentOpponent();
	}
	
	public abstract String toString();
}