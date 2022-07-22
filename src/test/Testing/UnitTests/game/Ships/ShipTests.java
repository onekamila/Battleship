package Testing.UnitTests.game.Ships;


import game.Ships.Battleship;
import game.Ships.Ship;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ShipTests
{
    private static final String TEST_NAME = "Battleship";
    private static final String TEST_STRING = "B";
    private Ship testShip;
    
    
    @BeforeEach
    public void init()
    {
        testShip = new Battleship();
    }
    
    
    // No hits initially
    @Test
    public void initiallyNoHits()
    {
        assertEquals(0, testShip.getHits());
    }
    
    // Single hit
    @Test
    public void singleHit()
    {
        hitShip(1);
    }
    
    // Multiple hits
    @Test
    public void multipleHits()
    {
        hitShip(3);
    }
    
    @Test
    // Sinking hit
    public void sinkingHit()
    {
        hitShip(testShip.getLength());
        
        assertTrue(testShip.sunk());
    }
    
    @Test
    public void correctName()
    {
        assertEquals(TEST_NAME, testShip.getName());
    }
    
    @Test
    public void correctString()
    {
        assertEquals(TEST_STRING, testShip.toString());
    }
    
    private void hitShip(int hits)
    {
        for(int i = 0; i < hits; i++)
        {
            testShip.hit();
        }
        
        assertEquals(hits, testShip.getHits());
    }
}