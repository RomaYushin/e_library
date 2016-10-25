package ua.kharkiv.yushin.command;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * One part of command pattern. Contains method execute. Each realization of
 * this interface provide one action with database. Such as add new book, remove
 * book, edit book, show all books, show help discription.
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 * @see CommandContainer
 *
 */
public interface ICommand {

	/**
	 * Each realization provides one of action for managinng database
	 * 
	 * @param inputString
	 *            - input string from user request
	 * @param reader
	 *            - instance of BufferedReader. Need in order to ask any
	 *            quastion to user in this method
	 * @throws IOException
	 */
	public void execute(String inputString, BufferedReader reader) throws IOException;
}
