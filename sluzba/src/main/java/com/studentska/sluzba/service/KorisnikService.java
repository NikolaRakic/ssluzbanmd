package com.studentska.sluzba.service;


import com.studentska.sluzba.dto.request.KorisnikDTO;
import com.studentska.sluzba.model.Korisnik;

public interface KorisnikService {

	String kreirajIliIzmeni(KorisnikDTO request) throws Exception;
	
	void izbrisi(int idKorisnika) throws Exception;

	Korisnik findByUsername(String username);
}
