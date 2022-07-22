package Testing.UnitTests.game.GameBoard;


import game.Ships.Ship;


class ShipSquare
{
    public Ship ship;
    public int[] position;
    
    public ShipSquare(Ship ship, int[] position)
    {
        this.ship = ship;
        this.position = position;
    }
}