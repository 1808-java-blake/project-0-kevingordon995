package com.revature.bankingdaos;

import com.revature.user.User;
import com.revature.bankingdaos.BankingUserDAO;
import com.revature.bankingdaos.BankingUserSerializer;

public interface BankingUserDAO {
public static final BankingUserDAO currentUserDao = BankingUserSerializer.bus;
	
	void createUser(User u);
	User findUsernameAndPassword(String username, String password);
	void updateUser(User u);
	void deleteUser(User u);
}
