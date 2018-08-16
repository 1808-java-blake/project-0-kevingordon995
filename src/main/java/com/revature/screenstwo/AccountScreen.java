package com.revature.screenstwo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.screens.BankingHomeScreen;
import com.revature.screens.CurrentUser;
import com.revature.screens.Screen;
import com.revature.user.User;
import com.revature.bankingdaos.BankingUserDAO;
//import com.revature.bankingdaos.BankingUserSerializer;

public class AccountScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	DecimalFormat df = new DecimalFormat("#.00");
	private BankingUserDAO bud = BankingUserDAO.currentUserDao;
	private User user = CurrentUser.current;
	private double balance = user.getBalance();
	private String filePath = "src/main/resources/transactionHistory/" + user.getUsername() + ".txt";
	String statement;
	@Override
	public Screen start() {
		
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
			return new BankingHomeScreen();
		}
		
		return new AccountScreen();
	}
	
	void deposit(double balance) {
		System.out.println("Enter amount you want to deposit: ");
		int deposited = scan.nextInt();
		double previousBalance = user.getBalance();
		this.balance += deposited;
		statement = "You deposited $" + deposited + ".";
		user.setBalance(this.balance);
		bud.updateUser(user);
		//Write statement to transaction history	
		try {
			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Previous Balance: " + previousBalance + " :Deposited $" + deposited + ", Total: " + user.getBalance());
			bw.newLine();
			bw.close();
		}catch(Exception e) {
			System.out.println("Unable to log deposit into history");
		}	 
		System.out.println("Your balance is now: $" + this.balance);
	}
	void withdraw(double balance) {
		System.out.println("Enter amount you want to withdraw: ");
		int withdrew = scan.nextInt();
		double previousBalance = user.getBalance();
		this.balance -= withdrew;
		statement = "You withdrew $" + withdrew + ".";
		user.setBalance(this.balance);
		bud.updateUser(user);
		//Write statement to transaction history
		try {
			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Previous Balance: " + previousBalance + " :Withdrew $" + withdrew + ", Total: " + user.getBalance());
			bw.newLine();
			bw.close();
		}catch(Exception e) {
			System.out.println("Unable to log withdrawal into history");
		}			 
		System.out.println("Your balance is now: $" + this.balance);
	}
}
