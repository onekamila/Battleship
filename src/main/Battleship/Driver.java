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
 * @version 0.1.0
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
        
        File file = new File("gameLog.txt");
        PrintWriter writer = new PrintWriter(file);
        MoveHistory history = game.getHistory();
        
        writer.println("\t   Moves\n===================");
        int moveNum;
        String historyStr;
        for(int i = 0; i < history.size(); i += 2)
        {
            moveNum = (i / 2);
            historyStr = moveNum + ". " + history.get(i);
            
            if(history.size() < (i + 1))
            {
                historyStr += ", " + history.get(i + 1);
            }
            
            writer.println(historyStr);
        }
    }
}