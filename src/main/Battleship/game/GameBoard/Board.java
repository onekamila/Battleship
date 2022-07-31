package Battleship.game.GameBoard;


import Battleship.game.Move;
import Battleship.game.Result;
import Battleship.game.Ships.Ship;

import java.util.ArrayList;


/**
 * Represents a game board for a single player. This model displays the player's ships
 *
 * @see Square
 * @see Move
 *
 * @author Garrett Kamila Crayton
 * @version 0.1.0
 * @since 0.0.0
 */
public class Board
{
    private Square[][] squares;
    protected ArrayList<Square> available;
    
    
    /**
     * Class Constructor
     */
    public Board()
    {
        initBoard();
    }
    
    /**
     * Alternative constructor, used to construct a <code>Board</code> using previously used <code>Square</code>
     *
     * @param squares the 2D array of <code>Square</code>s to be used
     */
    protected Board(Square[][] squares)
    {
        this.squares = squares;
        available = new ArrayList<>();
        
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                available.add(squares[i][j]);
            }
        }
    }
    
    private void initBoard()
    {
        squares = new Square[10][10];
        available = new ArrayList<>();
        
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                String position = ((char) (j + 65)) + Integer.toString((i + 1));
                Square newSquare = new Square(position);
                squares[i][j] = newSquare;
                available.add(squares[i][j]);
            }
        }
    }
    
    
    /**
     * Returns all <code>Square</code>s for the <code>Board</code>
     *
     * @return the 2D array of <code>Square</code>s for this <code>Board</code>
     */
    public Square[][] getSquares()
    {
        return this.squares;
    }
    
    /**
     * Returns all available <code>Square</code>s for the <code>Board</code>
     *
     * @return the <code>ArrayList</code> of available <code>Squares</code>
     */
    public ArrayList<Square> getAvailable()
    {
        return this.available;
    }
    
    /**
     * Returns the <code>Square</code> at the specified row and column
     *
     * @param row the row number of the desired <code>Square</code>
     * @param col the column number of the desired <code>Square</code>
     * @return the <code>Square</code> at the given location
     */
    public Square getSquare(int row, int col)
    {
        return squares[row][col];
    }
    
    /**
     * Places the given <code>Ship</code> in the specified location on the <code>Board</code>
     *
     * @see Square#setShip(Ship)
     *
     * @param startRow the desired starting row of the <code>Ship</code>
     * @param startCol the desired starting column of the <code>Ship</code>
     * @param endRow the desired ending row of the <code>Ship</code>
     * @param endCol the desired ending column of the <code>Ship</code>
     * @param ship the desired <code>Ship</code> to be placed
     */
    public void placeShip(int startRow, int startCol, int endRow, int endCol, Ship ship)
    {
        int rows = endRow - startRow;
        int cols = endCol - startCol;
        
        int row = startRow;
        int col = startCol;
        
        for(int i = 0; i < ship.getLength(); i++)
        {
            if(rows != 0)
            {
                row = startRow + i;
            }
            if(cols != 0)
            {
                col = startCol + i;
            }
            
            
            Square currentSquare = squares[row][col];
            currentSquare.setShip(ship);
            available.remove(currentSquare);
        }
    }
    
    /**
     * Returns whether the specified <code>Square</code> is available
     *
     * @param row the desired row number
     * @param col the desired column number
     * @return <code>true</code> if the <code>Square</code> is empty (no <code>Ship</code> placed on it),
     *          <code>false</code> otherwise
     */
    public boolean checkAvailable(int row, int col)
    {
        Square checkedSquare = squares[row][col];
        return available.contains(checkedSquare);
    }
    
    /**
     * Performs the desired <code>Move</code> on the specified <code>Square</code>
     *
     * @see Square#move()
     *
     * @param row the row of the desired move
     * @param col the column of the desired move
     * @return the <code>Move</code> object representing the desired move
     */
    public Move move(int row, int col)
    {
        Square movedSquare = squares[row][col];
        Result result = movedSquare.move();
        available.remove(squares[row][col]);
        Move move = new Move(movedSquare, result);
        
        return move;
    }
    
    /**
     * Resets the specified <code>Square</code> to the original (<code>null</code>) state.
     *
     * @see Square#reset()
     *
     * This is mostly used for testing.
     */
    public void resetSquare(int row, int col)
    {
        Square movedSquare = squares[row][col];
        movedSquare.reset();
        available.add(movedSquare);
    }
    
    
    /**
     * Returns a <code>String</code> representation of this of the <code>Board</code>
     *
     * @see Square#toString()
     *
     * @return a <code>String</code> representation of the <code>Board</code>
     */
    @Override
    public String toString()
    {
        String outStr = "     ";
        
        for(int i = 0; i < 10; i++)
        {
            char outChar = (char) ('A' + i);
            outStr += (outChar) + " ";
        }
        
        outStr += "\n";
        
        for(int i = 0; i < 10; i++)
        {
            outStr += String.format("%3d", (i + 1)) + "  ";
            
            for(int j = 0; j < 10; j++)
            {
                outStr += squares[i][j] + " ";
            }
            
            outStr += "\n";
        }
        
        return outStr;
    }
    
    /**
     * Compares the <code>Square</code>s of each <code>Board</code>
     *
     * @param other the <code>Board</code> to compare against
     * @return <code>true</code> if the <code>Square</code>s are the same, <code>false</code> otherwise
     */
    public boolean equals(Board other)
    {
        return (this.squares == other.squares);
    }
}