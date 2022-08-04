package Battleship.GameIO;


import Battleship.game.MoveHistory;

import sun.misc.Signal;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Writes the full <code>MoveHistory</code> to a file.
 *
 * @see MoveHistory
 *
 * @author Garrett Kamila Crayton
 * @version 0.2.0
 * @since 0.2.0
 */
public class MoveHistoryWriter
{
	private static final String DT_FORMAT_STRING = "YYYY/MM/DD HH:mm:ss";
	private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern(DT_FORMAT_STRING);
	/**
	 * The path to the directory all the log files will be stored to
	 */
	public static String LOG_DIR_PATH;
	
	private PrintWriter gameLog;
	private MoveHistory history;
	
	
	/**
	 * Class constructor
	 *
	 * @param history the <code>MoveHistory</code> to be written
	 */
	public MoveHistoryWriter(MoveHistory history)
	{
		this.history = history;
		init();
	}
	
	private void init()
	{
		getLogPath();
		
		createFile();
		
		Signal.handle(new Signal("INT"), signal -> interrupt());
	}
	
	private static void getLogPath()
	{
		if(LOG_DIR_PATH == null)
		{
			String os = System.getProperty("os.name");
			if(os.equalsIgnoreCase("win"))
			{
				LOG_DIR_PATH = System.getenv("AppData");
			}
			else
			{
				LOG_DIR_PATH = System.getProperty("user.home");
				LOG_DIR_PATH += File.separator + "Library" + File.separator + "Application Support";
			}
			
			LOG_DIR_PATH += File.separator + "Battleship" + File.separator + "logs";
		}
	}
	
	private void createFile()
	{
		String name = "game";
		
		checkDir();
		
		File logDir = new File(LOG_DIR_PATH);
		File[] logs = logDir.listFiles();
		
		if(logs.length > 0)
		{
			name += logs.length;
		}
		
		name += ".log";
		
		String logPath = LOG_DIR_PATH + File.separator + name;
		
		File logFile = new File(logPath);
		setFile(logFile);
		
		
		gameLog.print("Game Start: ");
		writeTime();
		gameLog.println("");
	}
	
	private void checkDir()
	{
		File logDir = new File(LOG_DIR_PATH);
		
		logDir.mkdirs();
	}
	
	
	/**
	 * Returns the <code>PrintWriter</code> that writes to the output <code>File</code>
	 *
	 * @return the <code>PrintWriter</code> that writes to the output <code>File</code>
	 */
	public PrintWriter getGameLog()
	{
		return gameLog;
	}
	
	/**
	 * Set the output <code>File</code> to the specified <code>File</code>
	 *
	 * This is purely for testing coverage purposes. The system should never have to call this.
	 *
	 * @param file the new <code>File</code> to be written to
	 */
	public void setFile(File file)
	{
		if(gameLog != null)
		{
			gameLog.close();
		}
		
		try
		{
			gameLog = new PrintWriter(file);
		}
		catch (Exception e)
		{
			gameLog = null;
		}
	}
	
	
	/**
	 * Write the <code>MoveHistory</code> to the output <code>File</code>
	 */
	public void writeHistory()
	{
		gameLog.println("\t   Moves\n===================");
		
		int moveNum;
		String historyStr;
		for(int i = 0; i < history.size(); i += 2)
		{
			moveNum = (i / 2) + 1;
			historyStr = String.format("%3d. %6s", moveNum, history.get(i));
			
			if(history.size() > (i + 1))
			{
				historyStr += String.format(", %6s", history.get(i + 1));
			}
			
			gameLog.println(historyStr);
		}
	}
	
	private void writeTime()
	{
		String timeStr = DT_FORMATTER.format(LocalDateTime.now());
		
		gameLog.println(timeStr);
	}
	
	/**
	 * Catches any Interrupt signals
	 */
	private void interrupt()
	{
		writeHistory();
		gameLog.println("");
		gameLog.print("Game was interrupted at: ");
		writeTime();
		
		gameLog.close();
	}
	
	/**
	 * Close the output <code>File</code>
	 */
	public void close()
	{
		gameLog.println("\n");
		gameLog.print("Game End: ");
		writeTime();
		gameLog.close();
	}
}