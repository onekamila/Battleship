package Battleship.Controller;


import Battleship.GameIO.MoveHistoryWriter;
import Battleship.GameIO.Output;
import Battleship.GameIO.Views.PlayerView;
import Battleship.game.Game;
import Battleship.game.GameBoard.Square;
import Battleship.game.Move;
import Battleship.game.Result;
import Battleship.game.Ships.Ship;


/**
 * Controller class for a full game of Battleship.
 * <p>
 * This class controls the actual flow of the game. It receives input from the user, validates the input, uses the input
 * to manipulate the <code>Game</code> model (assuming the input is valid), and then outputs the result to the user.
 * <p>
 * This class will also allow the project to be expanded to use different IO, along with adding a single-player (AI)
 * option to the game in the future.
 *
 * @see PlacementTurn
 * @see Turn
 * @see Game
 * @see Output
 * @see PlayerView
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.2
 * @since 0.1.0
 */
public class GameController
{
	private Output output;
	private MoveHistoryWriter historyWriter;
	private Game game;
	
	private PlacementTurn p1PT;
	private PlacementTurn p2PT;
	private Turn turnController;
	
	private PlayerView view;
	
	
	/**
	 * Class constructor
	 *
	 * @param game the <code>Game</code> to be controlled
	 */
	public GameController(Game game)
	{
		this.game = game;
		
		init();
	}
	
	private void init()
	{
		this.output = Output.getInstance();
		
		p1PT = new PlacementTurn(game.getPlayer1());
		p2PT = new PlacementTurn(game.getPlayer2());
		turnController = new Turn(game);
		
		view = new PlayerView(game);
		historyWriter = new MoveHistoryWriter(game.getHistory());
	}
	
	
	/**
	 * Return the current <code>PlayerView</code>
	 * @return the <code>PlayerView</code> based on the current <code>Game</code> state
	 */
	public PlayerView getView()
	{
		return view;
	}
	
	/**
	 * Return the <code>MoveHistoryWriter</code> for this <code>Game</code>
	 * @return the <code>MoveHistoryWriter</code> for this <code>Game</code>
	 */
	public MoveHistoryWriter getHistoryWriter()
	{
		return historyWriter;
	}
	
	/**
	 * Begin the game (including placing the ships)
	 */
	public void play()
	{
		// Place ships
		p1PT.start();
		p2PT.start();
		
		// Begin game
		startGame();
	}
	
	/**
	 * Begin the game proper (excludes placing the ships)
	 */
	public void startGame()
	{
		boolean valid;
		
		while(game.getWinner() == null)
		{
			// Display player view
			output.println(view.toString());
			
			// Run Turn
			valid = false;
			while (!valid)
			{
				valid = turnController.start();
			}
			
			// Output result
			outputResult();
		}
		
		// Write history file
		historyWriter.writeHistory();
		historyWriter.close();
	}
	
	private void outputResult()
	{
		Move move = game.getLastMove();
		Square square = move.getSquare();
		Result result = move.getResult();
		String outStr = result.name() + "!";
		
		// If a hit or sunk, get the correct output
		if(result != Result.MISS)
		{
			// Get the ship that was hit
			Ship hitShip = square.getShip();
			
			// If a hit
			if(result == Result.HIT)
			{
				outStr += " - " + hitShip.getName();
			}
			else
			{
				outStr = "You sank my " + hitShip.getName() + "!";
			}
		}
		
		output.println(outStr);
	}
}