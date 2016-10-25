package ua.kharkiv.yushin.db;

/**
 * Class, which contains constants for class MySQLBook. There are querys for 
 * managing the database
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 */
public class Querys {
	
	public static final String GET_BOOK_OR_BOOKS_BY_BOOK_NAME = "SELECT * FROM `e_library`.`book` WHERE `book_name`= ? ";
	public static final String ADD_BOOK = "INSERT INTO `e_library`.`book` (`author`, `book_name`) VALUES (?, ?);";
	public static final String REMOVE_BOOK = "DELETE FROM `e_library`.`book` WHERE `book_name`= ?;";
	public static final String GET_ALL_BOOKS = "SELECT * FROM `e_library`.`book`;";	
	public static final String EDIT_BOOK = "UPDATE `e_library`.`book` SET `book_name`= ? WHERE `id`= ?;";
}
