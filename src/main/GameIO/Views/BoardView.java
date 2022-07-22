package GameIO.Views;


import game.GameBoard.Board;
import game.GameBoard.OpponentBoard;


public class BoardView
{
    public Board board;
    public OpponentBoard oBoard;
    
    
    public BoardView(Board board, OpponentBoard oBoard)
    {
        this.board = board;
        this.oBoard = oBoard;
    }
    
    
    public String toString()
    {
        String outStr = "\t\t  My Fleet \t\t\t\t\tEnemy Fleet\t\t\n";
        outStr += " -----------------------\t -----------------------\n";
        
        String[] pBoardStr = board.toString().split("\n");
        String[] oBoardStr = oBoard.toString().split("\n");
    
        for(int i = 0; i < 11; i++)
        {
            outStr += pBoardStr[i] + "\t" + oBoardStr[i] + "\n";
        }
        
        return outStr;
    }
}
