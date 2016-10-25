package ua.kharkiv.yushin.main;


import ua.kharkiv.yushin.controller.Controller;

/**
 * Class main contains only one method for start application.
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 *
 */
public class Main {

	/**
	 * JVM start to run application with method main
	 * 
	 * @param args
	 *            an array of strings, come from outside application
	 */
	public static void main(String[] args) {
		new Controller().startApplication();
	}
}
