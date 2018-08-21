package com.revature.bankingsqldaos;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankingsqlbeans.BankAccount;
import com.revature.bankingsqlbeans.User;
import com.revature.bankingsqlscreens.LoginScreen;
import com.revature.bankingsqlscreens.Screen;
import com.revature.bankingsqlscreens.UpdateInfo;
import com.revature.bankingsqlutil.ConnectionUtil;

public class BankingDaoJdbc implements BankingUserDao {
	
	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createUser(User u) {
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO user_info (user_id, username, lastname, firstname, address, balance, age, pass_word) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setInt(1, u.getUserID());
				ps.setString(2, u.getUsername());
				ps.setString(3, u.getLastName());
				ps.setString(4, u.getFirstName());
				ps.setString(5,  u.getAddress());
				ps.setInt(6, u.getBalance());
				ps.setInt(7, u.getAge());
				ps.setString(8, u.getPassword());
				
				int recordsCreated = ps.executeUpdate();
				log.trace(recordsCreated + " recored created");
		}catch (SQLException e){
			log.warn("failed to create new user");
			e.printStackTrace();
		}
	}
	public void createAccount(BankAccount b, User u) {
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO account_info (account_id, balance, user_id) VALUES(?, ?, ?)");
			ps.setInt(1, b.getAccountID());
			ps.setInt(2, u.getBalance());
			ps.setInt(3, u.getUserID());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " recored created");
		} catch (SQLException e) {
			log.warn("failed to create new account");
			e.printStackTrace();
		}
	}
	public User findUsernameAndPassword(String username, String password) {//Copy parts for admin
		try (Connection conn = cu.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM user_info WHERE username=? AND pass_word=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User u = new User();
				u.setAge(rs.getInt("age"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setUserID(rs.getInt("user_id"));
				u.setAddress(rs.getString("address"));
				u.setBalance(rs.getInt("balance"));
				return u;
			}else {
				log.warn("failed to find user with provided credentials from the db");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public void updateAddress(User u, String address) {
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE user_info SET address=? WHERE username=?");
			ps.setString(1, address);
			ps.setString(2, u.getUsername());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + "recored created");
		} catch (SQLException e) {
			log.warn("Could not update user address");
		}
		
	}
	@Override
	public void updateBalance(User u, int balance, int userID) {//add user_id parameter UPDATE account_info
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE user_info SET balance=? WHERE username=?");//add second update statement for account_info
			ps.setInt(1, balance);
			ps.setString(2, u.getUsername());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " recored created");
		} catch (SQLException e) {
			log.warn("Could not update user balance");
		}
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE account_info SET balance=? WHERE user_id=?");//add second update statement for account_info
			ps.setInt(1, balance);
			ps.setInt(2, u.getUserID());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " recored created");
		} catch (SQLException e) {
			log.warn("Could not update account balance");
		}
	}
	
	@Override
	public void changePassword(User u, String password) {
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE user_info SET pass_word=? WHERE username=?");
			ps.setString(1, password);
			ps.setString(2, u.getUsername());
			int recordsDelete = ps.executeUpdate();
			log.trace(recordsDelete + " user password updated");
			
		}catch(SQLException e) {
			log.warn("Unable to change user's password");
		}
		
	}
	
	@Override
	public Screen deleteUser(User u) {
		
		System.out.println("Are you sure you want delete your account. All accounts attributed to you will be removed: ");
		System.out.println("Enter 1 for yes");
		System.out.println("Enter any other key for no");
		String selection = scan.nextLine();
		
		if (selection == "1") {
			
				String filePath = "src/main/resources/transactionHistory/" + u.getUsername() + ".txt";
				File f = new File(filePath);
				if (f.exists()) {
					f.delete();
				}
				try (Connection conn = cu.getConnection()){
					PreparedStatement ps = conn.prepareStatement(
							"DELETE FROM user_info WHERE username=?");
					ps.setString(1, u.getUsername());
					int recordsDelete = ps.executeUpdate();
					log.trace(recordsDelete + " user deleted from database");
					
				}catch(SQLException e) {
					log.warn("Unable to delete user from database");
				}
				return new LoginScreen();
		}
		return new UpdateInfo();
	}
}
