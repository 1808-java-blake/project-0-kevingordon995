package com.revature.bankingsqlscreens;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Random;

import com.revature.bankingsqlbeans.User;
import com.revature.bankingsqlbeans.BankAccount;
import com.revature.bankingsqldaos.BankingUserDao;
import com.revature.bankingsqlscreens.LoginScreen;

public class RegisterUserScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private BankingUserDao bud = BankingUserDao.currentUserDao;
	
	@Override
	public Screen start() {
		User u = new User();
		Random rand = new Random();
		u.setUserID(rand.nextInt(100000) + 300000);
		BankAccount b = new BankAccount();
		System.out.println("Enter new username");
		u.setUsername(scan.nextLine());
		System.out.println("Enter password");
		u.setPassword(scan.nextLine());
		System.out.println("Enter first name");
		u.setFirstName(scan.nextLine());
		System.out.println("Enter last name");
		u.setLastName(scan.nextLine());
		System.out.println("Enter age");
		String age = scan.nextLine();
		System.out.println("Enter current address");
		u.setAddress(scan.nextLine());
		System.out.println("Enter starting balance");
		String filePath = "src/main/resources/transactionHistory/" + u.getUsername() + ".txt";
		String balance = scan.nextLine();
		try {
			u.setAge(Integer.valueOf(age));
		} catch (NumberFormatException e) {
			System.out.println("Invalid age");
		}
		try {
			u.setBalance(Integer.valueOf(balance));
		} catch (NumberFormatException e) {
			System.out.println("Invalid starting balance entered");
		}
		b.setAccountID(rand.nextInt(100000) + 500000);
		try {
			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Transaction History for: " + u.getFirstName() + " " + u.getLastName() + " (" + u.getUsername() + ")");
			bw.newLine();
			bw.close();
		}catch(Exception e) {
			System.out.println("Unable to log deposit into history");
		}
			bud.createUser(u);
			bud.createAccount(b, u);
			return new LoginScreen();
		}
	}
