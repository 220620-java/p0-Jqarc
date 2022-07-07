package com.revature.project;

import java.util.Scanner;

import com.revature.project.ds.List;
import com.revature.project.exceptions.UsernameAlreadyExistsException;
import com.revature.project.models.Account;
import com.revature.project.models.User;
import com.revature.project.service.UserService;
import com.revature.project.service.UserServiceImpl;

public class ProjectDriver {

	private static Scanner scanner = new Scanner(System.in);
	private static UserService userService = new UserServiceImpl();

	public static void main(String[] args) {
		boolean usingProjectApp = true;
		System.out.println("Welcome to Bank App!");

		User user = null;
		while (usingProjectApp) {
			if (user == null) {
				System.out.println("What would you like to do?\n" 
						+ "1. Log in\n" 
						+ "2. Register\n" 
						+ "x. Quit");
				String input = scanner.nextLine();

				switch (input) {
				case "1":
					user = logIn();
					break;
				case "2":
					register();
					break;
				default:
					usingProjectApp = false;
					System.out.println("Thanks for visiting! See you next time.");
				}
			}

			if (user != null) {
				System.out.println("Welcome, what would you like to do next?");
				System.out.println("1. View my accounts\n" 
						+ "2. Create an account\n" 
						+ "3. Deposit\n"
						+ "4. Withdraw\n"
						+ "x. Log out");
				String input = scanner.nextLine();

				switch (input) {
				case "1":
					viewMyAccount(user);
					break;
				case "2":
					//viewMyBeneficiaries(user);
					break;
				default:
					System.out.println("Loggin out.");
					user = null;
				}
			}
		}
		scanner.close();
	}

	private static User logIn() {
		boolean loggingIn = true;

		while (loggingIn) {
			System.out.println("Enter your username: ");
			String username = scanner.nextLine();
			System.out.println("Enter your password: ");
			String password = scanner.nextLine();

			User user = userService.logIn(username, password);

			if (user == null) {
				System.out.println("Hmm, we couldn't find a user matching those credentials.");
				System.out.println("Do you want to try again? y/n");
				String input = scanner.nextLine().toLowerCase();
				// if they did not say "yes" to trying again
				if (!("y".equals(input))) {
					loggingIn = false;
				}
			} else {
				return user;
			}
		}
		return null;
	}

	private static void register() {
		boolean registering = true;

		while (registering) {
			System.out.println("Enter a username: ");
			String username = scanner.nextLine();
			System.out.println("Enter a password: ");
			String password = scanner.nextLine();

			System.out.println("Type \"y\" to confirm, \"n\" to try again, or something else to go back.");
			String input = scanner.nextLine().toLowerCase();

			switch (input) {
			case "y":
				User user = new User(username, password);
				try {
					userService.registerUser(user);
					registering = false;
					System.out.println("Registering User Success!");
				} catch (UsernameAlreadyExistsException e) {
					System.out.println("Oh no, a user with that username already exists. " + "Let's try again.");
				}
				break;
			case "n":
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				registering = false;
			}
		}
	}
	
	private static void viewMyAccount(User user) {
		List<Account> accounts = user.getAccounts();
		System.out.println("Your Account(s):");
		System.out.println(accounts);
	}
	
	
}
