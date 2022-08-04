package Battleship;

import Battleship.Controller.GameController;
import Battleship.game.Game;
import Battleship.game.MoveHistory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 * Main class
 *
 * @see Game
 * @see GameController
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.0
 * @since 0.0.0
 */
public class Driver
{
    /**
     * Main method
     *
     * @param args arguments to be passed in (will be ignored)
     * @throws FileNotFoundException in case something weird happens
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        Game game = new Game();
        GameController controller = new GameController(game);
        
        controller.play();
    }
}