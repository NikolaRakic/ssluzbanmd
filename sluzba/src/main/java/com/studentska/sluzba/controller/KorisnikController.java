package com.studentska.sluzba.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.studentska.sluzba.dto.request.KorisnikDTO;
import com.studentska.sluzba.dto.request.LoginDTO;
import com.studentska.sluzba.dto.response.LoggedInUserDTO;
import com.studentska.sluzba.model.Korisnik;
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
	public ResponseEntity<String> kreirajIliIzmeni (@RequestBody KorisnikDTO request){
		
		try {
			return new ResponseEntity<String>(korisnikService.kreirajIliIzmeni(request),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/obrisi")
	public ResponseEntity<String> obrisi (int id){
		
		try {
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDto){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
		Authentication authentication = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
		Korisnik korisnikFromDb = korisnikService.findByUsername(loginDto.getUsername());
		LoggedInUserDTO loggedIn = new LoggedInUserDTO(korisnikFromDb.getIdKorisnik(), tokenUtils.generateToken(userDetails), korisnikFromDb.getUsername(), userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(loggedIn, HttpStatus.OK);
				
	}
	
	@PreAuthorize("hasAuthority('nastavnik')")
	@GetMapping("/samoZaNastavnike")
	public ResponseEntity<String> samoZaNastavnike(){
		return new ResponseEntity<String>("Sve ok", HttpStatus.OK);
	}
	
	

}
