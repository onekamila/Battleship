package Testing.UnitTests.Battleship.game.GameBoard;


import Battleship.game.GameBoard.Coordinate;
import Battleship.game.Ships.Ship;


class ShipSquare
{
    public Ship ship;
    public Coordinate coord;
    
    public ShipSquare(Ship ship, Coordinate coord)
    {
        this.ship = ship;
        this.coord = coord;
    }
}