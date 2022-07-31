package Testing.UnitTests.Battleship.GameIO.Validators;


import Battleship.GameIO.Input;
import Battleship.GameIO.Validators.InputValidator;
import Battleship.GameIO.Validators.MoveValidator;
import Battleship.game.Game;
import Battleship.game.GameBoard.Board;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class InputValidatorTest
{
    private static final String[] TEST_INPUTS = {"A1", "A1", "A10", "J1", "J10", "a1", "a10", "j1", "j10", "A0", "A11", "K0", "K1", "K10", "K11", "11", "AA", "1A", "1", "AA1", "AA123"};
    private InputValidator validator;
    private Game testGame;
    private Board testBoard;
    
    @BeforeEach
    public void init()
    {
        testGame = new Game();
        testBoard = testGame.getCurrentPlayer().getOpponentBoard();
        validator = new MoveValidator(testGame);
    }
    
    // Valid input
    @Test
    public void validInput()
    {
        assertValid(0);
    }
    
    // Valid A1
    @Test
    public void validInputA1()
    {
        assertValid(1);
    }
    
    // Valid A10
    @Test
    public void validInputA10()
    {
        assertValid(2);
    }
    
    // Valid J1
    @Test
    public void validInputJ1()
    {
        assertValid(3);
    }
    
    // Valid J10
    @Test
    public void validInputJ10()
    {
        assertValid(4);
    }
    
    // Valid all (loop)
    @Test
    public void validInputLoop()
    {
        for(char c = 'A'; c < 'K'; c++)
        {
            for (int i = 1; i < 11; i++)
            {
                String coord = c + Integer.toString(i);
                assertValid(coord);
                assertValid(coord);
            }
        }
    }
    
    
    // Valid a1
    @Test
    public void validInputa1()
    {
        assertValid(5);
    }
    
    // Valid a10
    @Test
    public void validInputa10()
    {
        assertValid(6);
    }
    
    // Valid j1
    @Test
    public void validInputj1()
    {
        assertValid(7);
    }
    
    // Valid j10
    @Test
    public void validInputj10()
    {
        assertValid(8);
    }
    
    // Valid all (lower loop)
    @Test
    public void validInputLowerLoop()
    {
        for(char c = 'a'; c < 'k'; c++)
        {
            for (int i = 1; i < 11; i++)
            {
                String coord = c + Integer.toString(i);
                assertValid(coord);
                assertValid(coord);
            }
        }
    }
    
    
    // Invalid A0
    @Test
    public void invalidInputA0()
    {
        assertInvalid(9);
    }
    
    // Invalid A11
    @Test
    public void invalidInputA11()
    {
        assertInvalid(10);
    }
    
    // Invalid K0
    @Test
    public void invalidInputK0()
    {
        assertInvalid(11);
    }
    
    // Invalid K1
    @Test
    public void invalidInputK1()
    {
        assertInvalid(12);
    }
    
    // Invalid K10
    @Test
    public void invalidInputK10()
    {
        assertInvalid(13);
    }
    
    // Invalid K11
    @Test
    public void invalidInputK11()
    {
        assertInvalid(14);
    }
    
    // Invalid all K-Z (loop)
    @Test
    public void invalidLoop()
    {
        for(char c = 'K'; c <= 'Z'; c++)
        {
            for (int i = 1; i < 11; i++)
            {
                String coord = c + Integer.toString(i);
                assertInvalid(coord);
                assertInvalid(coord);
            }
        }
    }
    
    // Invalid all k-z (lower loop)
    @Test
    public void invalidLowerLoop()
    {
        for(char c = 'k'; c <= 'z'; c++)
        {
            for (int i = 1; i < 11; i++)
            {
                String coord = c + Integer.toString(i);
                assertInvalid(coord);
                assertInvalid(coord);
            }
        }
    }
    
    // Invalid unavailable
    @Test
    public void invalidUnavailable()
    {
        testBoard.move(0, 0);
        assertInvalid(0);
    }
    
    // Invalid all num
    @Test
    public void invalidAllNum()
    {
        assertInvalid(15);
    }
    
    // Invalid all char
    @Test
    public void invalidAllChar()
    {
        assertInvalid(16);
    }
    
    // Invalid wrong order
    @Test
    public void invalidOrder()
    {
        assertInvalid(17);
    }
    
    // Invalid missing coordinate
    @Test
    public void invalidMissingCoordinate()
    {
        assertInvalid(18);
    }
    
    // Invalid too many char coords
    @Test
    public void invalidExtraCharacterCoordinate()
    {
        assertInvalid(19);
    }
    
    // Invalid too many characters
    @Test
    public void invalid3DGame()
    {
        assertInvalid(20);
    }
    
    
    private void assertValid(String coord)
    {
        assertTrue(validator.validate(coord));
    }
    
    private void assertValid(int index)
    {
        assertValid(TEST_INPUTS[index]);
    }
    
    
    private void assertInvalid(String coord)
    {
        assertFalse(validator.validate(coord));
    }
    
    private void assertInvalid(int index)
    {
        assertInvalid(TEST_INPUTS[index]);
    }
    
    
    @AfterEach
    public void shutdown()
    {
        Input.getInstance().close();
    }
}