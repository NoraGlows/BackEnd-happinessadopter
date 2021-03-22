package com.ga.happinessadopter.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.happinessadopter.model.User;


public interface UserDao extends CrudRepository<User, Integer> {
	public User findByEmailAddress(String emailAddress);}
