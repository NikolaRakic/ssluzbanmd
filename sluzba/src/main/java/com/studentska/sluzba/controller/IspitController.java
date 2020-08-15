package com.studentska.sluzba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentska.sluzba.dto.response.PolozenIspitResponseDTO;
import com.studentska.sluzba.service.IspitService;

@RestController
@RequestMapping("/ispit")
public class IspitController {

	@Autowired
	IspitService ispitService;
	
	@GetMapping("/polozeniIspitiZaStudenta")
	public ResponseEntity<?> polozeniIspitiZaStudenta(@RequestParam("idStudenta") int idStudenta){
		try {
			List<PolozenIspitResponseDTO> response = ispitService.polozeniIspitiZaStudenta(idStudenta);
			return new ResponseEntity<List<PolozenIspitResponseDTO>>(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/polozeniIspitiZaStudentaOptimized")
	public ResponseEntity<?> polozeniIspitiZaStudentaOptimized(@RequestParam("idStudenta") int idStudenta){
		try {
			List<PolozenIspitResponseDTO> response = ispitService.polozeniIspitiZaStudentaOptimized(idStudenta);
			return new ResponseEntity<List<PolozenIspitResponseDTO>>(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
