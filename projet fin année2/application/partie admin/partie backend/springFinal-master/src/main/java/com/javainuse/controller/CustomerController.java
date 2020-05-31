package com.javainuse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.javainuse.model.DAOUser;
import com.javainuse.model.UserDTO;
import com.javainuse.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerRepository repository;

	@GetMapping("/users")
	public List<DAOUser> getAllUsers() {
		System.out.println("Get all Customers...");

		List<DAOUser> users = new ArrayList<>();
		repository.findAll().forEach(users::add);

		return users;
	}

	@PostMapping(value = "/user/create")
	public DAOUser postCustomer(@RequestBody DAOUser customer) {

		DAOUser _customer = repository.save(new DAOUser(customer.getUsername(), customer.getPhone()));
				return _customer;
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUsers(@PathVariable("id") Integer id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/users/delete")
	public ResponseEntity<String> deleteAllUsers() {
		System.out.println("Delete All Customers...");

		repository.deleteAll();

		return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
	}

	@GetMapping(value = "users/username/{username}")
	public List<DAOUser> findByUsername(@PathVariable String username) {

		List<DAOUser> customers = repository.findByUsername(username);
		return customers;
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<DAOUser> updateUser(@PathVariable("id") Integer id, @RequestBody DAOUser customer) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<DAOUser> customerData = repository.findById(id);

		if (customerData.isPresent()) {
			DAOUser _customer = customerData.get();
			_customer.setUsername(customer.getUsername());
			_customer.setEmail(customer.getEmail());
			
			return new ResponseEntity<>(repository.save(_customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
