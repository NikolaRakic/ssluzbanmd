package com.studentska.sluzba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.studentska.sluzba.dto.request.KreirajSmerRequestDTO;
import com.studentska.sluzba.service.KorisnikService;
import com.studentska.sluzba.service.SmerService;

@RestController
@RequestMapping("/smer")
public class SmerController {
	
	@Autowired
	SmerService smerService;
	
	@PostMapping("/kreirajIliIzmeni")
	public ResponseEntity<String> kreirajIliIzmeni (@RequestBody KreirajSmerRequestDTO request){
		
		try {
			return new ResponseEntity<String>(smerService.kreirajIliIzmeni(request),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	
	
	}
	

}
