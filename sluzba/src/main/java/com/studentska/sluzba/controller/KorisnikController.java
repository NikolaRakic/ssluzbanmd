package com.studentska.sluzba.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studentska.sluzba.dto.request.KorisnikDTO;
import com.studentska.sluzba.dto.request.LoginDTO;
import com.studentska.sluzba.dto.request.PredmetDTO;
import com.studentska.sluzba.dto.request.SimpleMessageResponseDTO;
import com.studentska.sluzba.dto.request.StudentDTO;
import com.studentska.sluzba.dto.response.LoggedInUserDTO;
import com.studentska.sluzba.dto.response.ProfesoriDTO;
import com.studentska.sluzba.dto.response.StudentiDTO;
import com.studentska.sluzba.model.Korisnik;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.security.TokenUtils;
import com.studentska.sluzba.service.KorisnikService;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {
	
	@Autowired
	KorisnikService korisnikService;
	
	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@PostMapping("/kreirajIliIzmeni")
	public ResponseEntity<SimpleMessageResponseDTO> kreirajIliIzmeni (@RequestBody KorisnikDTO request){
		try {
			return new ResponseEntity<SimpleMessageResponseDTO>(new SimpleMessageResponseDTO(korisnikService.kreirajIliIzmeni(request)),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<SimpleMessageResponseDTO>(new SimpleMessageResponseDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/obrisi/{id}")
	public ResponseEntity<String> obrisi (@PathVariable int id){
		try {
			
			return new ResponseEntity<String>(korisnikService.izbrisi(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDto){
		System.out.println(loginDto.getUsername() + " " + loginDto.getPassword());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
		Authentication authentication = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
		Korisnik korisnikFromDb = korisnikService.findByUsername(loginDto.getUsername());
		String uloga = korisnikFromDb.getUloga().getNaziv();
		System.out.println("ULOGA JE " + uloga);
		LoggedInUserDTO loggedIn = new LoggedInUserDTO(korisnikFromDb.getIdKorisnik(), tokenUtils.generateToken(userDetails), korisnikFromDb.getUsername(), userDetails.getUsername(), uloga, userDetails.getAuthorities());
		System.out.println(loggedIn.getAuthorities());
		return new ResponseEntity<>(loggedIn, HttpStatus.OK);
				
	}
	
	@PreAuthorize("hasAuthority('nastavnik')")
	@GetMapping("/samoZaNastavnike")
	public ResponseEntity<String> samoZaNastavnike(){
		return new ResponseEntity<String>("Sve ok", HttpStatus.OK);
	}
	
	@GetMapping("/getStudenti")
	public ResponseEntity<?> getKorisnici(){
		try {
			List<StudentiDTO> response = korisnikService.sviStudenti();
			
			return new ResponseEntity<List<StudentiDTO>>(response , HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable int id) throws Exception{
		StudentDTO studentDTO = korisnikService.findOneStudent(id);
		if(studentDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(studentDTO, HttpStatus.OK);
	}
	
	@GetMapping("/getProfesori")
	public ResponseEntity<?> getProfesori(){
		try {
			List<ProfesoriDTO> response = korisnikService.sviProfesori();
			
			return new ResponseEntity<List<ProfesoriDTO>>(response , HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
