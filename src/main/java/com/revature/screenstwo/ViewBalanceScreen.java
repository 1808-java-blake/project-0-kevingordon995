package com.revature.screenstwo;


import java.text.DecimalFormat;

import com.revature.screens.BankingHomeScreen;
import com.revature.screens.CurrentUser;
import com.revature.screens.Screen;
import com.revature.user.User;

public class ViewBalanceScreen implements Screen {
	private User user = CurrentUser.current;
	DecimalFormat df = new DecimalFormat("#.00");
	@Override
	public Screen start() {
		System.out.println("Your current balance is: $" + user.getBalance());
		return new BankingHomeScreen();
	}
}
