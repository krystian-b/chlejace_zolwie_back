package com.back.chlejacezolwie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChlejacezolwieApiTest {
	
	@Autowired
	DataRepository repo;
	
	@GetMapping("/getall")
	public List<Data> getAllData() {
		return repo.findAll();
	}
	
	@GetMapping("/get")
	public Optional<Data> getNumber(@RequestParam Long x) {
		return repo.findById(x);
	}
	
}
