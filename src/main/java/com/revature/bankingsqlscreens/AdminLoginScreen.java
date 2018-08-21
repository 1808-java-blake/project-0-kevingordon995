package com.revature.bankingsqlscreens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankingsqlbeans.Admin;
import com.revature.bankingsqldaos.AdminDao;
import com.revature.bankingsqlutil.AdminState;

public class AdminLoginScreen implements Screen {
	Scanner scan = new Scanner(System.in);
	private Logger log = Logger.getRootLogger();
	private AdminDao ad = AdminDao.currentAdminDao;
	private AdminState adState = AdminState.state;
	@Override
	public Screen start() {
		System.out.println("Enter admin username");
		String username = scan.nextLine();
		System.out.println("Enter admin password");
		String password = scan.nextLine();
		
		Admin currentAdmin = ad.findAdminUsernameAndPassword(username, password);
		
		if (currentAdmin != null) {
			adState.setCurrentAdmin(currentAdmin);
			log.info("admin successfully login");
			return new AdminScreen();
		}
		log.debug("receives admin credentials");
		
		return null;
	}
	
}
