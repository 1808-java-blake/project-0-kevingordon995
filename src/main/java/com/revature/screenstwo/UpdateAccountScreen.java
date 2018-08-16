package com.revature.screenstwo;

import java.text.DecimalFormat;
import java.util.Scanner;
import com.revature.bankingdaos.BankingUserDAO;
import com.revature.screens.BankingHomeScreen;
import com.revature.screens.CurrentUser;
import com.revature.screens.LoginScreen;
import com.revature.screens.Screen;
import com.revature.user.User;

public class UpdateAccountScreen implements Screen {
	BankingUserDAO bud = BankingUserDAO.currentUserDao;
	private Scanner scan = new Scanner(System.in);
	User user = CurrentUser.current;
	DecimalFormat df = new DecimalFormat("#.00");
	@Override
	public Screen start() {
		System.out.println("Choose what info you need to update");
		System.out.println("Enter 1 to update address");
		System.out.println("Enter 2 to change password");
		System.out.println("Enter 3 to delete your account");
		System.out.println("Enter 4 to go back to Home");
		
		String selection = scan.nextLine();
		//bud.updateUser(user);
		switch (selection) {
		case "1":
			changeAddress(user.getAddress());
			return this;
		case "2":
			changePassword(user.getPassword());
			return this;
		case "3":
			deleteAccount();
			break;
		case "4":
			return new BankingHomeScreen();
		}
		
		return new LoginScreen();
	}
	void changeAddress(String address) {
		System.out.println("Enter new address: ");
		user.setAddress(scan.nextLine());
		bud.updateUser(user);
		System.out.println("Your new address is: " + user.getAddress());
	}
	void changePassword(String password) {
		System.out.println("Enter new password: ");
		user.setAddress(scan.nextLine());
		bud.updateUser(user);
		System.out.println("Your password has been changed");
	}
	Screen deleteAccount() {
		String deleteSelect;
		System.out.println("Are you sure you want to delete your account?");
		System.out.println("Enter 1 for yes");
		System.out.println("Enter 2 for no");
		deleteSelect = scan.nextLine();
		switch(deleteSelect) {
		case "1":
			bud.deleteUser(user);
			break;
		case "2":
			return new UpdateAccountScreen();
		}
		return new LoginScreen();
	}
}
