package com.revature.bankingdaos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.revature.bankingdaos.BankingUserSerializer;
import com.revature.user.User;

public class BankingUserSerializer implements BankingUserDAO{
	public static final BankingUserSerializer bus = new BankingUserSerializer();
	
	public BankingUserSerializer() {
		super();
	}
	@Override
	public void createUser(User u) {
		if (u == null) {
			System.out.println("cannot create null user");
			return;
		}
		File f = new File("src/main/resources/users/" + u.getUsername() + ".txt");
		if (f.exists()) {
			System.out.println("username already exists");
			return;
		}
		File f2 = new File("src/main/resources/users/" + u.getUsername() + ".txt");
		try {
			f.createNewFile();
			f2.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("failed to create file for new user");
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("src/main/resources/users/" + u.getUsername() + ".txt"))) {
			oos.writeObject(u);
			System.out.println("created user");
		} catch (FileNotFoundException e) {
			System.out.println("could not find user's file");
		} catch (IOException e) {
			System.out.println("failed to create user profile");
		}
	}

	@Override
	public User findUsernameAndPassword(String username, String password) {
		if (username == null || password == null) {
			System.out.println("invalid credentials");
			return null;
		}
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src/main/resources/users/" + username + ".txt"))) {
			try {
				User u = (User) ois.readObject();
				if (password.equals(u.getPassword())) {
					System.out.println("successfully logged in");
					return u;
				} else {
					System.out.println("invalid credentials");
					return null;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		
		} catch (FileNotFoundException e) {
			System.out.println("could not retreive user's info");
		} catch (IOException e) {
			System.out.println("failed to retreive user's info");
		}
		return null;
	}

	@Override
	public void updateUser(User u) {
		if (u == null) {
			System.out.println("cannot create null user");
			return;
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/main/resources/users/" + u.getUsername() + ".txt"))) {
			oos.writeObject(u);
			
		} catch (FileNotFoundException e) {
			System.out.println("could not find user's file");
		} catch (IOException e) {
			System.out.println("failed to create user profile");
		}
	}

	@Override
	public void deleteUser(User u) {
		if (u == null) {
			System.out.println("cannot delete null user");
			return;
		}
		String filePath = "src/main/resources/users/" + u.getUsername() + ".txt";
		String filePath2 = "src/main/resources/transactionHistory/" + u.getUsername() + ".txt";
		File f = new File(filePath);
		File f2 = new File(filePath2);
		if (f.exists() && f2.exists()) {
			f.delete();
			f2.delete();
		}
		
	}

}
