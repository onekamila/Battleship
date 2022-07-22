package GameIO;

public class Output
{
    private static Output instance;
    
    
    private Output()
    {
        ;
    }
    
    
    public static Output getInstance()
    {
        if(instance == null)
        {
            instance = new Output();
        }
        
        return instance;
    }
    
    public void print(String output)
    {
        System.out.print(output);
    }
    
    public void println(String output)
    {
        System.out.println(output);
        //System.out.print(output + "\n");
    }
}
