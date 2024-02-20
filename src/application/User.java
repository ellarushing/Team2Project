package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {

	private String username;

	public User(String user_name) {
		username = user_name;
		
	}

	int searchUser(String user_name) { // return 1 if user found, return 0 if not found, then create account
		int searchResult = 0; // initialize to not found 
		try {
			File users = new File("users.txt"); // load users.txt file to check if user exists
			Scanner fileScanner = new Scanner(users);
			
			while(fileScanner.hasNext()) { // if next entry of file is not null copy result to currentUser variable
				String currentUser = fileScanner.next();
				
				if(currentUser.equals(username))
				{
					searchResult = 1; // user found set search result to 1 so caller method knows not to create a new account
					
				}
			}
			fileScanner.close(); // close file scanner	
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return searchResult;
		
	}
	void goToPortal(int userType) // 1 for patient, 2 for nurse, 3 for doctor
	{
		
	}
	
	void createAccount(String user_name) // create account with username passed/ ask for password 
	{
		
	}

}
