package com.revature.screenstwo;

import java.text.DecimalFormat;
import java.util.Scanner;
import com.revature.screens.LoginScreen;
import com.revature.screens.Screen;

public class AdminScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	DecimalFormat df = new DecimalFormat("#.00");
	@Override
	public Screen start() {
		System.out.println("Welcome administrator. Please select the following options:");
		System.out.println("Select 1 to view transaction histories");
		System.out.println("Select 2 to delete user accounts");
		System.out.println("Select 4 to logout");
		
		String selection = scan.nextLine();
		switch (selection ) {
		case "1":
			
			break;
		case "2":
			
			break;
		case "3":
			break;
		case "4":
			return new LoginScreen();
		}
		return this;
	}
	
}
