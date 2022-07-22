package Testing.UnitTests.GameIO.Validators;


import GameIO.Validators.MoveValidator;
import game.GameBoard.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Tests for the MoveValidator class.
 * <p>
 * Most of the testing that was done for this class was handled in the InputValidator tests.
 * @see MoveValidator
 * @see InputValidatorTest
 */
public class MoveValidatorTest
{
    /* Question for Andrew:
    Would I need to include the tests from InputValidatorTest here as well?
    Or can I just leave them there and get away with just this one?
    (Either text me, or leave a follow-up comment. Whichever you decide)
     */
    private static final String TEST_COORDINATE_STRING = "A1";
    private static final int[] TEST_COORDINATE = {0, 0};
    
    private Board testBoard;
    private MoveValidator testValidator;
    
    
    @BeforeEach
    public void init()
    {
        testBoard = new Board();
        testValidator = new MoveValidator(testBoard);
    }
    
    @Test
    public void validInput()
    {
        assertTrue(testValidator.validate(TEST_COORDINATE_STRING));
    }
    
    @Test
    public void invalidRepeatMove()
    {
        assertTrue(testValidator.validate(TEST_COORDINATE_STRING));
        testBoard.move(TEST_COORDINATE[0], TEST_COORDINATE[1]);
        assertFalse(testValidator.validate(TEST_COORDINATE_STRING));
    }
}