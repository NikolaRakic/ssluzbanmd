package com.studentska.sluzba.service;


import java.util.List;

import com.studentska.sluzba.dto.request.KorisnikDTO;
import com.studentska.sluzba.dto.request.ProfesorDTO;
import com.studentska.sluzba.dto.request.StudentDTO;
import com.studentska.sluzba.dto.response.ProfesoriDTO;
import com.studentska.sluzba.dto.response.StudentiDTO;
import com.studentska.sluzba.model.Korisnik;

public interface KorisnikService {

	String kreirajIliIzmeni(KorisnikDTO request) throws Exception;
	
	String izbrisi(int idKorisnika) throws Exception;

	Korisnik findByUsername(String username);

	List<Korisnik> findAll();
	
	StudentDTO findOneStudent(int id) throws Exception;
	
	ProfesorDTO findOneNastavnik(int id) throws Exception;

	List<StudentiDTO> sviStudenti() throws Exception;

	List<ProfesoriDTO> sviProfesori() throws Exception;
	
	List<ProfesoriDTO> findAllByPredmetId(int id) throws Exception;
	
	List<ProfesoriDTO> findAllByPredmetIdNot(int id) throws Exception;

	List<StudentiDTO> sviStudentiZaSmer(int id);
}
