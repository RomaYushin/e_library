package ua.kharkiv.yushin.command;

import java.util.Map;
import java.util.TreeMap;

/**
 * One part of command pattern. Contains all available commands and suitable
 * classes for each
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 * @see ICommand
 *
 */
public class CommandContainer {
	/**
	 * map, containt command as key and new class as value
	 */
	private static Map<String, ICommand> commands = new TreeMap<String, ICommand>();

	/**
	 * initialize commands variable
	 */
	static {
		commands.put("add", new AddBookCommand());
		commands.put("remove", new RemoveBookCommand());
		commands.put("edit", new EditBookCommand());
		commands.put("all", new AllBooksCommand());
		commands.put("help", new HelpCommand());
	}

	/**
	 * get particular instance
	 * 
	 * @param commandName
	 *            - name of the command
	 * @return particular instance
	 */
	public static ICommand getCommand(String commandName) {
		return commands.get(commandName);
	}
}
