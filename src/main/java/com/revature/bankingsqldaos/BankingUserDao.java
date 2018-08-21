package com.revature.bankingsqldaos;

import com.revature.bankingsqlbeans.User;
import com.revature.bankingsqlbeans.BankAccount;
import com.revature.bankingsqldaos.BankingUserDao;
import com.revature.bankingsqlscreens.Screen;


public interface BankingUserDao {
public static final BankingUserDao currentUserDao = new BankingDaoJdbc();
	
	void createUser(User u);
	void createAccount(BankAccount b, User u);
	User findUsernameAndPassword(String username, String password);
	void updateAddress(User u, String address);
	void updateBalance(User u, int balance, int userID);
	void changePassword(User u, String password);
	Screen deleteUser(User u);
}
