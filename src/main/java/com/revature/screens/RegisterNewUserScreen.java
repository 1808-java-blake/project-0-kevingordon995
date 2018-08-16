package com.revature.screens;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.user.User;
import com.revature.bankingdaos.BankingUserDAO;

public class RegisterNewUserScreen implements Screen {

	private Scanner scan = new Scanner(System.in);
	private BankingUserDAO bud = BankingUserDAO.currentUserDao;
	DecimalFormat df = new DecimalFormat("#.00");
	@Override
	public Screen start() {
		User u = new User();
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
		String money = scan.nextLine();
		try {
			u.setAge(Integer.valueOf(age));
		} catch (NumberFormatException e) {
			System.out.println("Invalid age");
		}
		try {
			u.setBalance(Double.valueOf(money));
		} catch (NumberFormatException e) {
			System.out.println("Invalid starting balance entered");
		}
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
		return new LoginScreen();
	}

}
