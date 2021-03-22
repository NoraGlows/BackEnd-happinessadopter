package com.ga.happinessadopter.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.happinessadopter.model.Pet;


public interface PetDao extends CrudRepository<Pet, Integer> {
	public Pet findById(int id);
}