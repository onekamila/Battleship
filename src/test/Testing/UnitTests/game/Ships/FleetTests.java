package Testing.UnitTests.game.Ships;

import game.Ships.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FleetTests
{
	private Fleet testFleet;
	
	
	@BeforeEach
	public void init()
	{
		testFleet = new Fleet();
	}
	
	
	// Has 9 ships
	@Test
	public void hasEnoughShips()
	{
		assertEquals(9, testFleet.size());
	}
	
	// Has 1 Carrier
	@Test
	public void hasOneCarrier()
	{
		assertEquals(1, shipCount(AircraftCarrier.class));
	}
	
	// Has 1 Battleship
	@Test
	public void hasOneBattleship()
	{
		assertEquals(1, shipCount(Battleship.class));
	}
	
	// Has 2 Cruisers
	@Test
	public void hasTwoCruisers()
	{
		assertEquals(2, shipCount(Cruiser.class));
	}
	
	// Has 2 Submarines
	@Test
	public void hasTwoSubmarines()
	{
		assertEquals(2, shipCount(Submarine.class));
	}
	
	// Has 3 Destroyers
	@Test
	public void hasThreeDestroyers()
	{
		assertEquals(3, shipCount(Destroyer.class));
	}
	
	// Initially no hits
	@Test
	public void initiallyNoHits()
	{
		assertEquals(0, testFleet.getHits());
	}
	
	// 1 hit
	@Test
	public void oneHit()
	{
		testFleet.get(0).hit();
		assertEquals(1, testFleet.getHits());
	}
	
	// 1 sunk
	@Test
	public void oneSunk()
	{
		testFleet.get(0).hit();
		testFleet.get(0).hit();
		testFleet.get(0).hit();
		testFleet.get(0).hit();
		testFleet.get(0).hit();
		
		assertEquals(5, testFleet.getHits());
	}
	
	private int shipCount(Class shipClass)
	{
		int count = 0;
		
		for(Ship ship: testFleet)
		{
			if(ship.getClass() == shipClass)
			{
				count++;
			}
		}
		
		return count;
	}
}