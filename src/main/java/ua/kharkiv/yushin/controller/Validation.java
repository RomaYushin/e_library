package ua.kharkiv.yushin.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class with static methods for validate input parametrs
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 */
public class Validation {

	/**
	 * validation of input string
	 * 
	 * @param inputString
	 *            - user request
	 * @return true if input string is valid and false otherwise
	 */
	public static boolean validateInputString(String inputString) {
		if ("all books".equals(inputString)) {
			return true;
		}
		String regExp = "^^(help|add|remove|edit book|all books){1}( ){1}+(.)*(\")+(.)+(\")$";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(inputString);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * validation author string
	 * 
	 * @param author
	 *            - author of book from request
	 * @return true if input string is valid and false otherwise
	 */
	public static boolean validateAuthor(String author) {
		String regExp = "^[a-zA-Z-'. ]+?$";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(author);
		if (matcher.find()) {
			return true;
		}
		return false;

	}

	/**
	 * validation bookName string
	 * 
	 * @param bookName
	 *            - author of book from request
	 * @return true if input string is valid and false otherwise
	 */
	public static boolean validateBookName(String bookName) {
		if (bookName.length() == 0) {
			return false;
		}
		return true;
	}

	/**
	 *  validation book id from request
	 * @param id - book id from user request
	 * @return true if input string is valid and false otherwise
	 */
	public static boolean validateId(String id) {
		String regExp = "^\\d+$";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(id);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
}
