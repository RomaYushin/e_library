package ua.kharkiv.yushin.command;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * class for show help message to user
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 * @see ICommand
 * @see CommandContainer
 */
public class HelpCommand implements ICommand {

	@Override
	public void execute(String inputString, BufferedReader reader) throws IOException {
		
		System.out.println("-P: You can add new book to library database (db), remove book from db, edit name of the book, and see all books in db.");
		System.out.println("    Example for adding book: -U: add book author \"book name\" (\"-U: add J.Rowling \"Harry Potter\") ");
		System.out.println("    Example for removing book: -U: remove \"book name\" (\"-U: remove \"Harry Potter\") ");
		System.out.println("    Example for editing book: -U: edit \"book name\" (\"-U: edit \"Harry Potter\"\"), ");
		System.out.println("       than you should enter new name of book: \"-U: Tanja Grotter\" for example.");
		System.out.println("    If there are few books with the same name you should enter id (number) of the book, which will be proposed.");
		System.out.println("    Example for show all books: -U: all books. List of all books from db will appear.");
		System.out.println("    Also you can enter help command for showing examples of available commands. For example: -U: help");
		System.out.println("    For stop application enter stop command in any cays. For example: -U: stop\n");
		
		System.out.println("    Short stack of commands:");
		System.out.println("    -U: add book author \"book name\"");
		System.out.println("    -U: remove \"book name\"");
		System.out.println("    -U: edit \"book name\"");
		System.out.println("    -P: 	for editing you should enter new book name: ");
		System.out.println("    -U: all books");
		System.out.println("    -U: help");
		System.out.println("    -U: stop");
	}
}
