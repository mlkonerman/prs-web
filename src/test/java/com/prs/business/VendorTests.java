package com.prs.business;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.db.VendorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VendorTests {

	@Autowired
	private VendorRepository vendorRepository;

	@Test
	public void testUserGetAll() {
		Iterable<Vendor> vendors = vendorRepository.findAll();
		assertNotNull(vendors);

	}

	@Test
	//NOT WORKING
	public void testVendorAddAndDelete() {
		Vendor v = new Vendor("testcode", "testname", "testaddress", "testcity", "teststate", "testzip", "testphone",
				"testemail", true);
		// save a vendor
		assertNotNull(vendorRepository.save(v));
		// assert that last name is correct
		assertEquals("name", v.getName());
		// delete the user
		// userRepository.delete(u);
		// confirm user deletion by getting the user by id
		// assertFalse(userRepository.findById(u.getId()).isPresent());
	}

}