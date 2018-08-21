package com.revature.bankingsqlscreens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.revature.bankingsqldaos.AdminDao;
import com.revature.bankingsqlutil.AdminState;


public class AdminScreen implements Screen{
	private AdminState state = AdminState.state;
	private Scanner scan = new Scanner(System.in);
	private AdminDao ad = AdminDao.currentAdminDao;
	@Override
	public Screen start() {
		System.out.println("Welcome Administrator " + state.getCurrentAdmin().getLastName() +". Please choose from the following options: ");
		System.out.println("Enter 1 to view list of acounts");
		System.out.println("Enter 2 to view transaction histories");
		System.out.println("Enter 3 to delete account");
		System.out.println("Enter 4 to logout");
		String selection = scan.nextLine();
		
		switch (selection) {
		case "1":
			File folder = new File("src/main/resources/transactionHistory");
			File[] files = folder.listFiles();
			
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					System.out.println(files[i].getName());
				}
			}
			return this;
		case "2":
			System.out.println("Enter username associated with transaction history: ");
			String selected = scan.nextLine();
			String filePath = "src/main/resources/transactionHistory/" + selected + ".txt";	
			try {
				BufferedReader br = new BufferedReader(new FileReader(filePath));
				String st;
				try {
					while ((st = br.readLine()) != null) {
						System.out.println(st);
					}    
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				System.out.println("Unable to read file.");
			}		
			return this;
		case "3":
			System.out.println("Enter username of account to be deleted: ");
			String deleted = scan.nextLine();
			ad.adminDeleteUser(deleted);
		case "4":
			return new LoginScreen();
		default:
			System.out.println("Invalid entry. Try again.");
		}
		return this;
	}

}
