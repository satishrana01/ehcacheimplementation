package com.ehcache.spring.controller;

import com.ehcache.spring.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

	
	@Autowired
	private CustomerDAO customerDAO;

	
	@GetMapping("/customers/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		StringBuilder customer = customerDAO.get(id);
		return new ResponseEntity("", HttpStatus.OK);
	}

	@GetMapping("/mycustomers/{id}")
	public ResponseEntity getMyCustomer(@PathVariable("id") Long id) {

		StringBuilder customer = customerDAO.get2(id);
		return new ResponseEntity("", HttpStatus.OK);
	}

	@GetMapping("/readFileName")
	public ResponseEntity getFileName() {

		String fileName = customerDAO.getFileName();
		return new ResponseEntity(fileName, HttpStatus.OK);
	}

	@GetMapping("/clearcache")
	public ResponseEntity clearEHCache() {

		customerDAO.clearCache();
		return new ResponseEntity("Hurray Cache Cleared !!!!!!!!", HttpStatus.OK);

	}



}