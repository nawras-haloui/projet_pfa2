package com.javainuse.repository;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;


import com.javainuse.model.DAOUser;

public interface CustomerRepository extends CrudRepository<DAOUser, Integer> {
	List<DAOUser> findByUsername(String username);
}
