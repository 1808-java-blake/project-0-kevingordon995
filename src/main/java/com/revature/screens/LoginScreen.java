package com.revature.screens;

import java.util.Scanner;
import com.revature.user.User;
import com.revature.bankingdaos.BankingUserDAO;

public class LoginScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private BankingUserDAO bud = BankingUserDAO.currentUserDao;
	public Screen start() {
		System.out.println("Enter username or type register to sign up: ");
		String username = scan.nextLine();
		if ("register".equalsIgnoreCase(username)) {
			return new RegisterNewUserScreen();
		}
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		
		//log.debug("receives users credentials");
		User currentUser = bud.findUsernameAndPassword(username, password);
		//Track current user in another class
		
		if(currentUser != null) {
			//log.info("user successfully logged in");
			CurrentUser.current = currentUser;
			return new BankingHomeScreen();
		}
		System.out.println("unable to login");
		return this;
	}
}
