package GameIO;


import java.util.Scanner;


public class Input
{
    private static Input instance;
    private Scanner input;
    
    private Input()
    {
        input = new Scanner(System.in);
    }
    
    public static Input getInstance()
    {
        if(instance == null)
        {
            instance = new Input();
        }
        
        return instance;
    }
    
    public String readLine()
    {
        String inStr = input.nextLine().strip();
        while(inStr.isBlank())
        {
            inStr = input.nextLine().strip();
        }
        return inStr;
    }
    
    public void close()
    {
        input.close();
        instance = null;
    }
}