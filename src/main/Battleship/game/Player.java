package Battleship.game;


import Battleship.game.GameBoard.Board;
import Battleship.game.GameBoard.Coordinate;
import Battleship.game.GameBoard.OpponentBoard;
import Battleship.game.Ships.*;

import java.util.ArrayList;


/**
 * Represents a single player
 *
 * @see Board
 * @see OpponentBoard
 * @see Fleet
 * @see ArrayList
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.2
 * @since 0.0.0
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
     * Return the number of sunken Ships for this <code>Player</code>
     *
     * @return the number of Ships in the <code>Player</code>'s fleet that have been sunk
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
     * @see Player#setOpponentBoard(Player)
     *
     * @param opponent the new <code>OpponentBoard</code>
     */
    public void setOpponentBoard(Player opponent)
    {
        this.oBoard = new OpponentBoard(opponent.board);
    }
    
    
    /**
     * Places the specified <code>Ship</code> at the specified <code>Coordinate</code> on this <code>Player</code>'s
     * <code>Board</code>
     *
     * @see Board#placeShip(Coordinate, Coordinate, Ship)
     *
     * @param start the start <code>Coordinate</code> of the <code>Ship</code>
     * @param end the end <code>Coordinate</code> of the <code>Ship</code>
     * @param ship the <code>Ship</code> to be placed
     */
    public void placeShip(Coordinate start, Coordinate end, Ship ship)
    {
        board.placeShip(start, end, ship);
    }
    
    
    /**
     * Performs the desired move at the specified <code>Coordinate</code>
     *
     * @param coord the <code>Coordinate</code> of the <code>Move</code>
     * @return the <code>Move</code> object representing this move
     */
    public Move move(Coordinate coord)
    {
        Move move = oBoard.move(coord);
        
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