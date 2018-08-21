package com.revature.bankingsqlscreens;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.revature.bankingsqlutil.BankingAppState;

public class TransactionHistoryScreen implements Screen{
	
	private BankingAppState state = BankingAppState.state;
	
	@Override
	public Screen start() {
		String filePath = "src/main/resources/transactionHistory/" + state.getCurrentUser().getUsername() + ".txt";
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
		return new HomeScreen();
	}

}
