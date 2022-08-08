package Battleship.Controller;


import Battleship.GameIO.Input;
import Battleship.GameIO.Output;
import Battleship.GameIO.Validators.InputValidator;


/**
 * Abstract class that handles overall turn operations
 *
 * @see Input
 * @see Output
 * @see InputValidator
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.2
 * @since 0.1.0
 */
public abstract class TurnController
{
	private Input in;
	private Output out;
	
	/** The <code>Validator</code> for this class */
	protected InputValidator validator;
	
	
	/**
	 * Class constructor
	 *
	 * @param validator the <code>Validator</code> for this class
	 */
	protected TurnController(InputValidator validator)
	{
		this.in = Input.getInstance();
		this.out = Output.getInstance();
		this.validator = validator;
	}
	
	
	/**
	 * Read a line from <code>Input</code>
	 *
	 * @return a line of input as a <code>String</code>
	 */
	protected String readLine()
	{
		return in.readLine();
	}
	
	/**
	 * Write a <code>String</code> to output
	 *
	 * @param output the <code>String</code> to output
	 */
	protected void print(String output)
	{
		out.print(output);
	}
	
	/**
	 * Write a line to output
	 *
	 * @param output output the <code>String</code> to output
	 */
	protected void println(String output)
	{
		out.println(output);
	}
	
	/**
	 * Validate the given input
	 *
	 * @see InputValidator#validate(String)
	 *
	 * @param coordStr the coordinate as a <code>String</code>
	 * @return <code>true</code> if the coordinate given is valid, <code>false</code> otherwise
	 */
	protected boolean validate(String coordStr)
	{
		return validator.validate(coordStr);
	}
}