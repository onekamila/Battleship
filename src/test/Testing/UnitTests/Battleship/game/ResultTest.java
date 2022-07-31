package Testing.UnitTests.Battleship.game;


import Battleship.game.Result;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ResultTest
{
    private static final String HIT_NAME = "HIT";
    private static final String MISS_NAME = "MISS";
    private static final String SUNK_NAME = "SUNK";
    
    private static final String HIT_STRING = "X";
    private static final String MISS_STRING = "O";
    private static final String SUNK_STRING = "X";
    
    
    @Test
    public void hitName()
    {
        assertEquals(HIT_NAME, Result.HIT.name());
    }
    
    @Test
    public void missName()
    {
        assertEquals(MISS_NAME, Result.MISS.name());
    }
    
    @Test
    public void sunkName()
    {
        assertEquals(SUNK_NAME, Result.SUNK.name());
    }
    
    @Test
    public void hitString()
    {
        assertEquals(HIT_STRING, Result.HIT.toString());
    }
    
    @Test
    public void missString()
    {
        assertEquals(MISS_STRING, Result.MISS.toString());
    }
    
    @Test
    public void sunkString()
    {
        assertEquals(SUNK_STRING, Result.SUNK.toString());
    }
}