package com.revature.bankingsqlscreens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankingsqldaos.BankingUserDao;
import com.revature.bankingsqlutil.BankingAppState;
import com.revature.bankingsqlbeans.User;
import com.revature.bankingsqlscreens.HomeScreen;
import com.revature.bankingsqlscreens.RegisterUserScreen;
import com.revature.bankingsqlscreens.Screen;

public class LoginScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private BankingUserDao bud = BankingUserDao.currentUserDao;
	private Logger log = Logger.getRootLogger();
	private BankingAppState state = BankingAppState.state;
	
	@Override
	public Screen start() {
		log.debug("started login screen");
		System.out.println("Enter username or type REGISTER to sign up: ");
		String username = scan.nextLine();
		if ("register".equalsIgnoreCase(username)) {
			return new RegisterUserScreen();
		}
		if("admin".equalsIgnoreCase(username)) { 
			return new AdminLoginScreen();
		}
		//create and load admin table and data, admindao, track admin
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		
		log.debug("receives users credentials");
		User currentUser = bud.findUsernameAndPassword(username, password);
		
		if(currentUser != null) {
			state.setCurrentUser(currentUser);
			log.info("user successfully logged in");
			return new HomeScreen();
		} else {
			log.info("invalid new user credentials");
		}
		System.out.println("unable to login");
		return this;
	}
}
