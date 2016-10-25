package ua.kharkiv.yushin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ua.kharkiv.yushin.command.CommandContainer;
import ua.kharkiv.yushin.command.ICommand;

/**
 * class with one method, which distributes user request to particular
 * class-realisation. Part of realization command pattern.
 * 
 * @author Yushin Roman
 * @version 25.10.2016
 */
public class Controller {

	/**
	 * input string get information from System.in stream with user request
	 */
	private String inputString;

	/**
	 * string contains type of command. Can be add, remove, edit, all, help.
	 */
	private String commandName;

	/**
	 * variable of ICommand interface. Get class realisation while app runninng
	 * <code>ICommand command = new AddBookCommand();</code> for example
	 */
	private ICommand command;

	/**
	 * distributes user request to particular class-realisation. Based on
	 * endless loop <code>while(true){}</code>. Exit is "stop" command.
	 */
	public void startApplication() {

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
			
			// greeting messages
			System.out.println("App starts!");
			System.out.println("This is simple console application for managing e-library.\n");
			// call help command in order to show discription before using application
			command = CommandContainer.getCommand("help");
			command.execute(inputString, reader);
			System.out.println("\nLet's start");

			// endless loop. Exit is "stop" command in any cays
			while (true) {
				System.out.print("-U: ");
				inputString = reader.readLine();
				// if input string "stop" exit from application
				if ("stop".equalsIgnoreCase(inputString)) {
					System.out.println("-P: Application is stopped. Good buy!");
					break;
				}
				// show help discription
				if ("help".equalsIgnoreCase(inputString)) {
					command = CommandContainer.getCommand("help");
					command.execute(inputString, reader);
					continue;
				}
				// validate input string
				if (!Validation.validateInputString(inputString)) {
					System.out.println("-P: invalid input string: " + inputString);
					continue;
				}
				
				// get first word-command from input string
				commandName = inputString.split(" ")[0];
				// inizialize command variable with particular class
				command = CommandContainer.getCommand(commandName);
				// carry out execute method in particular class
				command.execute(inputString, reader);
			}
		} catch (IOException e) {
			System.out.println("-P: application stopped.");
			e.printStackTrace();
		}
		// reader.close();
	}

}
