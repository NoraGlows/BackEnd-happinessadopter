package com.ga.happinessadopter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/hello")
	public String hello() {
		return "Hello Ghost";
		
	}
}
