package GameIO.Validators;

import GameIO.CoordinateParser;
import game.GameBoard.Board;

public abstract class InputValidator
{
    protected Board board;
    
    
    public InputValidator(Board board)
    {
        this.board = board;
    }
    
    
    public boolean validate(String input)
    {
        try
        {
            int[] coord = parse(input);
        
            if ((coord[0] < 10) && (coord[1] < 10))
            {
                return board.checkAvailable(coord[0], coord[1]);
            }
            
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    protected int[] parse(String coord)
    {
        return CoordinateParser.parse(coord);
    }
}