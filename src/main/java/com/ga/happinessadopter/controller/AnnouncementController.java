package com.ga.happinessadopter.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga.happinessadopter.model.Announcement;
import com.ga.happinessadopter.dao.AnnouncementDao;
@RestController

public class AnnouncementController {
	@Autowired
	private AnnouncementDao dao ;
	
	@PostMapping("/announcement/add")
	public Announcement addAnnouncement(@RequestBody Announcement announcement) {
		dao.save(announcement);
		return announcement;
}
	@GetMapping("/announcement/index")
	public Iterable<Announcement> getannouncement() {
		var it = dao.findAll();
		return it;
	}
	
	@PutMapping("/announcement/edit")
	public Announcement editAnnouncement(@RequestBody Announcement announcement) {
		dao.save(announcement);
		return announcement;
	}
	
	@DeleteMapping("/announcement/delete")
	public boolean deleteAnnouncement(@RequestParam int id) {
		Announcement announcement = dao.findById(id);
		dao.deleteById(id);
		return true;
	}

}