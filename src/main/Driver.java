import game.Game;
import game.MoveHistory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 * Main class
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class Driver
{
    /**
     * Main method
     * @param args arguments to be passed in (will be ignored)
     * @throws FileNotFoundException in case something weird happens
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        Game game = new Game();
        
        game.start();
        
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