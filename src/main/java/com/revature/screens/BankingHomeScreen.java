package com.revature.screens;


import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.bankingdaos.BankingUserDAO;
import com.revature.screenstwo.AccountScreen;
import com.revature.screenstwo.TransactionHistoryScreen;
import com.revature.screenstwo.UpdateAccountScreen;
import com.revature.screenstwo.ViewBalanceScreen;

public class BankingHomeScreen implements Screen {
	BankingUserDAO bud = BankingUserDAO.currentUserDao;
	DecimalFormat df = new DecimalFormat("#.00");
	private Scanner scan = new Scanner(System.in);
	
	public Screen start() {
		System.out.println("Please choose from the following options: ");
		System.out.println("Enter 1 to deposit/withdraw money");
		System.out.println("Enter 2 to view balance");
		System.out.println("Enter 3 to view transaction history");
		System.out.println("Enter 4 to update account information");
		String selection = scan.nextLine();
		
		switch (selection) {
		case "1":
			return new AccountScreen();
		case "2":
			return new ViewBalanceScreen();
		case "3":
			return new TransactionHistoryScreen();
		case "4":
			return new UpdateAccountScreen();
		default:
			System.out.println("Invalid entry. Try again.");
		}
		
		return this;
	}

}
