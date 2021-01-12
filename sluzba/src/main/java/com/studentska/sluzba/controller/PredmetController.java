package com.studentska.sluzba.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentska.sluzba.dto.request.DodeliPredmetSmeruRequestDTO;
import com.studentska.sluzba.dto.request.PredmetDTO;
import com.studentska.sluzba.dto.request.SmerDTO;
import com.studentska.sluzba.dto.response.PredmetNaSmeruResponseDTO;
import com.studentska.sluzba.model.Predmet;
import com.studentska.sluzba.model.Smer;
import com.studentska.sluzba.service.PredmetService;
import com.studentska.sluzba.service.SmerService;

@RestController
@RequestMapping("/predmet")
public class PredmetController {
	
	@Autowired
	PredmetService predmetService;
	
	@PostMapping("/kreirajIliIzmeni")
	public ResponseEntity<String> kreirajIliIzmeni (@RequestBody PredmetDTO request){
		
		try {
			return new ResponseEntity<String>(predmetService.kreirajIliIzmeni(request),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	
	
	}
	
	@PostMapping("/obrisi/{id}")
	public ResponseEntity<String> obrisi(@PathVariable int id){
		try {
			return new ResponseEntity<String>(predmetService.izbrisi(id),HttpStatus.OK);
		}catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping("/getPredmet/{id}")
	public ResponseEntity<PredmetDTO> getSmer(@PathVariable int id){
		Predmet predmet = predmetService.findOne(id);
		if(predmet == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new PredmetDTO(predmet), HttpStatus.OK);
	}
	
	
	@GetMapping("/getPredmeti")
	public ResponseEntity<List<PredmetDTO>> getPredmeti(){
		System.out.println("USAAO");
		List<Predmet> predmeti = predmetService.findAll();
		//convert predmeti to DTOs
		List<PredmetDTO> predmetiDTO = new ArrayList<>();
		for (Predmet s : predmeti) {
			predmetiDTO.add(new PredmetDTO(s));
			System.out.println(s);
		}
		return new ResponseEntity<>(predmetiDTO, HttpStatus.OK);
	}
	
	@GetMapping("/predmetiPoSmeru")
	public ResponseEntity<?> getPredmetiPoSmeru(@RequestParam("idSmera") int idSmera){
		try {
			List<PredmetNaSmeruResponseDTO> response = predmetService.getPredmetiPoSmeru(idSmera);
			return new ResponseEntity<List<PredmetNaSmeruResponseDTO>>(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/dodeliPredmetSmeru")
	public ResponseEntity<String> dodeliPredmetSmeru(@RequestBody DodeliPredmetSmeruRequestDTO request){
		try {
			String response = predmetService.dodeliPredmetSmeru(request);
			return new ResponseEntity<String>(response,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}