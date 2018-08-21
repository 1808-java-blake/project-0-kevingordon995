package com.revature.bankingsqlscreens;


import java.util.Scanner;

import com.revature.bankingsqlscreens.AccountScreen;
import com.revature.bankingsqlutil.BankingAppState;

public class HomeScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private BankingAppState state = BankingAppState.state;
	@Override
	public Screen start() {
		System.out.println("Please choose from the following options: ");
		System.out.println("Enter 1 to deposit/withdraw money");
		System.out.println("Enter 2 to view balance");
		System.out.println("Enter 3 to view transaction history");
		System.out.println("Enter 4 to update account information");
		System.out.println("Enter 5 to logout");
		String selection = scan.nextLine();
		
		switch (selection) {
		case "1":
			return new AccountScreen();
		case "2":
			System.out.println("You currently have $" + state.getCurrentUser().getBalance());
			return this;
		case "3":
			return new TransactionHistoryScreen();
		case "4":
			return new UpdateInfo();
		case "5":
			return new LoginScreen();
		default:
			System.out.println("Invalid entry. Try again.");
		}
		
		return this;
	}

}
