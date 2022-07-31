package Battleship.GameIO;


/**
 * Handles all output to the user
 *
 * @author Garrett Kamila Crayton
 * @version 0.1.0
 * @since 0.0.0
 */
public class Output
{
    private static Output instance;
    
    
    /**
     * Class constructor
     */
    private Output()
    {
        ;
    }
    
    
    /**
     * Get the single instances of the <code>Output</code>
     *
     * @return the instance of the <code>Output</code>
     */
    public static Output getInstance()
    {
        if(instance == null)
        {
            instance = new Output();
        }
        
        return instance;
    }
    
    
    /**
     * Write to the <code>Output</code>
     *
     * @param output the text to be written
     */
    public void print(String output)
    {
        System.out.print(output);
    }
    
    /**
     * Write to the <code>Output</code> with a new line at the end
     *
     * @param output the text to be written
     */
    public void println(String output)
    {
        System.out.println(output);
    }
}