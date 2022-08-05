package Battleship.GameIO;


import java.util.Scanner;


/**
 * Handles input from the user
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.1
 * @since 0.0.0
 */
public class Input
{
    private static Input instance;
    private Scanner input;
    
    
    /**
     * Class Constructor
     */
    private Input()
    {
        input = new Scanner(System.in);
    }
    
    
    /**
     * Get the single instances of the <code>Input</code>
     *
     * @return the instance of the <code>Input</code>
     */
    public static Input getInstance()
    {
        if(instance == null)
        {
            instance = new Input();
        }
        
        return instance;
    }
    
    /**
     * Read a single line from the user
     *
     * @return a single line of text from the user
     */
    public String readLine()
    {
        String inStr = input.nextLine().strip();
        while(inStr.isBlank())
        {
            inStr = input.nextLine().strip();
        }
        return inStr;
    }
    
    
    /**
     * Close the <code>InputStream</code>
     */
    public void close()
    {
        input.close();
        instance = null;
    }
}