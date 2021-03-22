package com.ga.happinessadopter.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.happinessadopter.model.Announcement;


public interface AnnouncementDao extends CrudRepository<Announcement, Integer> {
	public Announcement findById(int id);
}
