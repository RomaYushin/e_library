package ua.kharkiv.yushin.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.kharkiv.yushin.db.MySQLBook;
import ua.kharkiv.yushin.entity.Book;

/**
 * One part of command pattern. Show all books from database
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 * @see CommandContainer
 * @see ICommand
 *
 */
public class AllBooksCommand implements ICommand {

	@Override
	public void execute(String inputString, BufferedReader reader)  throws IOException {

		// get all books from database
		List <Book> books = MySQLBook.getAllBooks();
		// sort by name
		Collections.sort(books, new Comparator<Book>() {
			public int compare (Book b1, Book b2) {
				return b1.getBook_name().compareTo(b2.getBook_name());
			}
		});	
		
		System.out.println("-P: all books sorted by book name:");
		// show all books
		for (Book book: books) {			
			System.out.print("    book id: " + book.getId() + ";");
			System.out.print("    book author: " + book.getAuthor() + ";");
			/* make view more readable. Add empty spaces to author. 
			 * End of line must be in one glad
			 */
			for (int i = 0; i< (30 - book.getAuthor().length()); i++) {
				System.out.print(" ");
			}
			
			System.out.print("    book name: " + book.getBook_name());
			System.out.println("");
		}
	}
}
