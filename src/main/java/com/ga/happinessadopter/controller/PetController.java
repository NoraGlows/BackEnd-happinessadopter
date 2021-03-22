package com.ga.happinessadopter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga.happinessadopter.dao.PetDao;
import com.ga.happinessadopter.model.Pet;


@RestController
public class PetController {
	@Autowired
	private Environment env;
	@Autowired
	private PetDao dao;


	@PostMapping("/pet/add")
	public Pet addPet(@RequestBody Pet pet ) {
		dao.save(pet);
		return pet;
	}


	@GetMapping("/pet/index")
	public Iterable<Pet> getPet() {
		var it = dao.findAll();
		return it;
	}


	@GetMapping("/pet/detail")
	public Pet petDetails(@RequestParam int id) {
		System.out.println(id);
		Pet pet = dao.findById(id);
		return pet;
	}


	@PutMapping("/pet/edit")
	public Pet editPet(@RequestBody Pet pet) {
		dao.save(pet);
		return pet;
	}


	@DeleteMapping("/pet/delete")
	public boolean deletePet(@RequestParam int id) {
		Pet pet = dao.findById(id);
		dao.deleteById(id);
		return true;
	}
}
