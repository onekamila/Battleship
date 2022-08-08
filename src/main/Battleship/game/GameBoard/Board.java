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
 * @version 0.2.2
 * @since 0.0.0
 */
public class Board
{
    private Square[][] squares;
    /** The list of available <code>Square</code>s for this <code>Board</code> */
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
                Square newSquare = new Square(new Coordinate(i, j));
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
     * Places the given <code>Ship</code> at the specified <code>Coordinate</code> on this <code>Board</code>
     *
     * @see Square#setShip(Ship)
     *
     * @param start the start <code>Coordinate</code> of the <code>Ship</code>
     * @param end the end <code>Coordinate</code> of the <code>Ship</code>
     * @param ship the <code>Ship</code> to be placed
     */
    public void placeShip(Coordinate start, Coordinate end, Ship ship)
    {
        int rows = (end.x - start.x) + 1;
        int cols = (end.y - start.y) + 1;
        
        for(int x = 0; x < rows; x++)
        {
            for(int y = 0; y < cols; y++)
            {
                Square currentSquare = squares[start.x + x][start.y + y];
                currentSquare.setShip(ship);
                available.remove(currentSquare);
            }
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
     * Performs the desired move on the specified <code>Square</code>
     *
     * @param coord the <code>Coordinate</code> of the desired <code>Square</code>
     * @return the <code>Move</code> object representing the desired move
     */
    public Move move(Coordinate coord)
    {
        Square movedSquare = squares[coord.x][coord.y];
        Result result = movedSquare.move();
        available.remove(movedSquare);
        Move move = new Move(movedSquare);
        
        return move;
    }
    
    /**
     * Resets the specified <code>Square</code> to the original (<code>null</code>) state.
     *
     * @see Square#reset()
     *
     * This is mostly used for testing.
     *
     *
     * @param coord the <code>Coordinate</code> of the <code>Square</code> to be reset
     */
    public void resetSquare(Coordinate coord)
    {
        Square movedSquare = squares[coord.x][coord.y];
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