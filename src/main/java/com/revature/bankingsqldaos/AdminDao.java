package com.revature.bankingsqldaos;

import com.revature.bankingsqlbeans.Admin;

public interface AdminDao {
	
	public static final AdminDao currentAdminDao = new AdminDaoJdbc();
	
	Admin findAdminUsernameAndPassword(String username, String password);
	void adminDeleteUser(String username);
}
