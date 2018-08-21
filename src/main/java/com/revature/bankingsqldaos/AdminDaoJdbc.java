package com.revature.bankingsqldaos;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.bankingsqlbeans.Admin;
import com.revature.bankingsqlutil.ConnectionUtil;

public class AdminDaoJdbc implements AdminDao{
	
	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Admin findAdminUsernameAndPassword(String username, String password) {
			try (Connection conn = cu.getConnection()){
			
				PreparedStatement ps = conn.prepareStatement(
						"SELECT * FROM admin_info WHERE admin_username=? AND admin_pass_word=?");
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					Admin a = new Admin();
					a.setAdminID(rs.getInt("admin_id"));
					a.setAdminUsername(rs.getString("admin_username"));
					a.setLastName(rs.getString("admin_lastname"));
					a.setFirstName(rs.getString("admin_firstname"));
					return a;
				} else {
					log.warn("failed to find admin with provided credentials from the db");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	@Override
	public void adminDeleteUser(String username) {
		
		String filePath = "src/main/resources/transactionHistory/" + username + ".txt";
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM user_info WHERE username=?");
			ps.setString(1, username);
			int recordsDelete = ps.executeUpdate();
			log.trace(recordsDelete + " user deleted from database");
			
		}catch(SQLException e) {
			log.warn("Unable to delete user from database");
		}
	}
}
