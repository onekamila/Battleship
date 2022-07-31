package Testing.UnitTests.Battleship.GameIO.Views;


import Battleship.GameIO.Input;
import Battleship.GameIO.Views.FleetOverview;
import Testing.TestingConstants;
import Battleship.game.Game;
import Battleship.game.Player;
import Battleship.game.Ships.Ship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FleetOverviewTest
{
    private static final String EXPECTED_EMPTY_VIEW = TestingConstants.EXPECTED_EMPTY_FLEET_VIEW;
    private static final String EXPECTED_HIT_SHIP_VIEW = TestingConstants.EXPECTED_FIRST_HIT_FLEET_VIEW;
    private static final String EXPECTED_SUNK_SHIP_VIEW = TestingConstants.EXPECTED_SUNK_SHIP_FLEET_VIEW;
    private static final String EXPECTED_SUNKEN_FLEET_VIEW = TestingConstants.EXPECTED_SUNK_FLEET_VIEW;
    
    private Game testGame;
    private Player testPlayer1;
    private Player testPlayer2;
    private Ship testShip;
    private FleetOverview testView;
    
    
    @BeforeEach
    public void init()
    {
        testGame = new Game();
        testPlayer1 = testGame.getPlayer1();
        testPlayer2 = testGame.getPlayer2();
        testView = new FleetOverview(testGame);
        testShip = testPlayer1.getFleet().get(0);
    }
    
    // Initially normal
    @Test
    public void initalView()
    {
        assertEquals(EXPECTED_EMPTY_VIEW, testView.toString());
    }
    
    // Single hit
    @Test
    public void singleHitView()
    {
        testShip.hit();
        assertEquals(EXPECTED_HIT_SHIP_VIEW, testView.toString());
    }
    
    // Sink single ship
    @Test
    public void singleSunkShipView()
    {
        sinkShip(testShip);
        assertEquals(EXPECTED_SUNK_SHIP_VIEW, testView.toString());
    }
    
    // Sink entire fleet
    @Test
    public void sunkFleetView()
    {
        sinkFleet();
        assertEquals(EXPECTED_SUNKEN_FLEET_VIEW, testView.toString());
    }
    
    
    private void sinkShip(Ship ship)
    {
        for(int i = 0; i < ship.getLength(); i++)
        {
            ship.hit();
        }
    }
    
    private void sinkFleet()
    {
        for(Ship ship: testPlayer1.getFleet())
        {
            sinkShip(ship);
        }
    }
    
    
    @AfterEach
    public void shutdown()
    {
        Input.getInstance().close();
    }
}