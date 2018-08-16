package com.revature.screenstwo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.revature.screens.BankingHomeScreen;
import com.revature.screens.CurrentUser;
import com.revature.screens.Screen;
import com.revature.user.User;

public class TransactionHistoryScreen implements Screen{
	private User user = CurrentUser.current;
	private String filePath = "src/main/resources/transactionHistory/" + user.getUsername() + ".txt";
	public Screen start(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath ));
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
		return new BankingHomeScreen();
	}
}
