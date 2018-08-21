package com.revature.bankingsqllauncher;

import org.apache.log4j.Logger;

import com.revature.bankingsqlscreens.LoginScreen;
import com.revature.bankingsqlscreens.Screen;

public class Launcher {
	private static Logger log = Logger.getRootLogger();
	public static void main(String[] args) {
		Screen s = new LoginScreen();
		log.trace("Beginning application");
		
		while (true) {
			s = s.start();
		}
	}
}
