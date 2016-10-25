package ua.kharkiv.yushin.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ua.kharkiv.yushin.controller.Validation;
import ua.kharkiv.yushin.db.MySQLBook;
import ua.kharkiv.yushin.entity.Book;

/**
 * One part of command pattern. Realize edditing book in the database.
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 * @see ICommand
 * @see CommandContainer
 *
 */
public class EditBookCommand implements ICommand {

	@Override
	public void execute(String inputString, BufferedReader reader) throws IOException {

		String response = null;
		String newBookName = null;
		String currentName = null;
		List<Book> books = null;
		String bookId_string = null;
		Set<Integer> setOfId = new HashSet<Integer>();

		// for edit book needs new book name. This question is for user
		System.out.print("-P:     for editing you should enter new book name: ");
		newBookName = reader.readLine();
		// add newBookName to inputString
		inputString = inputString + " " + newBookName;
		// get current name of book
		currentName = inputString.split("(edit|\")")[2].trim();
		// validation current book name
		if (!Validation.validateBookName(currentName)) {
			System.out.println("-P: invalid current book name " + currentName);
			return;
		}
		// validation new book name
		if (!Validation.validateBookName(newBookName)) {
			System.out.println("-P: invalid new book name " + newBookName);
			return;
		}
		// get list of book from database. Can be one or few books.
		books = MySQLBook.getBookByBookName(currentName);

		/* 
		 * If one book, edit name. 
		 * If few books ask question to user which one he (she) want to edit
		 */
		if (books.size() == 1) {
			response = MySQLBook.edit(books.get(0).getId(), newBookName);
		} else if (books.size() > 1) {
			System.out.println("-P: There are few books with name \"" + currentName + "\":");
			for (Book book : books) {
				setOfId.add(book.getId());
				// show all books
				System.out.print("    book id: " + book.getId() + ";");
				System.out.print("    book author: " + book.getAuthor() + ";");
				/* make view more readable. Add empty spaces to author. 
				 * End of line must be in one glad
				 */
				for (int i = 0; i < (30 - book.getAuthor().length()); i++) {
					System.out.print(" ");
				}
				System.out.print("    book name: " + book.getBook_name());
				System.out.println("");
			}
			System.out.println("");
			System.out.print("    Which book's name do you want to change? Enter id of this book: ");
			// ask question about number of book for edit
			bookId_string = reader.readLine();
			// validate bookId_string
			if (!Validation.validateId(bookId_string)) {
				System.out.println("-P: invalid input id of book, try again!");
				return;
			}
			// cast to int
			int bookId = Integer.parseInt(bookId_string);
			// check if this number in setOfId
			if (!setOfId.contains(bookId)) {
				System.out.println("-P: invalid input id of book, try again!");
				return;
			}
			// make editing
			response = MySQLBook.edit(bookId, newBookName);
		}
		System.out.println("-P: " + response);
	}
}
