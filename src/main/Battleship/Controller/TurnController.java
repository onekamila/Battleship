package Battleship.Controller;


import Battleship.GameIO.CoordinateParser;
import Battleship.GameIO.Input;
import Battleship.GameIO.Output;
import Battleship.GameIO.Validators.InputValidator;


/**
 * Abstract class that handles the overall turn operations
 */
public abstract class TurnController
{
	private Input in;
	private Output out;
	protected InputValidator validator;
	
	protected TurnController(InputValidator validator)
	{
		this.in = Input.getInstance();
		this.out = Output.getInstance();
		this.validator = validator;
	}
	
	
	protected String readLine()
	{
		return in.readLine();
	}
	
	protected void print(String output)
	{
		out.print(output);
	}
	
	protected void println(String output)
	{
		out.println(output);
	}
	
	protected boolean validate(String coordStr)
	{
		return validator.validate(coordStr);
	}
	
	protected int[] parseCoord(String coord)
	{
		return CoordinateParser.parse(coord);
	}
}