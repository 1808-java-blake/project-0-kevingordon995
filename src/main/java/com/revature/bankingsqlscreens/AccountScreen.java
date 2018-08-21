package com.revature.bankingsqlscreens;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import com.revature.bankingsqldaos.BankingUserDao;
import com.revature.bankingsqlscreens.HomeScreen;
import com.revature.bankingsqlutil.BankingAppState;
import com.revature.bankingsqlscreens.Screen;
import com.revature.bankingsqlscreens.AccountScreen;
import com.revature.bankingsqlbeans.User;

public class AccountScreen implements Screen{

	private Scanner scan = new Scanner(System.in);
	private BankingUserDao bud = BankingUserDao.currentUserDao;
	private BankingAppState state = BankingAppState.state;
	String statement;
	private int balance;
	@Override
	public Screen start() {
		User currentUser = state.getCurrentUser();
		balance = currentUser.getBalance();
		System.out.println("Please choose from the following options: ");
		System.out.println("Enter 1 to deposit money");
		System.out.println("Enter 2 to withdraw");
		System.out.println("Enter 3 to go back to Home");
		
		String selection = scan.nextLine();
		
		switch (selection) {
		case "1":
			deposit(balance);
			break;
		case "2":
			withdraw(balance);
			break;
		case "3":
			return new HomeScreen();
		}
		
		return new AccountScreen();
	}
	
	void deposit(int balance) {
		User currentUser = state.getCurrentUser();
		System.out.println("Enter amount you want to deposit: ");
		int deposited = scan.nextInt();
		int previousBalance = currentUser.getBalance();
		if (previousBalance + deposited >= 10000000) {
			System.out.println("Too much money in account. Congratualations, but withdraw some money before continuing");
		} else {
			this.balance += deposited;
			statement = "You deposited $" + deposited + ".";
			currentUser.setBalance(this.balance);
			bud.updateBalance(currentUser, this.balance, currentUser.getUserID());
		
		//Write statement to transaction history	
			try {
				String filePath = "src/main/resources/transactionHistory/" + currentUser.getUsername() + ".txt";
				FileWriter fw = new FileWriter(filePath, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Previous Balance: $" + previousBalance + " :Deposited $" + deposited + ", Total: " + currentUser.getBalance());
				bw.newLine();
				bw.close();
			}catch(Exception e) {
				System.out.println("Unable to log deposit into history");
			}	 
			System.out.println("Your balance is now: $" + this.balance);
		}
	}
	void withdraw(double balance) {
		User currentUser = state.getCurrentUser();
		System.out.println("Enter amount you want to withdraw: ");
		int withdrew = scan.nextInt();
		double previousBalance = currentUser.getBalance();
		if (withdrew > previousBalance) {
			System.out.println("Insufficient funds. Deposit money first into account");
		} else {
			this.balance -= withdrew;
			statement = "You withdrew $" + withdrew + ".";
			currentUser.setBalance(this.balance);
			bud.updateBalance(currentUser, this.balance, currentUser.getUserID());
		
		//Write statement to transaction history
			try {
				String filePath = "src/main/resources/transactionHistory/" + currentUser.getUsername() + ".txt";
				FileWriter fw = new FileWriter(filePath, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Previous Balance: $" + previousBalance + " :Withdrew $" + withdrew + ", Total: " + currentUser.getBalance());
				bw.newLine();
				bw.close();
			}catch(Exception e) {
				System.out.println("Unable to log withdrawal into history");
			}			 
			System.out.println("Your balance is now: $" + this.balance);
		}
	}
}
