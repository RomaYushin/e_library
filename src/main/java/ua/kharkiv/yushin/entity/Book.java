package ua.kharkiv.yushin.entity;

/**
 * Class - java bean. Realize entity book
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 */
public class Book {
	
	/**
	 * identifier number in the database
	 */
	private int id;
	
	/**
	 * author of the book
	 */
	private String author;
	
	/**
	 * name of the book
	 */
	private String bookName;
	
	public Book() {
	}
	
	public Book(int id, String author, String bookName) {
		this.id = id;
		this.author = author;
		this.bookName = bookName;
	}

	public Book(String author, String book_name) {
		this.author = author;
		this.bookName = book_name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getBook_name() {
		return bookName;
	}
	
	public void setBook_name(String book_name) {
		this.bookName = book_name;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", bookName=" + bookName + "]";
	}
	
	
	
	
	

}

