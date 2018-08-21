package com.revature.bankingsqlutil;

import com.revature.bankingsqlbeans.Admin;

public class AdminState {
	public static final AdminState state = new AdminState();
	private Admin currentAdmin;
	
	private AdminState() {
		
	}
	/**
	 * @return the currentAdmin
	 */
	public Admin getCurrentAdmin() {
		return currentAdmin;
	}

	/**
	 * @param currentUser the currentAdmin to set
	 */
	public void setCurrentAdmin(Admin currentAdmin) {
		this.currentAdmin = currentAdmin;
	}
}
