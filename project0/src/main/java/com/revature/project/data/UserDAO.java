package com.revature.project.data;

import com.revature.project.models.User;

public interface UserDAO extends DataAccessObject<User> {
	public User findByUsername(String username);
	
}
