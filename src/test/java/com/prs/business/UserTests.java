package com.prs.business;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.business.User;
import com.prs.db.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testUserGetAll() {
		Iterable<User> users = userRepository.findAll();
		assertNotNull(users);
		
	}
	
	@Test
	public void testUserAddAndDelete() {
		User u = new User("username", "password", "firstname", "lastname", "phone", "email", true, true);
		//save a user
		assertNotNull(userRepository.save(u));
		// assert that last name is correct
		assertEquals("lastname", u.getLastName());
		//delete the user
		userRepository.delete(u);
		//confirm user deletion by getting the user by id
		assertFalse(userRepository.findById(u.getId()).isPresent());
	}

}
