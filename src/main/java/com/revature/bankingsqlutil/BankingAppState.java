package com.revature.bankingsqlutil;

import com.revature.bankingsqlbeans.User;
import com.revature.bankingsqlutil.BankingAppState;

public class BankingAppState {
	public static final BankingAppState state = new BankingAppState();
	private User currentUser;
	
	private BankingAppState() {
		
	}
	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}
