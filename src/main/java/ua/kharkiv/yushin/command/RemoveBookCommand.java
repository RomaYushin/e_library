package ua.kharkiv.yushin.command;

import java.io.BufferedReader;
import java.util.List;

import ua.kharkiv.yushin.controller.Validation;
import ua.kharkiv.yushin.db.MySQLBook;
import ua.kharkiv.yushin.entity.Book;

/**
 * One part of command pattern. Realize removing book from the database.
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 * @see ICommand
 * @see CommandContainer
 */
public class RemoveBookCommand implements ICommand {
	
	@Override
	public void execute(String inputString, BufferedReader reader) {
		
		String response = null;
		String bookAuthor = null;
		String bookName = null;
		List<Book> books = null;
		
		// get author and bookname from input string
		bookAuthor = inputString.split("(remove|\")")[1].trim();
		bookName = inputString.split("(remove|\")")[2].trim();

		//validation author
		if (!"".equals(bookAuthor)) {
			System.out.println("-P: invalid input string: " + inputString
					+ ". For remove it should not to write author name: " + bookAuthor
					+ ". Try again with the next example: remove \"book name\"");
			return;
		}
		
		// validation book name
		if (!Validation.validateBookName(bookName)) {
			System.out.println("-P: invalid input string: " + inputString
					+ ". Invalid book name. Book name must contain at last 1 character"
					+ "Try again with the next example: add book author \"book name\"");
		}

		// get books by book name
		books = MySQLBook.getBookByBookName(bookName);
		
		// check if book for removing contains in DB
		if (books.isEmpty()) {
			System.out.println("-P: book with name: \"" + bookName + "\" doesn't exist in db");
			return;
		}
		// make removing
		response = MySQLBook.remove(bookName);
		System.out.println("-P: " + response);
	}
}
