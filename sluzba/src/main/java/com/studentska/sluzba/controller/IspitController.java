package com.studentska.sluzba.controller;

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

import com.studentska.sluzba.dto.request.IspitIliKolokvijumDTO;
import com.studentska.sluzba.dto.request.PredmetDTO;
import com.studentska.sluzba.dto.response.IspitiDTO;
import com.studentska.sluzba.dto.response.PolozenIspitResponseDTO;
import com.studentska.sluzba.model.Rok;
import com.studentska.sluzba.model.TipIspita;
import com.studentska.sluzba.service.IspitService;
import com.studentska.sluzba.service.RokService;
import com.studentska.sluzba.service.TipIspitaService;

@RestController
@RequestMapping("/ispit")
public class IspitController {

	@Autowired
	IspitService ispitService;
	
	@Autowired
	RokService rokService;
	
	@Autowired
	TipIspitaService tipIspitaService;
	
	//drugi nacin
//	@GetMapping("/polozeniIspitiZaStudenta")
//	public ResponseEntity<?> polozeniIspitiZaStudenta(@RequestParam("idStudenta") int idStudenta){
//		try {
//			List<PolozenIspitResponseDTO> response = ispitService.polozeniIspitiZaStudenta(idStudenta);
//			return new ResponseEntity<List<PolozenIspitResponseDTO>>(response, HttpStatus.OK);
//		}catch(Exception e) {
//			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
	
	//prvi nacin
	@GetMapping("/polozeniIspitiZaStudenta/{idStudenta}")
	public ResponseEntity<?> polozeniIspitiZaStudentaOptimized(@PathVariable int idStudenta){
		try {
			List<PolozenIspitResponseDTO> response = ispitService.polozeniIspitiZaStudentaOptimized(idStudenta);
			return new ResponseEntity<List<PolozenIspitResponseDTO>>(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getSviIspiti")
	public ResponseEntity<List<IspitiDTO>> getSviIspiti(){
			List<IspitiDTO> ispiti = ispitService.getAll();
			return new ResponseEntity<>(ispiti, HttpStatus.OK);
	
	}
	
	@GetMapping("/getIspitiStudentNastavnik/{id}")
	public ResponseEntity<List<IspitiDTO>> getIspitiStudentNastavnik(@PathVariable int id){
			
			List<IspitiDTO> ispiti = ispitService.getAllByStudentOrNastavnik(id);
			return new ResponseEntity<>(ispiti, HttpStatus.OK);
	
	}
	
	
	@PostMapping("/unosIspitaIliKolokvijuma")
	public ResponseEntity<String> unosIspitaIliKolokvijuma (@RequestBody IspitIliKolokvijumDTO request){
		
		try {
			return new ResponseEntity<String>(ispitService.unosIspitaIliKolokvijuma(request),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	 
	@GetMapping("/getSviRokovi")
	public ResponseEntity<List<Rok>> getSviRokovi(){
			List<Rok> rokovi = rokService.getRokovi();
			return new ResponseEntity<>(rokovi, HttpStatus.OK);
	
	}
	
	@GetMapping("/getTipoviIspita")
	public ResponseEntity<List<TipIspita>> getTipoviIspita(){
			List<TipIspita> tipoviIspita = tipIspitaService.findAll();
			return new ResponseEntity<>(tipoviIspita, HttpStatus.OK);
	
	}
	
	@GetMapping("/getSviAktivniRokovi")
	public ResponseEntity<List<Rok>> getSviAktivniRokovi(){
			List<Rok> rokovi = rokService.getAktivniRokovi();
			return new ResponseEntity<>(rokovi, HttpStatus.OK);
	
	}
	
	@PostMapping("/sacuvajRokove")
	public ResponseEntity<String> sacuvajRokove(@RequestBody List<Rok> request){
		try {
			return new ResponseEntity<String>(rokService.sacuvajRokove(request),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
