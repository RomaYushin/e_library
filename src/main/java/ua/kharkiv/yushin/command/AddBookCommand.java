package ua.kharkiv.yushin.command;

import java.io.BufferedReader;
import java.io.IOException;

import ua.kharkiv.yushin.controller.Validation;
import ua.kharkiv.yushin.db.MySQLBook;
import ua.kharkiv.yushin.entity.Book;

/**
 * One part of command pattern. Realize adding book to the database.
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 * @see CommandContainer
 * @see ICommand
 *
 */
public class AddBookCommand implements ICommand {

	@Override
	public void execute(String inputString, BufferedReader reader)  throws IOException {

		String author = null;
		String bookName = null;
		Book newBook =null;
		String response = null;
		
		// get author and bookName from input string
		author = inputString.split("(add|\")")[1].trim();
		bookName = inputString.split("(add|\")")[2].trim();

		// validation of author string
		if (!Validation.validateAuthor(author)) {
			System.out.println("-P: invalid input string: " + inputString
					+ ". Invalid author. Author can consist of letters, sign \" ' \", and spaces. "
					+ "Try again with the next example: add book author \"book name\"");
			return;
		}
		// validation bookName string
		if (!Validation.validateBookName(bookName)) {
			System.out.println("-P: invalid input string: " + inputString
					+ ". Invalid book name. Book name must contain at last 1 character"
					+ "Try again with the next example: add book author \"book name\"");
			return;
		}
		// create new instance of book
		newBook = new Book(author, bookName);
		// add book to database
		response = MySQLBook.add(newBook);
		System.out.println("-P: " + response);
	}
}
