package com.back.chlejacezolwie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChlejacezolwieTest {
	
	@GetMapping("/test")
	public int getNumber(@RequestParam int x) {
		return x;
	}
	
}
