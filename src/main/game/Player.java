package game;


import game.GameBoard.Board;
import game.GameBoard.OpponentBoard;
import game.Ships.*;

import java.util.ArrayList;


/**
 * Represents a single player
 *
 * @author Garrett Kamila Crayton
 * @version 0.0.0
 */
public class Player
{
    private Board board;
    private OpponentBoard oBoard;
    private Fleet fleet;
    private ArrayList<Ship> sunk;
    
    
    /**
     * Class constructor
     */
    public Player()
    {
        board = new Board();
        oBoard = new OpponentBoard(board);
        fleet = new Fleet();
        sunk = new ArrayList<Ship>();
    }
    
    
    /**
     * Return this <code>Player</code>'s <code>Fleet</code>
     *
     * @return the <code>Fleet</code> for this <code>Player</code>
     */
    public Fleet getFleet()
    {
        return fleet;
    }
    
    /**
     * Return the number of sunken Ships
     *
     * @return the number of Ships in the Player's fleet that have been sunk
     */
    public int getSunk()
    {
        checkFleet();
        return sunk.size();
    }
    
    /**
     * Return this <code>Player</code>'s <code>Board</code>
     *
     * @return the <code>Board</code> for this <code>Player</code>
     */
    public Board getBoard()
    {
        return board;
    }
    
    /**
     * Return this <code>Player</code>'s <code>OpponentBoard</code>
     *
     * @return the <code>OpponentBoard</code> for this <code>Player</code>
     */
    public OpponentBoard getOpponentBoard()
    {
        return oBoard;
    }
    
    /**
     * Set this <code>Player</code>'s <code>OpponentBoard</code> to the given <code>OpponentBoard</code>
     *
     * @param opponent the new <code>OpponentBoard</code>
     */
    public void setOpponentBoard(Player opponent)
    {
        this.oBoard = new OpponentBoard(opponent.board);
    }
    
    
    /**
     * Place a single <code>Ship</code> at the specified location
     *
     * @param startRow the desired starting row of the <code>Ship</code>
     * @param startCol the desired starting column of the <code>Ship</code>
     * @param endRow the desired ending row of the <code>Ship</code>
     * @param endCol the desired ending column of the <code>Ship</code>
     * @param ship the desired <code>Ship</code> to be placed
     */
    public void placeShip(int startRow, int startCol, int endRow, int endCol, Ship ship)
    {
        board.placeShip(startRow, startCol, endRow, endCol, ship);
    }
    
    /**
     * Performs the desired move at the specified location
     *
     * @param row the row of the desired move
     * @param col the column of the desired move
     * @return the Move object representing the desired move
     */
    public Move move(int row, int col)
    {
        Move move = oBoard.move(row, col);
        
        return move;
    }
    
    /**
     * Check if any ships have been sunk
     */
    protected void checkFleet()
    {
        for(Ship ship: fleet)
        {
            if(ship.sunk() && (!sunk.contains(ship)))
            {
                sunk.add(ship);
            }
        }
    }
}