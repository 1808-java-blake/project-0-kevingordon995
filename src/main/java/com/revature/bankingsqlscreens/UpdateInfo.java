package com.revature.bankingsqlscreens;

import java.util.Scanner;

import com.revature.bankingsqldaos.BankingUserDao;
import com.revature.bankingsqlscreens.HomeScreen;
import com.revature.bankingsqlutil.BankingAppState;
import com.revature.bankingsqlscreens.LoginScreen;
import com.revature.bankingsqlbeans.User;

public class UpdateInfo implements Screen {
	BankingUserDao bud = BankingUserDao.currentUserDao;
	private Scanner scan = new Scanner(System.in);
	BankingAppState state = BankingAppState.state;
	@Override
	public Screen start() {
		User currentUser = state.getCurrentUser();
		
		System.out.println("Choose what info you need to update");
		System.out.println("Enter 1 to update address");
		System.out.println("Enter 2 to change password");
		System.out.println("Enter 3 to delete your account");
		System.out.println("Enter 4 to go back to Home");
		
		String selection = scan.nextLine();
		//bud.updateUser(user);
		switch (selection) {
		case "1":
			System.out.println("Enter your new address: ");
			String newAddress = scan.nextLine();
			bud.updateAddress(currentUser, newAddress);
			return this;
		case "2":
			System.out.println("Enter new password: ");
			String newPassword = scan.nextLine();
			bud.changePassword(currentUser, newPassword);
			return this;
		case "3":
			bud.deleteUser(currentUser);
		case "4":
			return new HomeScreen();
		}
		
		return new LoginScreen();
	}
}
