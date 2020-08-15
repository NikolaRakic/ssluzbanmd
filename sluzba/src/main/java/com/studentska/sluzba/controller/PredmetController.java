package com.studentska.sluzba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentska.sluzba.dto.request.KreirajPredmetRequestDTO;
import com.studentska.sluzba.service.PredmetService;
import com.studentska.sluzba.service.SmerService;

@RestController
@RequestMapping("/predmet")
public class PredmetController {
	
	@Autowired
	PredmetService predmetService;
	
	@PostMapping("/kreirajIliIzmeni")
	public ResponseEntity<String> kreirajIliIzmeni (@RequestBody KreirajPredmetRequestDTO request){
		
		try {
			return new ResponseEntity<String>(predmetService.kreirajIliIzmeni(request),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	
	
	}
	

}