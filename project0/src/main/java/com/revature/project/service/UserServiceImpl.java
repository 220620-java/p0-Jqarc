package com.revature.project.service;

import com.revature.project.data.AccountDAO;
import com.revature.project.data.AccountPostgres;
import com.revature.project.data.UserDAO;
import com.revature.project.data.UserPostgres;
import com.revature.project.ds.List;
import com.revature.project.exceptions.UsernameAlreadyExistsException;
import com.revature.project.models.Account;
import com.revature.project.models.User;

public class UserServiceImpl implements UserService{
	private UserDAO userDao = new UserPostgres();
	private AccountDAO accDao = new AccountPostgres();
	
	@Override
	public User registerUser(User user) throws UsernameAlreadyExistsException {
		
		// Try to find the username if it already exist
		User tempUser = userDao.findByUsername(user.getUsername());
		
		if(user.getUsername() == tempUser.getUsername()) {
			System.out.println("Username already exists");
			throw new UsernameAlreadyExistsException();
		} else if (user.getUsername() == null) {
			System.out.println("You have not entered a username");
			return null;
		} else {
			user = userDao.create(user);
			return user;
		}
		
		
		
		/*
		// Create new user
		user = userDao.create(user);
		
		// See if username already exists
		if (user.getUsername() == tempUser.getUsername()) {
			System.out.println("Username is taken!");
			throw new UsernameAlreadyExistsException();
		} 
		// See if the user entered anything for username
		else if (user.getUsername() == null) {
			return null;
		} 
		// 
		else {
			return user;
		}
		
		/*
		user = userDao.create(user);
		if (user == null) {
			throw new UsernameAlreadyExistsException();
		}
		return user;
		*/
	}

	@Override
	public User logIn(String username, String password) {
		User user = userDao.findByUsername(username);
		if (user != null && (password!=null && password.equals(user.getPassword()))) {
			return user;
		}
		return null;
	}

	@Override
	public List<Account> viewMyAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
