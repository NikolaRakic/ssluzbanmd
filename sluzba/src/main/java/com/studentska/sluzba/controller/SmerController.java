package com.studentska.sluzba.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentska.sluzba.dto.request.PredmetDTO;
import com.studentska.sluzba.dto.request.SmerDTO;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.PredmetNaSmeru;
import com.studentska.sluzba.model.Smer;
import com.studentska.sluzba.service.KorisnikService;
import com.studentska.sluzba.service.SmerService;



@RestController
@RequestMapping("/smer")
public class SmerController {
	
	@Autowired
	SmerService smerService;
	
	@PostMapping("/kreirajIliIzmeni")
	public ResponseEntity<String> kreirajIliIzmeni (@RequestBody SmerDTO request){
		
		try {
			return new ResponseEntity<String>(smerService.kreirajIliIzmeni(request),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	
	
	}
	
	@GetMapping("/getSmer/{id}")
	public ResponseEntity<SmerDTO> getSmer(@PathVariable int id){
		System.out.println("usao");
		Smer smer = smerService.findOne(id);
		if(smer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new SmerDTO(smer), HttpStatus.OK);
	}
	
	
	@GetMapping()
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<List<SmerDTO>> getSmerovi(){
		List<Smer> smerovi = smerService.findAll();
		//convert smerovi to DTOs
		List<SmerDTO> smeroviDTO = new ArrayList<>();
		for (Smer s : smerovi) {
			smeroviDTO.add(new SmerDTO(s));
		}
		return new ResponseEntity<>(smeroviDTO, HttpStatus.OK);
	}
	
	@PutMapping("/obrisi/{id}")
	public ResponseEntity<String> obrisi(@PathVariable int id){
		try {
			return new ResponseEntity<String>(smerService.izbrisi(id),HttpStatus.OK);
		}catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	


}
