package constants;

/**
 * This Enum is used to define the various logging levels that are used to log a message during the game.
 * ERROR- Used while handling exceptions.
 * DEBUG- Used while testing to log messages for debugging.
 * INFO- Used to log any information such as a successful move, while playing.
 * WARN- Used to log warnings like incorrect user inputs, unsuccessful moves.
 * 
 * @author Shivani
 *
 */
public enum LogLevel {

	ERROR, 
	DEBUG, 
	INFO, 
	WARN;
	
}
