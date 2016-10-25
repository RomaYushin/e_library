package ua.kharkiv.yushin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.kharkiv.yushin.entity.Book;

/**
 * In this class there are methods for manage database.
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 */
public class MySQLBook {

	/**
	 * URL of database
	 */
	private static String url = "jdbc:mysql://localhost/e_library?useSSL=false";

	/**
	 * database user name
	 */
	private static String name = "root";

	/**
	 * database user password
	 */
	private static String password = "1111";

	/**
	 * instance of class Connection
	 */
	private static Connection connection = null;

	/**
	 * method return a list of books selected by book name from database It can
	 * be only one book in the list or few with the same name
	 * 
	 * @param bookName
	 *            - name of the book
	 * @return list of books selected by book name from database
	 */
	public static List<Book> getBookByBookName(String bookName) {

		List<Book> books = new ArrayList<Book>();

		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(Querys.GET_BOOK_OR_BOOKS_BY_BOOK_NAME)) {
			ps.setString(1, bookName);
			ps.execute();
			ResultSet rs = ps.getResultSet();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt(1));
				book.setAuthor(rs.getString(2));
				book.setBook_name(rs.getString(3));
				books.add(book);
			}
		} catch (SQLException e) {
			System.out.println(Messages.CAN_NOT_GET_BOOK_OR_BOOKS_BY_BOOK_NAME);
		}
		return books;
	}

	/**
	 * method for adding new book to the database. Return message, that adding
	 * was successful or not
	 * 
	 * @param newBook
	 *            - new book for adding
	 * @return message, that adding was successful or not
	 */
	public static String add(Book newBook) {

		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(Querys.ADD_BOOK,
						PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, newBook.getAuthor());
			ps.setString(2, newBook.getBook_name());
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				newBook.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Messages.CAN_NOT_ADD_BOOK;
		}
		return "book \"" + newBook.getAuthor() + " " + newBook.getBook_name() + "\" was successfully added";
	}

	/**
	 * method for removing book from database. Return message, that removing was
	 * successful or not
	 * 
	 * @param bookName
	 *            - book name for removing
	 * @return message, that removing was successful or not
	 */
	public static String remove(String bookName) {
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(Querys.REMOVE_BOOK)) {

			ps.setString(1, bookName);
			ps.execute();

		} catch (SQLException e) {
			return Messages.CAN_NOT_REMOVE_BOOK;
		}
		return "book with name: \"" + bookName + "\" was removed from database successfully.";
	}

	/**
	 * method for editing book in database. Return message, that editing was
	 * successful or not
	 * 
	 * @param idCurrentBook
	 *            - id of book for edit
	 * @param newBookName
	 *            - new name of the book
	 * @return message, that editing was successful or not
	 */
	public static String edit(int idCurrentBook, String newBookName) {

		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(Querys.EDIT_BOOK)) {

			ps.setString(1, newBookName);
			ps.setInt(2, idCurrentBook);
			ps.executeUpdate();

		} catch (SQLException e) {
			return Messages.CAN_NOT_EDIT_BOOK;
		}
		return "book name with id: \"" + idCurrentBook + "\" was edit in database successfully.";
	}

	/**
	 * return list of all books from the database
	 * 
	 * @return list of all books from the database
	 */
	public static List<Book> getAllBooks() {

		List<Book> books = new ArrayList<Book>();

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(Querys.GET_ALL_BOOKS)) {

			ps.execute();
			ResultSet rs = ps.getResultSet();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt(1));
				book.setAuthor(rs.getString(2));
				book.setBook_name(rs.getString(3));
				books.add(book);
			}
		} catch (SQLException e) {
			System.out.println(Messages.CAN_NOT_GET_ALL_BOOKS);
		}
		return books;
	}

	/**
	 * return new connection to the database
	 * 
	 * @return new connection to the database
	 * @throws SQLException
	 */
	private static Connection getConnection() throws SQLException {
		connection = DriverManager.getConnection(url, name, password);
		return connection;
	}
}
